package Loja;

import java.io.Serializable;

public class Item implements Serializable{
    private final Produto p;
    private int quantidade;

    /**
     *
     * @param p produto do conjunto
     * @param quantidade quantidade desse produto
     */
    public Item(Produto p,int quantidade){
        this.p = p;
        this.quantidade = quantidade;
        p.retiraStock(quantidade);
    }

    /**
     * Get method
     * @return produto
     */
    public Produto getProduto(){
        return p;
    }

    /**
     * Get method
     * @return quantidade
     */
    public int getQuantidade(){
        return quantidade;
    }

    public String fatura(){
        return p.getIdentificador() + "  " + p.getNome()  + " " + quantidade + " " + conta() + "€";
    }

    /**
     * Metodo que aumenta a quantidade de um dado produto se o cliente comprar duas vezes o mesmo produto
     * @param q quantidade a aumentar
     */
    public void addQuantidade(int q){
        quantidade += q;
    }

    /**
     *
     * @return calcula o total desse conjunto de produtos, quer tenha promocao, quer nao tenha
     */
    public double conta(){ //TODO pensar noutro nome para esta funcao
        if(p.getPromocao() == null) return (p.getPreco() * quantidade);
        else {
            double pDesconto;
            if((pDesconto = p.getPromocao().percentagemDesconto(quantidade)) != 0){
                return (p.getPreco() * quantidade)*pDesconto;
            }
            else{
                return p.getPreco() * (quantidade - p.getPromocao().nProdutosGratis(quantidade));
            }
        }
    }

}