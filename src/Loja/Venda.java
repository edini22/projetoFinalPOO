package Loja;
import java.io.Serializable;
import java.util.*;

public class Venda implements Serializable{
    private Cliente consumidor;
    private List<Item> items;

    public Venda(Cliente consumidor) {
        this.consumidor = consumidor;
        items = new ArrayList<>();
    }

    public void adicionaItem(Item item) {
        boolean existe = false;
        for(int i = 0; i< items.size(); i++){
            if(items.get(i).getProduto().getIdentificador().equals(item.getProduto().getIdentificador())){
                existe = true;
                items.get(i).addQuantidade(item.getQuantidade());
            }
        }
        if(!existe){
            items.add(item);
        }
    }
    
    private String listaProdutos(){
        String lista = "";
        for(Item i : items){
            lista += "\t >" + i.fatura() + "\n";
        }
        return lista;
    }

    public String toString(){
        return "Cliente: " + consumidor + "\nCesto de Compras:\n" + listaProdutos();
    }

    public double total(){
        double total = 0;
        for(Item i: items){
            total += i.conta();
        }
        return total;
    }

}