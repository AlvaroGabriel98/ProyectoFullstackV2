-- =========================================================
-- DOGGYSPA - SCRIPT DE BASES DE DATOS
-- Sistema de microservicios para centro de estética canina
-- MySQL / XAMPP
-- =========================================================

CREATE DATABASE IF NOT EXISTS bd_cliente
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_mascota
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_servicio
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_reserva
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_pago
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;


-- =========================================================
-- BD CLIENTE
-- =========================================================

USE bd_cliente;

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    direccion VARCHAR(150) NOT NULL
);


-- =========================================================
-- BD MASCOTA
-- =========================================================

USE bd_mascota;

CREATE TABLE IF NOT EXISTS mascota (
    id_mascota BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    raza VARCHAR(80) NOT NULL,
    edad INT NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    observacion VARCHAR(255),
    id_cliente BIGINT NOT NULL
);


-- =========================================================
-- BD SERVICIO
-- =========================================================

USE bd_servicio;

CREATE TABLE IF NOT EXISTS servicio (
    id_servicio BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    duracion_minutos INT NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);


-- =========================================================
-- BD RESERVA
-- =========================================================

USE bd_reserva;

CREATE TABLE IF NOT EXISTS reserva (
    id_reserva BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado VARCHAR(30) NOT NULL,
    observacion VARCHAR(255),
    id_mascota BIGINT NOT NULL,
    id_servicio BIGINT NOT NULL
);


-- =========================================================
-- BD PAGO
-- =========================================================

USE bd_pago;

CREATE TABLE IF NOT EXISTS pago (
    id_pago BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_pago DATE NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    metodo VARCHAR(30) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    id_reserva BIGINT NOT NULL
);


-- =========================================================
-- DATOS DE PRUEBA INICIALES
-- =========================================================

USE bd_cliente;

INSERT INTO cliente (nombre, telefono, correo, direccion)
VALUES
('Ana Pérez', '912345678', 'ana.perez@correo.cl', 'Av. Las Condes 123'),
('Carlos Soto', '923456789', 'carlos.soto@correo.cl', 'Providencia 456'),
('María González', '934567890', 'maria.gonzalez@correo.cl', 'Santiago Centro 789');


USE bd_mascota;

INSERT INTO mascota (nombre, raza, edad, peso, observacion, id_cliente)
VALUES
('Firulais', 'Poodle', 4, 6.50, 'Nervioso al bañarse', 1),
('Luna', 'Golden Retriever', 3, 24.80, 'Muy tranquila', 2),
('Toby', 'Quiltro', 5, 12.30, 'Requiere cuidado con sus orejas', 3);


USE bd_servicio;

INSERT INTO servicio (nombre, descripcion, precio, duracion_minutos, activo)
VALUES
('Lavado', 'Baño completo con shampoo especial para perros', 12000, 45, TRUE),
('Corte', 'Corte de pelo según raza y tamaño', 18000, 60, TRUE),
('Baño antipulgas', 'Baño con producto antipulgas y limpieza general', 15000, 50, TRUE),
('Corte de uñas', 'Corte y limado de uñas', 7000, 20, TRUE),
('Limpieza de oídos', 'Limpieza externa de oídos', 8000, 20, TRUE);


USE bd_reserva;

INSERT INTO reserva (fecha, hora, estado, observacion, id_mascota, id_servicio)
VALUES
('2026-06-10', '10:00:00', 'AGENDADA', 'Primera visita', 1, 1),
('2026-06-11', '12:30:00', 'AGENDADA', 'Servicio completo', 2, 2);


USE bd_pago;

INSERT INTO pago (fecha_pago, monto, metodo, estado, id_reserva)
VALUES
('2026-06-10', 12000, 'EFECTIVO', 'PAGADO', 1),
('2026-06-11', 18000, 'DEBITO', 'PENDIENTE', 2);