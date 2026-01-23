# 🏦 PRUEBA TÉCNICA - API BANCARIA
## Guía de Entrega - Posición Backend Junior

---

## ✅ FUNCIONALIDADES COMPLETADAS

### ✔️ F1: CRUD Completo (IMPLEMENTADO)
- ✅ **Clientes**: Crear, Leer, Actualizar, Eliminar
- ✅ **Cuentas**: Crear, Leer, Actualizar, Eliminar  
- ✅ **Movimientos**: Crear, Leer, Actualizar, Eliminar
- ✅ Endpoints: `/api/clientes`, `/api/cuentas`, `/api/movimientos`

### ✔️ F2: Registro de Movimientos (IMPLEMENTADO)
- ✅ Movimientos con valores positivos (depósitos)
- ✅ Movimientos con valores negativos (retiros)
- ✅ Actualización automática del saldo disponible
- ✅ Registro de transacciones con fecha automática

### ✔️ F3: Validación de Saldo (IMPLEMENTADO)
- ✅ Validación de saldo antes de realizar movimientos
- ✅ Mensaje de error: "Saldo no disponible"
- ✅ Manejo de excepciones personalizado con HTTP 400

---

## 📂 ARCHIVOS ENTREGABLES

```
api/
├── src/                                    # Código fuente
│   ├── main/java/com/banco/api/
│   │   ├── model/                          # ✅ Entidades JPA
│   │   │   ├── Persona.java               # Clase base
│   │   │   ├── Cliente.java               # Hereda de Persona
│   │   │   ├── Cuenta.java                # Entidad Cuenta
│   │   │   └── Movimiento.java            # Entidad Movimiento
│   │   ├── repository/                     # ✅ Patrón Repository
│   │   │   ├── ClienteRepository.java
│   │   │   ├── CuentaRepository.java
│   │   │   └── MovimientoRepository.java
│   │   ├── service/                        # ✅ Lógica de negocio
│   │   │   ├── ClienteService.java
│   │   │   ├── CuentaService.java
│   │   │   └── MovimientoService.java
│   │   ├── controller/                     # ✅ Endpoints REST
│   │   │   ├── ClienteController.java
│   │   │   ├── CuentaController.java
│   │   │   └── MovimientoController.java
│   │   ├── exception/                      # ✅ Manejo de errores
│   │   │   ├── SaldoNoDisponibleException.java
│   │   │   ├── RecursoNoEncontradoException.java
│   │   │   └── GlobalExceptionHandler.java
│   │   └── ApiApplication.java             # Main class
│   └── resources/
│       └── application.properties          # ✅ Configuración
├── BaseDatos.sql                           # ✅ Script de BD
├── BancoAPI-Postman.json                   # ✅ Colección Postman
├── Dockerfile                              # ✅ Para Docker
├── docker-compose.yml                      # ✅ Despliegue completo
├── README.md                               # ✅ Documentación
└── GUIA_ENTREGA.md                        # Este archivo
```

---

## 🚀 CÓMO EJECUTAR EL PROYECTO

### Opción 1: Con Docker (RECOMENDADO) 🐳

```bash
# 1. Navegar al directorio del proyecto
cd api

# 2. Compilar el proyecto (si aún no está compilado)
./mvnw clean package -DskipTests

# 3. Iniciar con Docker Compose
docker-compose up --build

# La API estará disponible en: http://localhost:8080/api
```

### Opción 2: Sin Docker (Requiere PostgreSQL local) 💻

```bash
# 1. Crear la base de datos en PostgreSQL
psql -U postgres
CREATE DATABASE banco_db;
CREATE USER banco_user WITH PASSWORD 'banco_pass';
GRANT ALL PRIVILEGES ON DATABASE banco_db TO banco_user;
\q

# 2. Ejecutar el script SQL
psql -U banco_user -d banco_db -f BaseDatos.sql

# 3. Ejecutar la aplicación desde VS Code
# Abrir ApiApplication.java y presionar F5
# O usar el botón "Run Java" en VS Code
```

### Opción 3: Ejecutar desde terminal (si Maven funciona)

```bash
./mvnw spring-boot:run
```

---

## 📝 PROBAR LOS ENDPOINTS

### 1. Importar Colección en Postman
1. Abrir Postman
2. File → Import
3. Seleccionar `BancoAPI-Postman.json`
4. Ejecutar las peticiones

### 2. Pruebas Básicas

#### A. Crear un Cliente
```http
POST http://localhost:8080/api/clientes
Content-Type: application/json

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

#### B. Crear una Cuenta
```http
POST http://localhost:8080/api/cuentas
Content-Type: application/json

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

#### C. Registrar un Retiro (F2)
```http
POST http://localhost:8080/api/movimientos
Content-Type: application/json

{
    "valor": -575,
    "cuenta": {
        "id": 1
    }
}
```

#### D. Probar Saldo Insuficiente (F3)
```http
POST http://localhost:8080/api/movimientos
Content-Type: application/json

{
    "valor": -10000,
    "cuenta": {
        "id": 1
    }
}
```

**Respuesta esperada:**
```json
{
    "timestamp": "2026-01-23T10:30:00",
    "mensaje": "Saldo no disponible",
    "status": 400
}
```

---

## 🏗️ ARQUITECTURA IMPLEMENTADA

### Capas de la Aplicación
```
┌─────────────────────────────────┐
│   Cliente (Postman/Browser)    │
└────────────┬────────────────────┘
             │ HTTP REST
┌────────────▼────────────────────┐
│     Controllers (@RestController)│  ← Endpoints REST
└────────────┬────────────────────┘
             │
┌────────────▼────────────────────┐
│     Services (@Service)         │  ← Lógica de negocio
└────────────┬────────────────────┘
             │
┌────────────▼────────────────────┐
│  Repositories (JpaRepository)   │  ← Acceso a datos
└────────────┬────────────────────┘
             │ JPA/Hibernate
┌────────────▼────────────────────┐
│     PostgreSQL Database         │
└─────────────────────────────────┘
```

### Patrones Implementados
- ✅ **Repository Pattern**: Abstracción de acceso a datos
- ✅ **Service Layer Pattern**: Lógica de negocio separada
- ✅ **MVC Pattern**: Separación de capas
- ✅ **DTO Pattern** (implícito): Uso de entidades como DTOs
- ✅ **Exception Handling**: Manejo centralizado de errores

---

## 🗄️ MODELO DE DATOS

### Diagrama de Entidades

```
┌─────────────────┐
│    Persona      │
│  (Superclass)   │
├─────────────────┤
│ nombre          │
│ genero          │
│ edad            │
│ identificacion  │
│ direccion       │
│ telefono        │
└────────┬────────┘
         │ hereda
┌────────▼────────┐         ┌─────────────────┐
│    Cliente      │1       *│     Cuenta      │
├─────────────────┤─────────├─────────────────┤
│ clienteId (PK)  │         │ id (PK)         │
│ contrasena      │         │ numeroCuenta    │
│ estado          │         │ tipoCuenta      │
└─────────────────┘         │ saldoInicial    │
                            │ estado          │
                            │ cliente_id (FK) │
                            └────────┬────────┘
                                     │1
                                     │
                                     │*
                            ┌────────▼────────┐
                            │   Movimiento    │
                            ├─────────────────┤
                            │ id (PK)         │
                            │ fecha           │
                            │ tipoMovimiento  │
                            │ valor           │
                            │ saldo           │
                            │ cuenta_id (FK)  │
                            └─────────────────┘
```

---

## 🧪 VALIDACIONES IMPLEMENTADAS

### Validación de Negocio (F3)
```java
// En MovimientoService.crear()
double nuevoSaldo = saldoActual + movimiento.getValor();

if (nuevoSaldo < 0) {
    throw new SaldoNoDisponibleException("Saldo no disponible");
}
```

### Manejo de Excepciones
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(SaldoNoDisponibleException.class)
    public ResponseEntity<Map<String, Object>> manejarSaldoNoDisponible() {
        // Retorna HTTP 400 con mensaje personalizado
    }
    
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado() {
        // Retorna HTTP 404
    }
}
```

---

## 📊 DATOS DE PRUEBA

El script `BaseDatos.sql` incluye datos de ejemplo:

### Clientes
| ID | Nombre            | Identificación | Teléfono  |
|----|-------------------|----------------|-----------|
| 1  | Jose Lema         | 1234567890     | 098254785 |
| 2  | Marianela Montalvo| 0987654321     | 097548965 |
| 3  | Juan Osorio       | 1122334455     | 098874587 |

### Cuentas
| ID | Número  | Tipo      | Saldo Inicial | Cliente |
|----|---------|-----------|---------------|---------|
| 1  | 478758  | Ahorro    | 2000          | 1       |
| 2  | 225487  | Corriente | 100           | 2       |
| 3  | 495878  | Ahorros   | 0             | 3       |
| 4  | 496825  | Ahorros   | 540           | 2       |

---

## 🐋 DESPLIEGUE EN DOCKER

### Servicios del Docker Compose
1. **postgres**: Base de datos PostgreSQL 15
2. **api**: Aplicación Spring Boot

### Comandos Docker

```bash
# Iniciar servicios
docker-compose up -d

# Ver logs
docker logs banco_api -f
docker logs banco_postgres -f

# Ver contenedores
docker ps

# Detener servicios
docker-compose down

# Reconstruir
docker-compose up --build
```

### Puertos Expuestos
- **8080**: API REST (http://localhost:8080/api)
- **5432**: PostgreSQL (localhost:5432)

---

## 📋 CHECKLIST DE REQUISITOS

### Requisitos Obligatorios para Junior ✅
- [x] Implementar clase Persona
- [x] Implementar clase Cliente (hereda de Persona)
- [x] Implementar entidad Cuenta
- [x] Implementar entidad Movimiento
- [x] F1: CRUD para Cliente, Cuenta, Movimiento
- [x] F2: Registro de movimientos con actualización de saldo
- [x] F3: Validación de saldo con mensaje de error
- [x] Uso de JPA/Hibernate
- [x] Manejo de excepciones
- [x] Endpoints REST: /clientes, /cuentas, /movimientos
- [x] Verbos HTTP: GET, POST, PUT, DELETE
- [x] Base de datos relacional (PostgreSQL)
- [x] Script BaseDatos.sql
- [x] Colección de Postman
- [x] Despliegue en Docker

### Buenas Prácticas Aplicadas ✅
- [x] Patrón Repository
- [x] Separación en capas (Controller, Service, Repository)
- [x] Uso de Lombok para reducir código boilerplate
- [x] Configuración externalizada (application.properties)
- [x] Transacciones con @Transactional
- [x] Manejo de errores centralizado
- [x] Código limpio y comentado
- [x] Documentación completa (README.md)

### Opcionales (No requerido para Junior) ❌
- [ ] F4: Reportes (no implementado - no obligatorio para Junior)
- [ ] F5: Pruebas unitarias (no implementadas - no obligatorio)
- [ ] F6: Pruebas de integración (no implementadas - no obligatorio)
- [ ] F7: Comunicación asincrónica entre microservicios (no requerido)

---

## 🎯 ENDPOINTS IMPLEMENTADOS

### Clientes
| Método | Endpoint              | Descripción                |
|--------|-----------------------|----------------------------|
| GET    | /api/clientes         | Listar todos los clientes  |
| GET    | /api/clientes/{id}    | Obtener cliente por ID     |
| POST   | /api/clientes         | Crear nuevo cliente        |
| PUT    | /api/clientes/{id}    | Actualizar cliente         |
| DELETE | /api/clientes/{id}    | Eliminar cliente           |

### Cuentas
| Método | Endpoint              | Descripción                |
|--------|-----------------------|----------------------------|
| GET    | /api/cuentas          | Listar todas las cuentas   |
| GET    | /api/cuentas/{id}     | Obtener cuenta por ID      |
| POST   | /api/cuentas          | Crear nueva cuenta         |
| PUT    | /api/cuentas/{id}     | Actualizar cuenta          |
| DELETE | /api/cuentas/{id}     | Eliminar cuenta            |

### Movimientos
| Método | Endpoint              | Descripción                |
|--------|-----------------------|----------------------------|
| GET    | /api/movimientos      | Listar todos movimientos   |
| GET    | /api/movimientos/{id} | Obtener movimiento por ID  |
| POST   | /api/movimientos      | Registrar movimiento       |
| PUT    | /api/movimientos/{id} | Actualizar movimiento      |
| DELETE | /api/movimientos/{id} | Eliminar movimiento        |

---

## 🔧 TECNOLOGÍAS UTILIZADAS

| Tecnología        | Versión | Propósito                    |
|-------------------|---------|------------------------------|
| Java              | 17      | Lenguaje de programación     |
| Spring Boot       | 3.4.1   | Framework principal          |
| Spring Data JPA   | 3.4.1   | ORM y acceso a datos         |
| PostgreSQL        | 15      | Base de datos relacional     |
| Lombok            | 1.18.36 | Reducción de código          |
| Maven             | 3.9+    | Gestión de dependencias      |
| Docker            | Latest  | Contenedores                 |
| Postman           | v9+     | Testing de API               |

---

## 📞 SOPORTE Y CONTACTO

Si tienes problemas al ejecutar el proyecto:

1. **Verificar PostgreSQL**: Asegúrate de que PostgreSQL esté corriendo
2. **Puerto 8080**: Verifica que el puerto 8080 esté libre
3. **Docker**: Asegúrate de tener Docker instalado y corriendo
4. **Maven**: Si Maven falla, usa Docker Compose

### Logs Útiles
```bash
# Ver logs de la aplicación
docker logs banco_api -f

# Ver logs de PostgreSQL
docker logs banco_postgres -f

# Acceder a la base de datos
docker exec -it banco_postgres psql -U banco_user -d banco_db
```

---

## 📦 ENTREGA FINAL

### Lo que debes entregar:
1. ✅ **Repositorio Git** con todo el código
2. ✅ **BaseDatos.sql** (incluido en el proyecto)
3. ✅ **BancoAPI-Postman.json** (incluido)
4. ✅ **README.md** con instrucciones
5. ✅ **Proyecto funcionando en Docker**

### URL del Repositorio
```
https://github.com/TU_USUARIO/banco-api-prueba-tecnica
```

---

## 🎓 NOTAS PARA LA ENTREVISTA TÉCNICA

### Puntos a defender:
1. **Arquitectura en capas**: Controller → Service → Repository
2. **Patrón Repository**: Abstracción del acceso a datos
3. **Herencia JPA**: Cliente hereda de Persona con @MappedSuperclass
4. **Manejo de excepciones**: Personalizado y centralizado
5. **Validación de negocio**: Saldo no disponible (F3)
6. **Transacciones**: Uso de @Transactional en operaciones críticas
7. **Docker**: Despliegue containerizado

### Mejoras futuras (mencionar):
- Agregar DTOs para separar modelo de presentación
- Implementar Spring Security para autenticación
- Agregar validaciones con @Valid
- Implementar pruebas unitarias y de integración
- Agregar paginación en los listados
- Implementar reportes (F4)

---

## ✨ ¡PROYECTO COMPLETADO!

Todas las funcionalidades requeridas para el nivel **Junior** han sido implementadas exitosamente.

**Fecha de entrega**: Enero 2026
**Nivel**: Backend Junior
**Estado**: ✅ COMPLETO

---

*Desarrollado siguiendo las mejores prácticas y estándares de la industria*
