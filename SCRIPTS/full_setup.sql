DROP DATABASE IF EXISTS inventario_db;
CREATE DATABASE inventario_db;
USE inventario_db;

-- Tabla de Roles: Almacena los roles de los usuarios (Administrador, Almacenista)
CREATE TABLE IF NOT EXISTS roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- Tabla de Usuarios: Almacena la información de los usuarios y su rol
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- Se almacenará el hash de la contraseña
    rol_id INT,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

-- Tabla de Productos: Almacena la información de los productos en el inventario
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    cantidad INT NOT NULL DEFAULT 0,
    activo BOOLEAN NOT NULL DEFAULT TRUE -- TRUE para activo, FALSE para dado de baja
);

-- Tabla de Historial de Inventario: Registra todas las transacciones
CREATE TABLE IF NOT EXISTS historial_inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    usuario_id INT NOT NULL,
    tipo_transaccion VARCHAR(50) NOT NULL, -- Ej: "Registro inicial", "Salida de material", "Ajuste de stock"
    cantidad INT NOT NULL,
    fecha_transaccion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (producto_id) REFERENCES productos(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Inserción de roles iniciales
INSERT INTO roles (nombre) VALUES ('Administrador'), ('Almacenista');

-- Inserción de usuarios de ejemplo
-- La contraseña para ambos es "password".
INSERT INTO usuarios (username, password, rol_id) VALUES 
('admin', '$2b$12$ARh4er4yqFM9C8FnOfqTPOLeJlpmV7O8OftfzNh3CxcrkYa7JxqI2', 1), -- admin con rol de Administrador
('almacen', '$2b$12$ARh4er4yqFM9C8FnOfqTPOLeJlpmV7O8OftfzNh3CxcrkYa7JxqI2', 2); -- almacen con rol de Almacenista
