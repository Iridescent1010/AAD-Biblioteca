CREATE DATABASE BIBLIOTECA;
USE BIBLIOTECA;

DROP TABLE IF EXISTS categoria;
CREATE TABLE categoria (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    categoria TEXT
);

DROP TABLE IF EXISTS libro;
CREATE TABLE libro (
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        nombre TEXT,
        autor TEXT,
        editorial TEXT,
        categoria INTEGER,
        FOREIGN KEY(categoria) REFERENCES categoria(id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        nombre TEXT,
        apellidos TEXT
);

DROP TABLE IF EXISTS prestamos;
CREATE TABLE prestamos (
        idPrestamo INTEGER PRIMARY KEY AUTO_INCREMENT,
        idLibro INTEGER,
        idUsuario INTEGER,
        fechaPrestamo DATETIME,
        FOREIGN KEY(idLibro) REFERENCES libro(id) ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY(idUsuario) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS historico;
CREATE TABLE historico (
        idHistorico INTEGER PRIMARY KEY AUTO_INCREMENT,
        user TEXT,
        fecha DATETIME,
        info TEXT
);