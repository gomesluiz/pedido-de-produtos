package br.pucpcaldas.pedidos.dominio;

/**
 * Um objeto da classe <code>ItemDoPedido</code> armazena as informações de um
 * item associado a um objeto da classe <code>Pedido</code>.
 *
 * @author Luiz Alberto
 * @version 1.0
 * @see Pedido
 */
public class ItemDoPedido {
	private long sequencial;
	
	//-- referencia a classe Produto
	private Produto produto;

	private double quantidade;

	//--- referencia a classe Pedido
	private Pedido pedido;

	/**
	 * Construtor da classe.
	 * 
	 * @param produto    o produto associado ao item.
	 * @param quantidade a quantidade de produtos do item.
	 */
	public ItemDoPedido(Pedido pedido, Produto produto, double quantidade) {
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	//-- Hibernate exige um construtor padrao. 
	/**
	 * Construtor padrao.
	 */
	public ItemDoPedido(){}


	//-- Hibernate exige o par get/set para todos o atributos
	//-- que serao persistidos.
	/**
	 * Retorna o sequencial do item do pedido.
	 * @return
	 */
	public long getSequencial() {
		return sequencial;
	}

	/**
	 * Modifica o sequencial do item do pedido.
	 * @param sequencial
	 */
	public void setSequencial(long sequencial) {
		this.sequencial = sequencial;
	}

	/**
	 * Retorna a quantidade do item do pedido.
	 * @return
	 */
	public double getQuantidade() {
		return quantidade;
	}

	/**
	 * Modifica a quantidade do item do pedido.
	 * @param quantidade
	 */
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Calcula o custo total do item que é igual ao preco do produto multiplicado
	 * pela quantidade comprada.
	 * 
	 * @return
	 */
	public double calculaCusto() {
		return this.produto.getPreco() * this.quantidade;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	 * Retorna o produto associado ao pedido.
	 * 
	 * @return produto
	 */
	public Produto getProduto() {
		return this.produto;
	}

	/**
	 * Modifica o pedido associado ao item do pedido.
	 * @param pedido
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	/**
	 * Retorna o pedido associado ao item do pedido.
	 * @return
	 */
	public Pedido getPedido() {
		return this.pedido;
	}

	@Override
	public String toString() {
		return String.format("ItemDoPedido : produto=%s, quantidade=%.2f", this.produto, this.quantidade);
	}

}