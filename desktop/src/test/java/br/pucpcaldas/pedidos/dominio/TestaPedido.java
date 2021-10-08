package br.pucpcaldas.pedidos.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestaPedido {

	private static Produto caneta;
	private static Produto lapis;

	@BeforeClass
	public static void configuraTeste() {
		caneta = new Produto(1, "Caneta", 1.50);
		lapis = new Produto(2, "Lápis", 1.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void criaUmPedidoComNumeroNegativo() {
		@SuppressWarnings("unused")
		Pedido pedido = new Pedido(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeItem_DeveRetornarUmExcecaoParaProdutoNaoEncontrado() {
		Pedido pedido = new Pedido(1);

		pedido.removeItem(caneta);
	}

	@Test
	public void removeItem_DeveRemoverOItemRelacionadoAoProduto() {
		Pedido pedido = new Pedido(1);

		pedido.incluiItem(caneta, 3);

		pedido.removeItem(caneta);

		assertEquals(0.0, pedido.calculaTotal(), 0.01);
	}

	@Test
	public void removeItem_DeveRemoverOItemRelacionadoAoProdutoComDoisItens() {
		Pedido pedido = new Pedido(1);

		pedido.incluiItem(caneta, 3);
		pedido.incluiItem(lapis, 1);

		pedido.removeItem(caneta);

		assertEquals(1.00, pedido.calculaTotal(), 0.01);
	}

	@Test(expected = RuntimeException.class)
	public void calculafrete_DeveRetornarUmExcecaoParaUmPedidoVazio() {
		Pedido pedido = new Pedido(1);

		pedido.calculaFrete();
	}

	@Test
	public void calculafrete_DeveRetornarZeroParaPedidoComValorMenorOuIgualA200() {
		Pedido pedido = new Pedido(1);

		pedido.incluiItem(caneta, 10);

		assertEquals(0.0, pedido.calculaFrete(), 0.01);
	}

	@Test
	public void calculafrete_DeveRetornar10ParaPedidoComValorMenorOuIgualA500() {
		Pedido pedido = new Pedido(1);

		pedido.incluiItem(caneta, 200);

		assertEquals(10.0, pedido.calculaFrete(), 0.01);
	}

	@Test
	public void calculafrete_DeveRetornar12ParaPedidoComValorMaiorQue500() {
		Pedido pedido = new Pedido(1);

		pedido.incluiItem(caneta, 300);
		pedido.incluiItem(lapis, 100);

		assertEquals(12.0, pedido.calculaFrete(), 0.01);
	}

}
