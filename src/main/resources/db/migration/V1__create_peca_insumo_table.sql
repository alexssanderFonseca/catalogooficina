CREATE TABLE peca_insumo (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    codigo_fabricante VARCHAR(255),
    marca VARCHAR(255),
    quantidade_estoque INT NOT NULL,
    preco_custo DECIMAL(10, 2) NOT NULL,
    preco_venda DECIMAL(10, 2) NOT NULL,
    categoria VARCHAR(255),
    ativo BOOLEAN NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP
);