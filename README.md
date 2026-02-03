# API Banco - Proyecto Spring Boot

API REST para gestión de clientes, cuentas y movimientos bancarios.

## 🛠️ Tecnologías

- Java 17
- Spring Boot 3.2.1
- PostgreSQL 15
- Docker & Docker Compose
- Maven
- Lombok

## 📋 Requisitos previos

### Opción 1: Con Docker (Recomendado)
- Docker Desktop instalado
- Docker Compose incluido

### Opción 2: Sin Docker
- Java 17 JDK
- Maven 3.8+
- PostgreSQL 15
- pgAdmin (opcional)

## 🚀 Ejecución con Docker (MÁS FÁCIL)

### 1. Compilar el proyecto
```bash
mvn clean package -DskipTests
```

### 2. Iniciar todos los servicios
```bash
docker-compose up --build
```

Esto levantará:
- PostgreSQL en puerto 5432
- API en puerto 8080

### 3. Verificar que funciona
```
http://localhost:8080/api/clientes
```

### Comandos útiles Docker:
```bash
# Ver logs
docker-compose logs -f

# Detener servicios
docker-compose down

# Eliminar todo (incluido volúmenes)
docker-compose down -v

# Reiniciar solo la API
docker-compose restart api
```

## 🔧 Ejecución local (Sin Docker)

### 1. Configurar PostgreSQL

Instalar PostgreSQL y ejecutar:
```sql
CREATE USER banco_user WITH PASSWORD 'banco_pass';
CREATE DATABASE banco_db OWNER banco_user;
GRANT ALL PRIVILEGES ON DATABASE banco_db TO banco_user;
```

### 2. Ejecutar script de base de datos
```bash
psql -U banco_user -d banco_db -f BaseDatos.sql
```

### 3. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

O compilar y ejecutar:
```bash
mvn clean package
java -jar target/api-0.0.1-SNAPSHOT.jar
```

## 📡 Endpoints

Todos los endpoints tienen el prefijo `/api`

### Clientes
- `GET /api/clientes` - Obtener todos los clientes
- `GET /api/clientes/{id}` - Obtener cliente por ID
- `POST /api/clientes` - Crear cliente
- `PUT /api/clientes/{id}` - Actualizar cliente
- `DELETE /api/clientes/{id}` - Eliminar cliente

### Cuentas
- `GET /api/cuentas` - Obtener todas las cuentas
- `GET /api/cuentas/{id}` - Obtener cuenta por ID
- `POST /api/cuentas` - Crear cuenta
- `PUT /api/cuentas/{id}` - Actualizar cuenta
- `DELETE /api/cuentas/{id}` - Eliminar cuenta

### Movimientos
- `GET /api/movimientos` - Obtener todos los movimientos
- `GET /api/movimientos/{id}` - Obtener movimiento por ID
- `POST /api/movimientos` - Crear movimiento
- `PUT /api/movimientos/{id}` - Actualizar movimiento
- `DELETE /api/movimientos/{id}` - Eliminar movimiento

## 📮 Postman

Importar la colección `BancoAPI-Postman.json` en Postman:

1. Abrir Postman
2. File → Import
3. Seleccionar `BancoAPI-Postman.json`
4. Las peticiones estarán listas para usar

## 📊 Base de Datos

### Datos de ejemplo precargados:

**Clientes:**
- Jose Lema (ID: 1)
- Marianela Montalvo (ID: 2)
- Juan Osorio (ID: 3)

**Cuentas:**
- 478758 (Ahorro, Jose Lema)
- 225487 (Corriente, Marianela Montalvo)
- 495878 (Ahorros, Juan Osorio)
- 496825 (Ahorros, Marianela Montalvo)

## 🐛 Solución de problemas

### Error: Puerto 8080 ocupado
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <número> /F

# O cambiar el puerto en application.properties
server.port=8081
```

### Error: PostgreSQL no conecta (Docker)
```bash
# Ver logs de PostgreSQL
docker-compose logs postgres

# Reiniciar contenedor
docker-compose restart postgres
```

### Error: "Saldo no disponible"
- Es una validación del negocio
- No se puede retirar más de lo que hay en la cuenta

### Recrear base de datos desde cero
```bash
docker-compose down -v
docker-compose up --build
```

## 📁 Estructura del proyecto

```
api/
├── src/
│   ├── main/
│   │   ├── java/com/banco/api/
│   │   │   ├── controller/      # Controladores REST
│   │   │   ├── model/           # Entidades JPA
│   │   │   ├── repository/      # Repositorios
│   │   │   ├── service/         # Lógica de negocio
│   │   │   └── exception/       # Excepciones personalizadas
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── docker-compose.yml
├── Dockerfile
├── BaseDatos.sql
├── init-db.sql
└── pom.xml
```

## 🔐 Configuración

### Variables de entorno (Docker)

El `application.properties` está configurado para soportar variables de entorno:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/banco_db}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:banco_user}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:banco_pass}
```

### Modo desarrollo vs producción

- **Desarrollo local**: Usa valores por defecto (localhost)
- **Docker**: Usa variables de entorno del docker-compose.yml

## ✅ Testing

```bash
# Ejecutar tests
mvn test

# Compilar sin tests
mvn clean package -DskipTests
```

## 📝 Notas

- `ddl-auto=validate`: Las tablas deben existir antes de iniciar (creadas por SQL)
- Fechas de movimientos se generan automáticamente
- El tipo de movimiento se calcula según el valor (positivo=depósito, negativo=retiro)
- Validación de saldo disponible antes de crear movimiento

## 👨‍💻 Autor

Prueba Técnica - API Banco
