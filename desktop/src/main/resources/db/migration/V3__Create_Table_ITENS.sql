CREATE TABLE IF NOT EXISTS ITENS (
	sequencial 		INT IDENTITY NOT NULL,
	numero_pedido 	INT NOT NULL,
	quantidade 		NUMERIC(6, 2) NOT NULL,
	codigo_produto 	INT NOT NULL,
	
  	PRIMARY KEY(sequencial),
	
  	FOREIGN KEY(numero_pedido) REFERENCES PUBLIC.PEDIDOS(numero),
  	FOREIGN KEY(codigo_produto) REFERENCES PUBLIC.PRODUTOS(codigo)
 );