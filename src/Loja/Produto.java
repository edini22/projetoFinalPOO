package Loja;

import java.io.*;

/**
 * Classe que contém os atributos de um produto
 */
public abstract class Produto implements Serializable {
    private String identificador;
    private String nome;
    private double preco;
    private int stock;
    private Promocao promocao;

    /**
     * Construtor
     * 
     * @param identificador referência do produto
     * @param nome          nome do produto
     * @param preco         preço do produto
     * @param stock         quantidade de stock existente do produto
     */
    public Produto(String identificador, String nome, double preco, int stock) {
        this.identificador = identificador;
        this.nome = nome;
        this.preco = preco;
        this.stock = stock;
        this.promocao = null;
    }

    /**
     * Método que retira stock ao produto
     * 
     * @param n quantidade de produtos que se vai retirar ao stock
     */
    public void retiraStock(int n) {
        stock -= n;
    }

    public String getTipoProduto() {
        identificador = identificador.toLowerCase();
        String[] id = identificador.split("");
        return id[0];
    }

    /**
     * Método que adiciona stock ao produto
     * 
     * @param n quantidade de produtos que se vai adicionar ao stock
     */
    public void adicionastock(int n) {
        stock += n;
    }

    /**
     * ToString Method
     * 
     * @return Strinf formatada com os aributos do Produto
     */
    public String toString() {
        return "nome: " + nome + " preço: " + preco + " stock: " + stock;
    }

    /**
     * Get Method
     * 
     * @return identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Get Method
     * 
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Get Method
     * 
     * @return peso
     */
    public double getPeso() {
        return 0;
    }

    /**
     * Get Method
     * 
     * @return preço
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Get Method
     * 
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Get Method
     * 
     * @return promoção
     */
    public Promocao getPromocao() {
        return promocao;
    }

    /**
     * Set Method
     * 
     * @param p Promocao a colocar no atributo
     */
    public void setPromocao(Promocao p) {
        this.promocao = p;
    }
}

/**
 * Classe descendente de Produto que define os produtos alimentares
 */
class Alimentar extends Produto {
    private int calorias;
    private int pGordura;

    /**
     * 
     * @param identificador referência do produto
     * @param nome          nome do produto
     * @param preco         preço do produto
     * @param stock         quantidade de stock existente do produto
     * @param calorias      quantidade de calorias do produto alimentar
     * @param pGordura      percentagem de gordura do produto
     */
    public Alimentar(String identificador, String nome, double preco, int stock, int calorias, int pGordura) {
        super(identificador, nome, preco, stock);
        this.calorias = calorias;
        this.pGordura = pGordura;
    }

    /**
     * ToString Method
     * 
     * @return String formatada com os atributos do produto
     */
    @Override
    public String toString() {
        return "Nome: " + getNome() + " preço: " + getPreco() + " stock: " + getStock() + " calorias: " + calorias
                + " % de Gordura: " + pGordura;
    }
}

/**
 * Classe descendente de Produto que define os produtos de limpeza
 */
class Limpeza extends Produto {
    private int toxidade;

    /**
     * 
     * @param identificador referência do produto
     * @param nome          nome do produto
     * @param preco         preço do produto
     * @param stock         quantidade de stock existente do produto
     * @param toxidade      toxicidade do produto
     */
    public Limpeza(String identificador, String nome, double preco, int stock, int toxidade) {
        super(identificador, nome, preco, stock);
        this.toxidade = toxidade;
    }

    /**
     * ToString Method
     * 
     * @return String formatada com os atributos do produto
     */
    @Override
    public String toString() {
        return "Nome: " + getNome() + " preço: " + getPreco() + " stock: " + getStock() + " toxidade: " + toxidade;
    }
}

/**
 * Classe descendente de Produto que define os produtos de mobiliário
 */
class Mobiliario extends Produto {
    private double peso;
    private int[] dimensao;

    /**
     * 
     * @param identificador referência do produto
     * @param nome          nome do produto
     * @param preco         preço do produto
     * @param stock         quantidade de stock existente do produto
     * @param peso          peso do produto
     * @param dimensao      array de dimensoes do produto Comprimento*Largura*Altura
     */
    public Mobiliario(String identificador, String nome, double preco, int stock, double peso, int[] dimensao) {
        super(identificador, nome, preco, stock);
        this.dimensao = new int[3];// TODO Fazer proteçoes acerca do tamanho do array
        this.peso = peso;
        this.dimensao = dimensao;
    }

    @Override
    public double getPeso() {
        return peso;
    }

    /**
     * 
     * @return String formatada do array de dimensões do produto
     */
    private String dimensaoFormatada() {
        String dim = "[";
        for (int i : dimensao) {
            dim += i + " ";
        }
        dim += "]";
        return dim;
    }

    /**
     * ToString Method
     * 
     * @return String formatada com os atributos do produto
     */
    @Override
    public String toString() {
        return "Nome: " + getNome() + " preço: " + getPreco() + " stock: " + getStock() + " peso: " + peso
                + " Dimensoes: " + dimensaoFormatada();
    }

}