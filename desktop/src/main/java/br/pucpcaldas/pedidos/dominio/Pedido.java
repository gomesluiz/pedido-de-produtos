package br.pucpcaldas.pedidos.dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
	private Calendar dataDaRealizacao;
	private Collection<ItemDoPedido> itensDoPedido;

    /**
     * Construtor da classe.
     * @param numero    Um número inteiro positivo que identifica um pedido.
     */
	public Pedido(int numero) {
		if (numero < 0) {
			throw new IllegalArgumentException("O número do pedido deverá ser maior ou igual a zero!");
		}
		this.numero = numero;
		this.dataDaRealizacao = Calendar.getInstance();
		this.itensDoPedido = new ArrayList<ItemDoPedido>();
	}

    /**
     * Inclui um item no pedido.
     * 
     * @param produto       o produto pedido.
     * @param quantidade    a quantidade pedida.
     */
	public void incluiItem(Produto produto, double quantidade) {
		this.itensDoPedido.add(new ItemDoPedido(produto, quantidade));
	}

    /**
     * Calcula total do pedido que é igual soma do custo total 
     * de cada item do pedido.
     * 
     * @return  total do pedido.
     */
	public double calculaTotal() {
		double total = 0.0;
		for (ItemDoPedido itemDoPedido : itensDoPedido) {
			total += itemDoPedido.calculaCusto();
		}
		return total;
	}

	@Override
	public String toString() {
		return String.format("Pedido %03d# data=%2$te/%2$tm/%2$tY", this.numero, this.dataDaRealizacao);
	}
}
