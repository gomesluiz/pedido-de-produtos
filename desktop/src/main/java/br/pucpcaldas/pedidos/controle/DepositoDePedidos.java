package br.pucpcaldas.pedidos.controle;

import br.pucpcaldas.pedidos.dominio.Pedido;

public interface DepositoDePedidos {
	void adiciona(Pedido produto);
	void atualiza(Pedido produto);
	void remove(Pedido produto);
	Pedido listaPor(int codigo);
	void fecha();
}
