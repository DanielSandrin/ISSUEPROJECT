CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE clientes (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cidade VARCHAR(250) NOT NULL,
    uf CHAR(2) NOT NULL
);

CREATE TABLE chamados (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    descricao VARCHAR(500) NOT NULL,
    prioridade INT NOT NULL,
    dataAbertura DATE NOT NULL,
    dataFinalizacao DATE,
    cliente_id UUID,
    FOREIGN KEY(cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

CREATE TABLE imagens (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome TEXT NOT NULL,
    chamado_id UUID,
    FOREIGN KEY(chamado_id) REFERENCES chamados(id) ON DELETE CASCADE
);