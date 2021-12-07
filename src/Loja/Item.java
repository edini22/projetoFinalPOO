package Loja;

import java.io.Serializable;

/**
 * Classe auxiliar que contem um produto e a quantidade desse protudo
 */
public class Item implements Serializable {
    private Produto p;
    private int quantidade;

    /**
     * Construtor
     * 
     * @param p          produto do conjunto
     * @param quantidade quantidade desse produto
     */
    public Item(Produto p, int quantidade) {
        this.p = p;
        this.quantidade = quantidade;
        p.retiraStock(quantidade);
    }

    /**
     * Get method
     * 
     * @return Produto
     */
    public Produto getProduto() {
        return p;
    }

    /**
     * Get method
     * 
     * @return Quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * 
     * @return String formatada com os atributos de Item
     */
    public String fatura() {
        return p.getIdentificador() + "  " + p.getNome() + " " + quantidade + " " + conta() + "€";
    }

    /**
     * Metodo que aumenta a quantidade de um dado produto se o cliente comprar duas
     * vezes o mesmo produto
     * 
     * @param q quantidade a aumentar
     */
    public void addQuantidade(int q) {
        quantidade += q;
    }

    /**
     * Calcula o preco total desse conjunto de produtos, quer tenha ou nao promocao
     */
    public double conta() {
        if (p.getPromocao() == null)
            return (p.getPreco() * quantidade);
        else {
            double pDesconto;
            if ((pDesconto = p.getPromocao().percentagemDesconto(quantidade)) != 0) {
                return (p.getPreco() * quantidade) * (1 - pDesconto);
            } else {
                return p.getPreco() * (quantidade - p.getPromocao().nProdutosGratis(quantidade));
            }
        }
    }

}