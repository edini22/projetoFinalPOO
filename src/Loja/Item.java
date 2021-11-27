package Loja;

import java.io.Serializable;

public class Item implements Serializable{
    private Produto p;
    private int quantidade;
    
    public Item(Produto p,int quantidade){
        this.p = p;
        this.quantidade = quantidade;
        p.retiraStock(quantidade);
    }

    public Produto getProduto(){
        return p;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public String fatura(){
        return p.getIdentificador() + "  " + p.getNome()  + " " + quantidade + " " + conta() + "€";
    }

    public void addQuantidade(int q){
        quantidade += q;
    }

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