package br.pucpcaldas.pedidos.persistencia;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.pucpcaldas.pedidos.controle.DepositoDePedidos;
import br.pucpcaldas.pedidos.controle.DepositoDeProdutos;
import br.pucpcaldas.pedidos.dominio.Pedido;
import br.pucpcaldas.pedidos.dominio.ItemDoPedido;
import br.pucpcaldas.pedidos.dominio.Produto;


public class TestaDepositoDePedidos {

	private static DepositoDePedidos pedidos;
	private static DepositoDeProdutos produtos;
	
	private static Produto caneta, lapis;
	private static Pedido umPedido;
	private static Date dataEsperada;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pedidos 	= new DepositoDePedidosComHibernate();
		produtos 	= new DepositoDeProdutosComHibernate();
		
		
		caneta = new Produto(1, "Caneta", 2.50);
		lapis = new Produto(2, "Lapis", 1.50);
		
		umPedido = new Pedido(10);
		dataEsperada = umPedido.getData();

		// insere os produtos do banco de dados
		produtos.adiciona(caneta);
		produtos.adiciona(lapis);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// limpa os pedidos do banco de dados
		pedidos.remove(umPedido);

		// limpa os produtos do banco de dados
		produtos.remove(caneta);
		produtos.remove(lapis);

		// fecha a conexão com o banco de dados.
		produtos.fecha();
		pedidos.fecha();
	}

	@Test
	public void adiciona_InsereUmPedidoNoBancoDeDados() {
		// Arranjo
		umPedido.incluiItem(caneta, 10.0);
		umPedido.incluiItem(lapis, 10.0);
		double totalEsperado = umPedido.calculaTotal();
		List<ItemDoPedido> esperados = new ArrayList<ItemDoPedido>(umPedido.getItens());
		
		// Acao
		pedidos.adiciona(umPedido);

		// Assercao
		Pedido outroPedido = pedidos.listaPor(10);
		assertEquals(10, outroPedido.getNumero());
		assertEquals(dataEsperada,  outroPedido.getData());
		assertEquals(totalEsperado, outroPedido.calculaTotal(), 0.01);
		for (ItemDoPedido item:outroPedido.getItens())
		{
			ItemDoPedido esperado = esperados.get((int)item.getSequencial());
			assertEquals(esperado.getSequencial(), item.getSequencial());
			assertEquals(esperado.getQuantidade(), item.getQuantidade(), 0.01);
		}
	}
}
