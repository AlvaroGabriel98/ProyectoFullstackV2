-- =========================================================
-- RESPAWNCONSOLAS- SCRIPT DE BASES DE DATOS
-- MySQL / XAMPP
-- =========================================================

CREATE DATABASE IF NOT EXISTS bd_cliente
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_catalogo
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_notificaciones
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_pago
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;


CREATE DATABASE IF NOT EXISTS bd_pedidos
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;


CREATE DATABASE IF NOT EXISTS bd_stock
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
-- =========================================================
-- BD CLIENTE
-- =========================================================

USE bd_cliente;

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(150) NOT NULL,
    city VARCHAR(150) NOT NULL,
    country VARCHAR(150) NOT NULL
);


-- =========================================================
-- BD MASCOTA
-- =========================================================

USE bd_catalogo;

CREATE TABLE IF NOT EXISTS catalogo (
    id_consola BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100) NOT NULL,
    almacenamiento VARCHAR(50) NOT NULL,
    precio DECIMAL(5,2) NOT NULL,
    stock  DECIMAL(5,2) not null
);



-- =========================================================
-- DATOS DE PRUEBA INICIALES
-- =========================================================

USE bd_cliente;

INSERT INTO cliente (id_cliente,first_name,last_name,phone,address,city,country)
VALUES
('01','Alvaro','Olate','956789123','calle falsa 23','Santiago','Chile');


USE bd_catalogo;

INSERT INTO catalogo (id,nombre,fabricante,alamacenamiento,precio,stock)
VALUES
(01,'Playstation 5','Sony','1TB',599000,20);


