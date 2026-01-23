# 🚀 INICIO RÁPIDO - Prueba Técnica Banco API

## ⚡ Ejecutar en 3 pasos (RECOMENDADO)

### Paso 1: Compilar
```bash
cd api
./mvnw clean compile
```

### Paso 2: Iniciar con Docker
```bash
docker-compose up --build
```

### Paso 3: Probar en Postman
- Importar `BancoAPI-Postman.json` en Postman
- URL Base: `http://localhost:8080/api`
- Ejecutar las peticiones

---

## 🎯 URLs Principales

| Servicio    | URL                               |
|-------------|-----------------------------------|
| API         | http://localhost:8080/api         |
| PostgreSQL  | localhost:5432                    |

---

## 📝 Ejemplos Rápidos con cURL

### Crear Cliente
```bash
curl -X POST http://localhost:8080/api/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Jose Lema",
    "genero": "M",
    "edad": 35,
    "identificacion": "1234567890",
    "direccion": "Otavalo sn y principal",
    "telefono": "098254785",
    "contrasena": "1234",
    "estado": true
  }'
```

### Listar Clientes
```bash
curl http://localhost:8080/api/clientes
```

### Crear Cuenta
```bash
curl -X POST http://localhost:8080/api/cuentas \
  -H "Content-Type: application/json" \
  -d '{
    "numeroCuenta": "478758",
    "tipoCuenta": "Ahorro",
    "saldoInicial": 2000,
    "estado": true,
    "cliente": {
      "clienteId": 1
    }
  }'
```

### Registrar Retiro
```bash
curl -X POST http://localhost:8080/api/movimientos \
  -H "Content-Type: application/json" \
  -d '{
    "valor": -575,
    "cuenta": {
      "id": 1
    }
  }'
```

### Probar Saldo Insuficiente (debe fallar)
```bash
curl -X POST http://localhost:8080/api/movimientos \
  -H "Content-Type: application/json" \
  -d '{
    "valor": -10000,
    "cuenta": {
      "id": 1
    }
  }'
```

---

## 🐛 Solución de Problemas

### Puerto 8080 ocupado
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Cambiar puerto en application.properties
server.port=8081
```

### PostgreSQL no se conecta
```bash
# Verificar que PostgreSQL esté corriendo
docker ps

# Ver logs
docker logs banco_postgres -f

# Reiniciar contenedores
docker-compose down
docker-compose up --build
```

### Ver logs de la API
```bash
docker logs banco_api -f
```

---

## 📂 Archivos Importantes

| Archivo                    | Descripción                          |
|----------------------------|--------------------------------------|
| `GUIA_ENTREGA.md`          | 📋 Guía completa de entrega          |
| `README.md`                | 📖 Documentación técnica             |
| `BaseDatos.sql`            | 🗄️ Script de base de datos          |
| `BancoAPI-Postman.json`    | 📮 Colección de Postman              |
| `docker-compose.yml`       | 🐋 Configuración Docker              |

---

## ✅ Verificar que todo funciona

1. ✅ Contenedores corriendo: `docker ps`
2. ✅ API responde: `curl http://localhost:8080/api/clientes`
3. ✅ PostgreSQL conectado: Revisar logs
4. ✅ Crear cliente, cuenta, movimiento
5. ✅ Probar saldo insuficiente (debe retornar error 400)

---

## 🎓 Para la Entrevista

### Habla sobre:
1. **Patrón Repository** - Abstracción de datos
2. **Herencia JPA** - Cliente hereda de Persona
3. **Manejo de Excepciones** - Centralizado con @RestControllerAdvice
4. **Validación de Negocio** - Saldo no disponible (F3)
5. **Arquitectura en Capas** - Controller → Service → Repository

### Demuestra:
- ✅ CRUD completo funcionando
- ✅ Registro de movimientos
- ✅ Validación de saldo insuficiente
- ✅ Manejo de errores con mensajes claros
- ✅ Despliegue en Docker

---

**¡Listo para entregar! 🎉**
