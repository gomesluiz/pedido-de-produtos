CREATE TABLE IF NOT EXISTS PRODUTOS(
    codigo  INT             NOT NULL,
    nome    VARCHAR(30)     NOT NULL,
    preco   NUMERIC(6, 2)   NOT NULL, 
    PRIMARY KEY(codigo)
)