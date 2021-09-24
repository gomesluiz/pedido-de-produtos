package br.pucpcaldas.pedidos.controle;

import br.pucpcaldas.pedidos.dominio.Produto;

public interface DepositoDeProdutos {
	void adiciona(Produto produto);
	void atualiza(Produto produto);
	void remove(Produto produto);
	Produto listaPor(int codigo);
	void fecha();
}
