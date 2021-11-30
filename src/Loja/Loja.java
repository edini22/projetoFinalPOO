package Loja;

import java.util.*;

/**
 * Classe que devine as listas de Clientes, Produtos, Vendas e Promoções
 */
public class Loja{
    private ArrayList<Cliente> clientes;
    private ArrayList<Venda> vendas;
    private ArrayList<Produto> produtos;
    private ArrayList<Promocao> promocoes;

    /**
     * Construtor
     * Cria os ArrayList para os Clientes, Produtos, Vendas e Promoções
     */
    public Loja() {
        clientes = new ArrayList<>();
        vendas = new ArrayList<>();
        produtos = new ArrayList<>();
        promocoes = new ArrayList<>();
    }

    /**
     * Get Method
     * @return Lista de Clientes
     */
    public ArrayList<Cliente> getClientes(){
        return clientes;
    }

    /**
     * Get Method
     * @return Lista de Produtos
     */
    public ArrayList<Produto> getProdutos(){
        return produtos;
    }

    /**
     * Get Method
     * @return Lista de Vendas
     */
    public ArrayList<Venda> getVendas(){
        return vendas;
    }

    /**
     * Get Method
     * @return Lista de Promoções
     */
    public ArrayList<Promocao> getPromocoes(){
        return promocoes;
    }

    /**
     * Metodo que define a lista de Produtos da Loja
     * @param l lista de produtos
     */
    public void setListaProdutos(ArrayList<Produto> l){
        produtos = l;
    }

    /**
     * Metodo que define a lista de Clientes da Loja
     * @param l lista de produtos
     */
    public void setListaClientes(ArrayList<Cliente> c){
        clientes = c;
    }

    /**
     * Metodo que define a lista de Vendas da Loja
     * @param l lista de produtos
     */
    public void setListaVendas(ArrayList<Venda> v){
        vendas = v;
    }

    /**
     * Metodo que define a lista de Promções da Loja
     * @param l lista de produtos
     */
    public void setListaPromocoes(ArrayList<Promocao> p){
        promocoes = p;
    }
    
    /**
     * Método que adiciona um cliente à lista de Clientes
     * @param c cliente a adicionar a lista
     */
    public void AdicionaCliente(Cliente c) {
        clientes.add(c);
    }

    /**
     * Método que imprime a lista de clientes
     */
    public void ListaClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
            System.out.println("-------------");
        }
    }

    /**
     * Método que adiciona uma venda à lista de Vendas
     * @param v venda a adicionar a lista
     */
    public void adicionaVenda(Venda v){
        vendas.add(v);
    }

    /**
     * Método que adiciona um produto à lista de Produtos
     * @param p produto a adicionar a lista
     */
    public void adicionaProduto(Produto p){
        produtos.add(p);
    }

    /**
     * Método que imprime a lista de Produtos
     */
    public void listarProdutos(){
        for(Produto produto : produtos){
            if(produto.getStock() != 0)
                System.out.print(produto.getIdentificador() + "\t");
                System.out.println(produto);
        }
    }

    /**
     * Método que calcula o stock total da loja
     * @return stock total
     */
    public int stock(){
        int quant = 0;
        for(Produto produto : produtos){
            if(produto.getStock() != 0)
                quant += produto.getStock();
        }
        return quant;
    }

    /**
     * Método que faz o login de um cliente na loja
     * @param email email do cliente a dar login
     * @return c cliente que vai dar login
     */
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

    /**
     * Meétodo que imprime a lista de Vendas
     */
    public void ListaVendas(){
        if(vendas.size()!=0)
            for (Venda venda : vendas) {
                System.out.println(venda + "Preço Total: " + venda.total() + "€");
                System.out.println("-------------");
            }
        else System.out.println("Nao registo de vendas.");
    }

    /**
     * Método que devolve um produto da lista de Produtos
     * @param id identicador do produdo
     * @return produto
     */
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