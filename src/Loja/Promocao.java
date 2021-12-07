package Loja;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que contem os atributos de uma Promocao
 */
public abstract class Promocao implements Serializable {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String id;

    /**
     * Construtor
     * 
     * @param dataInicio data de inicio da promocao
     * @param dataFim data do fim da promocao
     * @param id referencia do produto
     */
    public Promocao(LocalDate dataInicio, LocalDate dataFim, String id) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.id = id;
    }

    /**
     * Get Method
     * 
     * @return data de inicio da promocao
     */
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    /**
     * Get Method
     * 
     * @return data de fim da promocao
     */
    public LocalDate getDataFim() {
        return dataFim;
    }

    /**
     * Get Method
     * 
     * @return referencia do produto
     */
    public String getId(){
        return id;
    }

    /**
     * Metodo utilizado para calcular a percentagem de desconto no tipo de promocao
     * PagaMenos
     * 
     * @param quantidade quantidade de Produtos
     * @return percentagem de desconto sobre todos os produtos
     */
    public double percentagemDesconto(int quantidade) {
        return 0;
    }

    /**
     * Metodo que devolve o numero de produtos gratis no tipo de Promocao
     * Leve3Pague4
     * 
     * @param quantidade quantidade de Produtos
     * @return numero de produtos gratis
     */
    public int nProdutosGratis(int quantidade) {
        return 0;
    }

    /**
     * retorna a string do tipo de promocao 
     * @return string do tipo de promocao
     */
    public String tipoPromocao(){
        return "";
    }
}

/**
 * Classe descendente de Promocao que define PagaMenos
 */
class PagaMenos extends Promocao {

    /**
     * Construtor
     * 
     * @param dataInicio data de inicio da promocao
     * @param dataFim data do fim da promocao
     * @param id referencia do produto
     */
    public PagaMenos(LocalDate dataInicio, LocalDate dataFim,String id) {
        super(dataInicio, dataFim,id);
    }

    @Override
    public double percentagemDesconto(int quantidade) {
        if (quantidade == 1) {
            return 1;
        } else if (quantidade > 1 && quantidade < 10) {
            double promo = 0.05;
            return (quantidade - 1) * promo;
        } else {
            return 0.50;
        }
    }

    @Override
    public String tipoPromocao(){
        return "Page Menos!";
    }

}

/**
 * Classe descendente de Promocao que define Paga3Leva4
 */
class Paga3Leva4 extends Promocao {

    /**
     * Construtor
     * 
     * @param dataInicio data de inicio da promocao
     * @param dataFim data do fim da promocao
     * @param id referencia do produto
     */
    public Paga3Leva4(LocalDate dataInicio, LocalDate dataFim, String id) {
        super(dataInicio, dataFim, id);
    }

    @Override
    public int nProdutosGratis(int quantidade) {
        return quantidade / 4;
    }

    @Override
    public String tipoPromocao(){
        return "Pague 3 Leve 4!";
    }

}