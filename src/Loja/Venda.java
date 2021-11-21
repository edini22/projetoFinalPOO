package Loja;
import java.util.*;

public class Venda {
    private Cliente consumidor;
    private List<Produto> produtos;
    private Promocao promocao;

    public Venda(Cliente consumidor) {
        this.consumidor = consumidor;
        produtos = new ArrayList<>();
    }

    public void adicionaProduto(Produto p) {
        produtos.add(p);
    }
    
    private String listaProdutos(){
        String lista = "";
        for(Produto produto : produtos){
            lista += "\t >" + produto.fatura() + "\n";
        }
        return lista;
    }

    public String toString(){
        return "Cliente: " + consumidor + "\nCesto de Compras:\n" + listaProdutos();
    }

    public double total(){
        double total = 0;
        for(Produto p: produtos){
            total += p.getPreco();
        }
        return total;
    }

}