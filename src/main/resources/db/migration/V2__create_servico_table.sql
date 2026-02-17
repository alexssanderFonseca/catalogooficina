CREATE TABLE servico (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    preco DECIMAL(10, 2) NOT NULL,
    duracao_estimada INT,
    categoria VARCHAR(255),
    ativo BOOLEAN NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP
);