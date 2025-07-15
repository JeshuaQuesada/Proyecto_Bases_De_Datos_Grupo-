-- Validamos que este el usuario y lo creamos todo poderoso.
DROP SCHEMA IF EXISTS hotel;
DROP USER IF EXISTS usuario_hotel;
CREATE SCHEMA hotel;
CREATE USER 'usuario_hotel'@'%' IDENTIFIED BY 'Hotel_Clave.';
GRANT ALL PRIVILEGES ON hotel.* TO 'usuario_hotel'@'%';
FLUSH PRIVILEGES;
USE hotel;

-- creamos la tabla para las reservas 
CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    cedula VARCHAR(20),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    tipo_habitacion varchar(100) NOT NULL,
    activo boolean default true
);

--Datos de ejemplo
INSERT INTO reservas (nombre, apellido, cedula, telefono, correo, tipo_habitacion, activo) VALUES
('Ana', 'García', '12345678', '111222333', 'ana.garcia@example.com', 'Estandar', TRUE),
('Carlos', 'López', '87654321', '444555666', 'carlos.lopez@example.com', 'Suite', TRUE),
('Laura', 'Martínez', '98761234', '777888999', 'laura.martinez@example.com', 'Suite', TRUE),
('Pedro', 'Rodríguez', '43219876', '101112131', 'pedro.rodriguez@example.com', 'Premium', TRUE),
('Sofía', 'Pérez', '11223344', '141516171', 'sofia.perez@example.com', 'Estandar', TRUE),
('Diego', 'Sánchez', '44332211', '181920212', 'diego.sanchez@example.com', 'Premium', TRUE);

--Creamos la tabla de usuarios
CREATE TABLE usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(512) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  apellido VARCHAR(30) NOT NULL,
  correo VARCHAR(75) NULL,
  telefono VARCHAR(15) NULL,
  activo boolean default true,
  PRIMARY KEY (id_usuario))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

--Tabla de roles
CREATE TABLE role (  
  rol varchar(20),
  PRIMARY KEY (rol)  
);

INSERT INTO role (rol) VALUES ('ADMIN'), ('USER');

--le ponemos los roles a los usuarios
CREATE TABLE rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(20),
  id_usuario INT,
  PRIMARY KEY (id_rol)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

--Ignorar
INSERT INTO rol (id_rol, nombre, id_usuario) VALUES
 (1, 'ADMIN', 1), 
 (2, 'USER', 1),
 (3, 'USER', 2);

--Ingnorar
INSERT INTO usuario (id_usuario, username, password, nombre, apellido, correo, telefono, activo) VALUES 
(1, 'juan', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.', 'Juan', 'Castro Mora', 'jcastro@gmail.com', '4556-8978', true),
(2, 'rebeca', '$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi', 'Rebeca', 'Contreras Mora', 'acontreras@gmail.com', '5456-8789', true);
