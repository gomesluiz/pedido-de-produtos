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
	private Produto produto;
	private double quantidade;

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

	public ItemDoPedido(){}

	public long getSequencial() {
		return sequencial;
	}

	public void setSequencial(long sequencial) {
		this.sequencial = sequencial;
	}

	public double getQuantidade() {
		return quantidade;
	}

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

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return this.pedido;
	}


	@Override
	public String toString() {
		return String.format("ItemDoPedido : produto=%s, quantidade=%.2f", this.produto, this.quantidade);
	}

}