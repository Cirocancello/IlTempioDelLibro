DROP DATABASE IF EXISTS iltempiodeldigitale;
CREATE DATABASE iltempiodeldigitale;
USE teraware;

CREATE TABLE utente (
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome TEXT NOT NULL,
	cognome TEXT NOT NULL,
	email VARCHAR(500) NOT NULL UNIQUE,
	_password TEXT,
	oauth BOOLEAN NOT NULL,
	propic_url TEXT DEFAULT 'https://via.placeholder.com/300x225',
	provincia TEXT NOT NULL,
	cap CHAR(5) NOT NULL,
	via TEXT NOT NULL,
	civico TEXT NOT NULL,
	note TEXT,
	_role TINYINT DEFAULT 0
);

CREATE TABLE ordine (
	id VARCHAR(36) PRIMARY KEY,
	id_utente INT NOT NULL,
	_data TIMESTAMP NOT NULL,
	stato TEXT NOT NULL,
	FOREIGN KEY (id_utente) REFERENCES utente(id)
);

CREATE TABLE categoria (
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome TEXT NOT NULL
);

CREATE TABLE prodotto (
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome TEXT NOT NULL,
	brand TEXT NOT NULL,
	prezzo FLOAT NOT NULL,
	quantita INT NOT NULL CHECK (quantita >= 0),
	image_url TEXT DEFAULT 'https://via.placeholder.com/300x225',
	informazioni TEXT,
	visibile BOOLEAN DEFAULT TRUE
);

CREATE TABLE categoria_prodotto (
	id_categoria INT,
	id_prodotto INT,
	PRIMARY KEY (id_categoria, id_prodotto),
	FOREIGN KEY (id_categoria) REFERENCES categoria(id),
	FOREIGN KEY (id_prodotto) REFERENCES prodotto(id)
);

CREATE TABLE feedback (
	id INT PRIMARY KEY AUTO_INCREMENT,
	id_prodotto INT NOT NULL,
	score TINYINT NOT NULL,
	titolo TEXT,
	descrizione TEXT,
	_data TIMESTAMP NOT NULL,
	FOREIGN KEY (id_prodotto) REFERENCES prodotto(id)
);

CREATE TABLE prodotto_ordine (
	id INT PRIMARY KEY AUTO_INCREMENT,
	id_prodotto INT NOT NULL,
	id_ordine VARCHAR(36) NOT NULL,
	prezzo_effettivo FLOAT NOT NULL,
	iva FLOAT NOT NULL,
	quantita INT NOT NULL CHECK (quantita > 0),
	FOREIGN KEY (id_prodotto) REFERENCES prodotto(id),
	FOREIGN KEY (id_ordine) REFERENCES ordine(id)
);

CREATE TABLE societa (
	p_iva VARCHAR(20) PRIMARY KEY,
	nome TEXT NOT NULL,
	email VARCHAR(500) NOT NULL
