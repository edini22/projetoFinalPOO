package Loja;

import java.io.Serializable;
import java.util.*;

/**
 * Classe que contém os atributos de uma Venda
 */
public class Venda implements Serializable {
    private Cliente consumidor;
    private List<Item> items;
    private int transporteMobilia;

    /**
     * Construtor
     * 
     * @param consumidor Cliente que realiza a compra
     */
    public Venda(Cliente consumidor) {
        this.consumidor = consumidor;
        items = new ArrayList<>();
        transporteMobilia = 0;
    }

    /**
     * Método que adiciona um item à venda
     * 
     * @param item conjunto de produtos iguais a adicionar a compra
     */
    public void adicionaItem(Item item) {
        boolean existe = false;
        if (item.getProduto().getPeso() != 0)
            if (item.getProduto().getPeso() >= 15)
                transporteMobilia += item.getQuantidade();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduto().getIdentificador().equals(item.getProduto().getIdentificador())) {
                existe = true;
                items.get(i).addQuantidade(item.getQuantidade());
            }
        }
        if (!existe) {
            items.add(item);
        }
    }

    /**
     * Método que Devolve uma String dos items da venda formatados
     * 
     * @return lista de items de uma venda
     */
    private String listaProdutos() {
        StringBuilder lista = new StringBuilder();
        for (Item i : items) {
            lista.append("\t >").append(i.fatura()).append("\n");
        }
        return lista.toString();
    }

    public String toString() {
        return "Cliente: " + consumidor + "\nCesto de Compras:\n" + listaProdutos();
    }

    /**
     * Calcula o preço total dos Produtos na Venda
     * 
     * @return preço total da venda
     */
    public double total() {
        double total = 0;
        for (Item i : items) {
            total += i.conta();
        }
        return total;
    }

    public int precoTransporte() {
        int preco = 0;
        if (consumidor.getFrequente()){
            if (total() <= 40)
                preco += 15;
        }
        else
            preco += 20;
        if (transporteMobilia != 0)
            preco += (transporteMobilia * 10);
        return preco;
    }

}