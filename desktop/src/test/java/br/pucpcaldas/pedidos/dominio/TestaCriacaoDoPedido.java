package br.pucpcaldas.pedidos.dominio;

import org.junit.Test;

public class TestaCriacaoDoPedido {

	@Test(expected = IllegalArgumentException.class)
	public void criaUmPedidoComNumeroNegativo() {
		@SuppressWarnings("unused")
		Pedido pedido = new Pedido(-1);
	}
}
