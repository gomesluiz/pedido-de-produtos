package br.pucpcaldas.pedidos.dominio;

import java.util.Set;
import java.util.Date;
import java.util.HashSet;

/**
 * Um objeto da classe <code>Pedido</code> representa as informações de um
 * pedido efetuado pelo cliente. Um pedido é composto de objetos da classe
 * <code>ItemDoPedido</code>.
 *
 * @author Luiz Alberto
 * @version 1.0
 * @see ItemDoPedido
 */

public class Pedido {
	private int numero;
	private Date data;
	private Set<ItemDoPedido> itens;
	//public static Pedido Nulo = new Pedido(0);

	/**
	 * Construtor da classe.
	 * 
	 * @param numero Um número inteiro positivo que identifica um pedido.
	 */
	public Pedido(int numero) {
		if (numero < 0) {
			throw new IllegalArgumentException("O número do pedido deverá ser maior ou igual a zero!");
		}
		this.numero = numero;
		this.data = new java.util.Date();
		this.itens = new HashSet<ItemDoPedido>();
	}

	//-- Hibernate exige um construtor padrao. 
	/**
	 * Construtor padrao.
	 */
	public Pedido(){ }

	/**
	 * 
	 * @return
	 */
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Set<ItemDoPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemDoPedido> itens) {
		this.itens = itens;
	}

	/**
	 * Inclui um item no pedido.
	 * 
	 * @param produto    o produto pedido.
	 * @param quantidade a quantidade pedida.
	 */
	public void incluiItem(Produto produto, double quantidade) {
		this.itens.add(new ItemDoPedido(this, produto, quantidade));
	}

	/**
	 * Remove um item do pedido
	 * 
	 * @param produto o produto a ser removido.
	 * 
	 * @throws IllegalArgumentException se não existe um item relacionado ao
	 *                                  produto.
	 * 
	 */
	public void removeItem(Produto produto) {

		ItemDoPedido procurado = null;

		for (ItemDoPedido item : itens) {
			if (item.getProduto().getCodigo() == produto.getCodigo()) {
				procurado = item;
				break;
			}
		}

		if (procurado == null)
			throw new IllegalArgumentException("Item inexistente!");

		itens.remove(procurado);
	}

	/**
	 * Calcula total do pedido que é igual soma do custo total de cada item do
	 * pedido.
	 * 
	 * @return total do pedido.
	 */
	public double calculaTotal() {
		double total = 0.0;
		for (ItemDoPedido itemDoPedido : itens) {
			total += itemDoPedido.calculaCusto();
		}
		return total;
	}
	
	/**
	 * Calcula frete de um pedido.
	 * 
	 * @return frete
	 */
	public double calculaFrete() {

		if (itens.isEmpty())
			throw new RuntimeException("Não possível calcular o frete para um produto vazio.");

		double total = this.calculaTotal();

		if (total > 500.00)
			return 12.00;
		else if (total > 200.00)
			return 10.00;

		return 0.0;
	}

	@Override
	public String toString() {
		return String.format("Pedido %03d# data=%2$te/%2$tm/%2$tY", this.numero, this.data);
	}

}
