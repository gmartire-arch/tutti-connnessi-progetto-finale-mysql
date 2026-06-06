CREATE DATABASE IF NOT EXISTS gestione_biblioteca;
USE gestione_biblioteca;

-- 1. TABELLA PADRE
CREATE TABLE risorse_biblioteca (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titolo VARCHAR(150) NOT NULL,
    disponibile BOOLEAN DEFAULT TRUE,
    tipo_risorsa VARCHAR(20) NOT NULL -- 'LIBRO' o 'DVD'
);

-- 2. TABELLA FIGLIA: LIBRI
CREATE TABLE libri (
    id INT PRIMARY KEY,
    autore VARCHAR(100) NOT NULL,
    FOREIGN KEY (id) REFERENCES risorse_biblioteca(id) ON DELETE CASCADE
);

-- 3. TABELLA FIGLIA: DVD
CREATE TABLE dvd (
    id INT PRIMARY KEY,
    durata_minuti INT NOT NULL,
    FOREIGN KEY (id) REFERENCES risorse_biblioteca(id) ON DELETE CASCADE
);

-- 4. TABELLA UTENTI
CREATE TABLE utenti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cognome VARCHAR(100) NOT NULL,
    codice_tessera VARCHAR(50) UNIQUE NOT NULL
);

-- 5. TABELLA PRESTITI (Punta a risorse_biblioteca)
CREATE TABLE prestiti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_risorsa INT NOT NULL,
    id_utente INT NOT NULL,
    data_inizio DATE NOT NULL,
    data_restituzione DATE NULL,
    FOREIGN KEY (id_risorsa) REFERENCES risorse_biblioteca(id) ON DELETE CASCADE,
    FOREIGN KEY (id_utente) REFERENCES utenti(id) ON DELETE CASCADE
);