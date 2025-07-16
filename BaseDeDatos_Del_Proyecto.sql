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
INSERT INTO reservas (nombre, apellido, cedula, telefono, correo, tipo_habitacion, activo) VALUES('Ana', 'García', '12345678', '111222333', 'ana.garcia@example.com', 'Estandar', TRUE);
INSERT INTO reservas (nombre, apellido, cedula, telefono, correo, tipo_habitacion, activo) VALUES('Carlos', 'López', '87654321', '444555666', 'carlos.lopez@example.com', 'Suite', TRUE);
INSERT INTO reservas (nombre, apellido, cedula, telefono, correo, tipo_habitacion, activo) VALUES('Laura', 'Martínez', '98761234', '777888999', 'laura.martinez@example.com', 'Suite', TRUE);
INSERT INTO reservas (nombre, apellido, cedula, telefono, correo, tipo_habitacion, activo) VALUES('Pedro', 'Rodríguez', '43219876', '101112131', 'pedro.rodriguez@example.com', 'Premium', TRUE);
INSERT INTO reservas (nombre, apellido, cedula, telefono, correo, tipo_habitacion, activo) VALUES('Sofía', 'Pérez', '11223344', '141516171', 'sofia.perez@example.com', 'Estandar', TRUE);
INSERT INTO reservas (nombre, apellido, cedula, telefono, correo, tipo_habitacion, activo) VALUES('Diego', 'Sánchez', '44332211', '181920212', 'diego.sanchez@example.com', 'Premium', TRUE);

--Creamos la tabla de usuarios
CREATE TABLE usuario (
  id_usuario INT NOT NULL,
  username VARCHAR2(20) NOT NULL,
  password VARCHAR2(512) NOT NULL,
  nombre VARCHAR2(20) NOT NULL,
  apellido VARCHAR2(30) NOT NULL,
  correo VARCHAR2(75),
  telefono VARCHAR2(15),
  activo NUMBER(1) DEFAULT 1, -- 1 = true, 0 = false
  PRIMARY KEY (id_usuario)
)

--Tabla de roles
CREATE TABLE role (  
  rol varchar(20),
  PRIMARY KEY (rol)  
);

INSERT INTO role (rol) VALUES ('ADMIN'), ('USER');

--le ponemos los roles a los usuarios
CREATE TABLE rol (
  id_rol INT NOT NULL,
  nombre VARCHAR2(20),
  id_usuario INT,
  PRIMARY KEY (id_rol)
);

--Ignorar
INSERT INTO rol (id_rol, nombre, id_usuario) VALUES (1, 'ADMIN', 1);
INSERT INTO rol (id_rol, nombre, id_usuario) VALUES  (2, 'USER', 1);
INSERT INTO rol (id_rol, nombre, id_usuario) VALUES  (3, 'USER', 2);

--Ingnorar
INSERT INTO usuario (id_usuario, username, password, nombre, apellido, correo, telefono, activo) VALUES (1, 'juan', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.', 'Juan', 'Castro Mora', 'jcastro@gmail.com', '4556-8978', 1);
INSERT INTO usuario (id_usuario, username, password, nombre, apellido, correo, telefono, activo) VALUES (2, 'rebeca', '$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi', 'Rebeca', 'Contreras Mora', 'acontreras@gmail.com', '5456-8789', 1);

SELECT U.username, R.nombre
FROM USUARIO U
JOIN ROL R ON U.id_usuario = R.id_usuario;




