# API Bancaria - Prueba Técnica

## Descripción
API REST desarrollada con Spring Boot para gestión de clientes, cuentas y movimientos bancarios.

## Tecnologías Utilizadas
- Java 17
- Spring Boot 3.4.1
- PostgreSQL 15
- JPA/Hibernate
- Lombok
- Maven
- Docker

## Requisitos Previos
- Java 17 o superior
- Maven 3.6 o superior
- PostgreSQL 15
- Docker y Docker Compose
- Postman (para pruebas)

## Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone <URL_DEL_REPOSITORIO>
cd api
```

### 2. Configurar Base de Datos (Opción Local)
Editar `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/banco_db
spring.datasource.username=banco_user
spring.datasource.password=banco_pass
```

### 3. Compilar el proyecto
```bash
./mvnw clean package
```

### 4. Ejecutar con Docker (Recomendado)
```bash
docker-compose up --build
```

### 5. Ejecutar localmente (sin Docker)
```bash
./mvnw spring-boot:run
```

## Estructura del Proyecto
```
api/
├── src/
│   ├── main/
│   │   ├── java/com/banco/api/
│   │   │   ├── controller/     # Controladores REST
│   │   │   ├── model/          # Entidades JPA
│   │   │   ├── repository/     # Repositorios JPA
│   │   │   ├── service/        # Lógica de negocio
│   │   │   ├── exception/      # Manejo de excepciones
│   │   │   └── ApiApplication.java
│   │   └── resources/
│   │       └── application.properties
├── BaseDatos.sql               # Script de BD
├── BancoAPI-Postman.json       # Colección Postman
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## Endpoints Disponibles

### Clientes
- `GET /api/clientes` - Obtener todos los clientes
- `GET /api/clientes/{id}` - Obtener cliente por ID
- `POST /api/clientes` - Crear nuevo cliente
- `PUT /api/clientes/{id}` - Actualizar cliente
- `DELETE /api/clientes/{id}` - Eliminar cliente

### Cuentas
- `GET /api/cuentas` - Obtener todas las cuentas
- `GET /api/cuentas/{id}` - Obtener cuenta por ID
- `POST /api/cuentas` - Crear nueva cuenta
- `PUT /api/cuentas/{id}` - Actualizar cuenta
- `DELETE /api/cuentas/{id}` - Eliminar cuenta

### Movimientos
- `GET /api/movimientos` - Obtener todos los movimientos
- `GET /api/movimientos/{id}` - Obtener movimiento por ID
- `POST /api/movimientos` - Registrar nuevo movimiento
- `PUT /api/movimientos/{id}` - Actualizar movimiento
- `DELETE /api/movimientos/{id}` - Eliminar movimiento

## Funcionalidades Implementadas

### F1: CRUD Completo
✅ Operaciones Crear, Leer, Actualizar y Eliminar para:
- Clientes
- Cuentas
- Movimientos

### F2: Registro de Movimientos
✅ Valores positivos (depósitos) y negativos (retiros)
✅ Actualización automática del saldo disponible
✅ Registro de transacciones con fecha automática

### F3: Validación de Saldo
✅ Validación de saldo disponible antes de realizar movimientos
✅ Mensaje de error: "Saldo no disponible"
✅ Manejo de excepciones personalizado

## Uso con Postman

1. Importar la colección `BancoAPI-Postman.json` en Postman
2. La API estará disponible en `http://localhost:8080/api`

### Ejemplo: Crear Cliente
```json
POST http://localhost:8080/api/clientes
{
    "nombre": "Jose Lema",
    "genero": "M",
    "edad": 35,
    "identificacion": "1234567890",
    "direccion": "Otavalo sn y principal",
    "telefono": "098254785",
    "contrasena": "1234",
    "estado": true
}
```

### Ejemplo: Crear Cuenta
```json
POST http://localhost:8080/api/cuentas
{
    "numeroCuenta": "478758",
    "tipoCuenta": "Ahorro",
    "saldoInicial": 2000,
    "estado": true,
    "cliente": {
        "clienteId": 1
    }
}
```

### Ejemplo: Registrar Movimiento (Retiro)
```json
POST http://localhost:8080/api/movimientos
{
    "valor": -575,
    "cuenta": {
        "id": 1
    }
}
```

## Manejo de Errores

### Saldo Insuficiente (400 Bad Request)
```json
{
    "timestamp": "2026-01-23T10:30:00",
    "mensaje": "Saldo no disponible",
    "status": 400
}
```

### Recurso No Encontrado (404 Not Found)
```json
{
    "timestamp": "2026-01-23T10:30:00",
    "mensaje": "Cliente no encontrado con id: 1",
    "status": 404
}
```

## Despliegue con Docker

### Construir y ejecutar
```bash
# Construir el JAR
./mvnw clean package

# Iniciar los contenedores
docker-compose up --build
```

### Verificar contenedores
```bash
docker ps
```

Deberías ver:
- `banco_postgres` - Base de datos PostgreSQL
- `banco_api` - Aplicación Spring Boot

### Detener contenedores
```bash
docker-compose down
```

### Detener y eliminar volúmenes
```bash
docker-compose down -v
```

## Base de Datos

El script `BaseDatos.sql` incluye:
- Esquema de tablas (clientes, cuentas, movimientos)
- Datos de ejemplo según el documento de la prueba técnica

### Acceder a PostgreSQL
```bash
docker exec -it banco_postgres psql -U banco_user -d banco_db
```

## Logs y Debug

Ver logs de la aplicación:
```bash
docker logs banco_api -f
```

Ver logs de PostgreSQL:
```bash
docker logs banco_postgres -f
```

## Buenas Prácticas Implementadas

✅ **Patrón Repository**: Separación de la capa de acceso a datos
✅ **Patrón Service**: Lógica de negocio en capa de servicio
✅ **Manejo de Excepciones**: Excepciones personalizadas con @RestControllerAdvice
✅ **DTOs implícitos**: Uso de entidades JPA directamente (nivel junior)
✅ **Validación de negocio**: Validación de saldo antes de transacciones
✅ **Transacciones**: Uso de @Transactional en operaciones críticas
✅ **Clean Code**: Código limpio y fácil de entender

## Arquitectura

```
Cliente (REST) 
    ↓
Controller (Endpoints REST)
    ↓
Service (Lógica de negocio)
    ↓
Repository (Acceso a datos)
    ↓
Base de Datos (PostgreSQL)
```

## Notas Adicionales

- La aplicación usa `spring.jpa.hibernate.ddl-auto=update` para actualizar el esquema automáticamente
- El puerto por defecto es `8080`
- El contexto de la aplicación es `/api`
- Los logs están configurados en nivel DEBUG para el paquete `com.banco`

## Autor
Desarrollado como parte de la Prueba Técnica para posición Backend Junior

## Fecha
Enero 2026
