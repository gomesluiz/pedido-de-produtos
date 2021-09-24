package br.pucpcaldas.pedidos.dominio;

/**
 * Um objeto da classe <code>Produto</code> representa as informações de um
 * produto.
 *
 * @author Luiz Alberto
 * @version 1.0
 *
 */
public class Produto {
    private int codigo;
    private String nome;
    private double preco;

    public static Produto Nulo = new Produto(0, "", 0);

    /**
     * Método construtor.
     *
     * @param codigo código do produto
     * @param nome   nome do produto
     * @param preco  preço unitário do produto
     */
    public Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Retorna o código do produto.
     *
     * @return código do produto
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return preço do produto
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Atualiza o nome do produto.
     *
     * @return nome do produto
     */
    public void setNome(String nome) {
        this.nome=nome;
    }

    /**
     * Atualiza o preço do produto.
     *
     * @return preço do produto
     */
    public void setPreco(double preco) {
        this.preco=preco;
    }

    @Override
    public String toString() {
        return String.format("Produto=%03d#, nome=%s, preco=%.2f", this.codigo,
            this.nome, this.preco);
    }
}
