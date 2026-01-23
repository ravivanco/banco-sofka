-- Script de Base de Datos para Prueba Técnica Banco
-- Base de datos: banco_db
-- Fecha: 2026-01-23

-- Tabla de Clientes (hereda de Persona)
CREATE TABLE IF NOT EXISTS clientes (
    cliente_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(10) NOT NULL,
    edad INTEGER NOT NULL,
    identificacion VARCHAR(20) UNIQUE NOT NULL,
    direccion VARCHAR(200),
    telefono VARCHAR(20),
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de Cuentas
CREATE TABLE IF NOT EXISTS cuentas (
    id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(20) NOT NULL,
    saldo_inicial DOUBLE PRECISION NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    cliente_id BIGINT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id)
);

-- Tabla de Movimientos
CREATE TABLE IF NOT EXISTS movimientos (
    id SERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    saldo DOUBLE PRECISION NOT NULL,
    cuenta_id BIGINT NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);

-- Datos de ejemplo según el documento PDF

-- Insertar Clientes (Usuarios de ejemplo)
INSERT INTO clientes (nombre, genero, edad, identificacion, direccion, telefono, contrasena, estado)
VALUES 
    ('Jose Lema', 'M', 35, '1234567890', 'Otavalo sn y principal', '098254785', '1234', TRUE),
    ('Marianela Montalvo', 'F', 28, '0987654321', 'Amazonas y NNUU', '097548965', '5678', TRUE),
    ('Juan Osorio', 'M', 42, '1122334455', '13 junio y Equinoccial', '098874587', '1245', TRUE);

-- Insertar Cuentas de ejemplo
INSERT INTO cuentas (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES 
    ('478758', 'Ahorro', 2000, TRUE, 1),
    ('225487', 'Corriente', 100, TRUE, 2),
    ('495878', 'Ahorros', 0, TRUE, 3),
    ('496825', 'Ahorros', 540, TRUE, 2);

-- Insertar Movimientos de ejemplo
INSERT INTO movimientos (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES 
    ('2022-02-10', 'Deposito de 600', 600, 700, 2),
    ('2022-02-08', 'Retiro de 540', -540, 0, 4);
