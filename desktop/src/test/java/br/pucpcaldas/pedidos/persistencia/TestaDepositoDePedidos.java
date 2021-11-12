package br.pucpcaldas.pedidos.persistencia;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.pucpcaldas.pedidos.controle.DepositoDePedidos;
import br.pucpcaldas.pedidos.controle.DepositoDeProdutos;
import br.pucpcaldas.pedidos.dominio.Pedido;
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

		// limpa os produtos do banco de dados
		produtos.remove(caneta);
		produtos.remove(lapis);
		
		// limpa os pedidos do banco de dados
		pedidos.remove(umPedido);

		// insere os produtos do banco de dados
		produtos.adiciona(caneta);
		produtos.adiciona(lapis);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		produtos.fecha();
		pedidos.fecha();
	}

	@Test
	public void adiciona_InsereUmPedidoNoBancoDeDados() {
		// Arranjo
		umPedido.incluiItem(caneta, 10.0);
		umPedido.incluiItem(lapis, 10.0);
		double totalEsperado = umPedido.calculaTotal();
		
		// Acao
		pedidos.adiciona(umPedido);

		// Assercao
		Pedido outroPedido = pedidos.listaPor(10);
		assertEquals(10, outroPedido.getNumero());
		assertEquals(dataEsperada,  outroPedido.getData());
		assertEquals(totalEsperado, outroPedido.calculaTotal(), 0.01);
		
		//assertProduto(umaCaneta, outraCaneta);
	}
/*
	@Test
	public void atualiza_AtualizaUmPedidoNoBancoDeDados() {
		// Arranjo
		
		// Acao
		deposito.adiciona(umaCaneta);
		umaCaneta.setNome("Caneta Azul");
		umaCaneta.setPreco(2.00);
		deposito.atualiza(umaCaneta);

		// Assercao
	
	}

	@Test
	public void remove_RemoveUmProdutoNoBancoDeDados() {
		// Arranjo
		Produto umaCaneta = caneta;

		// Acao
		deposito.adiciona(umaCaneta);
		deposito.remove(umaCaneta);

		// Assercao
		Produto outraCaneta = deposito.listaPor(1);
		assertProduto(Produto.Nulo, outraCaneta);
	}

	@Test
	public void lista_RetornaNuloParaUmProdutoQueNaoExiste() {
		// Arranjo

		// Acao
		Produto umaCaneta = deposito.listaPor(-1);

		// Assercao

		assertProduto(Produto.Nulo, umaCaneta);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		deposito.remove(caneta);
		deposito.remove(lapis);
	}
*/
	private void assertProduto(Produto produtoAntes, Produto produtoDepois) {
		assertEquals(produtoAntes.getCodigo(), produtoDepois.getCodigo());
		assertEquals(produtoAntes.getNome(), produtoDepois.getNome());
		assertEquals(produtoAntes.getPreco(), produtoDepois.getPreco(), 0.01);
	}
}
