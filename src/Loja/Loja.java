package Loja;

import java.util.*;

public class Loja{
    private ArrayList<Cliente> clientes;
    private ArrayList<Venda> vendas;
    private ArrayList<Produto> produtos;
    private ArrayList<Promocao> promocoes;
    private Date data; //para as promoçoes

    public Loja() {
        clientes = new ArrayList<>();
        vendas = new ArrayList<>();
        produtos = new ArrayList<>();
        promocoes = new ArrayList<>();
        data = new Date();
    }

    public ArrayList<Cliente> getClientes(){
        return clientes;
    }

    public ArrayList<Produto> getProdutos(){
        return produtos;
    }

    public ArrayList<Venda> getVendas(){
        return vendas;
    }

    public ArrayList<Promocao> getPromocoes(){
        return promocoes;
    }

    public void setListaProdutos(ArrayList<Produto> l){
        produtos = l;
    }

    public void setListaClientes(ArrayList<Cliente> c){
        clientes = c;
    }

    public void setListaVendas(ArrayList<Venda> v){
        vendas = v;
    }

    public void setListaPromocoes(ArrayList<Promocao> p){
        promocoes = p;
    }

    public void AdicionaCliente(Cliente c) {
        clientes.add(c);
    }

    public void ListaClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
            System.out.println("-------------");
        }
    }

    public void adicionaVenda(Venda v){
        vendas.add(v);
    }

    public void adicionaProduto(Produto p){
        produtos.add(p);
    }

    public void listarProdutos(){
        for(Produto produto : produtos){
            if(produto.getStock() != 0)
                System.out.print(produto.getIdentificador() + "\t");
                System.out.println(produto);
        }
    }

    public int stock(){
        int quant = 0;
        for(Produto produto : produtos){
            if(produto.getStock() != 0)
                quant += produto.getStock();
        }
        return quant;
    }

    public Cliente efetuarLogin(String email){
        Cliente c = null;
        for(Cliente cliente : clientes){
            if(cliente.getmail().equals(email)) {
                c = cliente;
                break;
            }
        }
        return c;
    }

    public void ListaVendas(){
        if(vendas.size()!=0)
            for (Venda venda : vendas) {
                System.out.println(venda + "Preço Total: " + venda.total() + "€");
                System.out.println("-------------");
            }
        else System.out.println("Nao registo de vendas.");
    }

    public Produto find(String id){
        Produto prod;
        for(Produto p: produtos){
            if(p.getIdentificador().equals(id)){
                prod = p;
                return prod;
            }
        }
        return null;
    }

}