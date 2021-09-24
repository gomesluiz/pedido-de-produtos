package br.pucpcaldas.pedidos.persistencia;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.pucpcaldas.pedidos.controle.DepositoDeProdutos;
import br.pucpcaldas.pedidos.dominio.Produto;

public class TestaDepositoDeProdutos {

	private static DepositoDeProdutos deposito;
	private static Produto caneta, lapis;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		deposito = new DepositoDeProdutosEmBancoDeDados();
		caneta = new Produto(1, "Caneta", 2.50);
		lapis = new Produto(2, "Lapis", 1.50);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		deposito.fecha();
	}

	@Test
	public void adiciona_InsereUmProdutoNoBancoDeDados() {
		// Arranjo
		Produto umaCaneta = caneta;

		// Acao
		deposito.adiciona(umaCaneta);

		// Assercao
		Produto outraCaneta = deposito.listaPor(1);
		assertProduto(umaCaneta, outraCaneta);
	}

	@Test
	public void atualiza_AtualizaUmProdutoNoBancoDeDados() {
		// Arranjo
		Produto umaCaneta = caneta;
		// Acao
		deposito.adiciona(umaCaneta);
		umaCaneta.setNome("Caneta Azul");
		umaCaneta.setPreco(2.00);
		deposito.atualiza(umaCaneta);

		// Assercao
		Produto outraCaneta = deposito.listaPor(1);
		assertProduto(umaCaneta, outraCaneta);
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

	private void assertProduto(Produto produtoAntes, Produto produtoDepois) {
		assertEquals(produtoAntes.getCodigo(), produtoDepois.getCodigo());
		assertEquals(produtoAntes.getNome(), produtoDepois.getNome());
		assertEquals(produtoAntes.getPreco(), produtoDepois.getPreco(), 0.01);
	}

}
