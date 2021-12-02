package Loja;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que contém os atributos de uma Promoção
 */
public abstract class Promocao implements Serializable {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String id;

    public Promocao(LocalDate dataInicio, LocalDate dataFim, String id) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public String getId(){
        return id;
    }

    /**
     * Método utilizado para calcular a percentagem de desconto no tipo de promoção
     * PagaMenos
     * 
     * @param quantidade quantidade de Produtos
     * @return percentagem de desconto sobre todos os produtos
     */
    public double percentagemDesconto(int quantidade) {
        return 0;
    }

    /**
     * Método que devolve o número de produtos grátis no tipo de Promoção
     * Leve3Pague4
     * 
     * @param quantidade quantidade de Produtos
     * @return número de produtos grátis
     */
    public int nProdutosGratis(int quantidade) {
        return 0;
    }

    public String tipoPromocao(){
        return "";
    }
}

class PagaMenos extends Promocao {

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

class Paga3Leva4 extends Promocao {

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