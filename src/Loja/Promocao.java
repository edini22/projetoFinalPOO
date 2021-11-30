package Loja;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que contém os atributos de uma Promoção
 */
public abstract class Promocao implements Serializable {
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Promocao(LocalDate dataInicio, LocalDate dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
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
}

class PagaMenos extends Promocao {

    public PagaMenos(LocalDate dataInicio, LocalDate dataFim) {
        super(dataInicio, dataFim);
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

}

class Paga3Leva4 extends Promocao {

    public Paga3Leva4(LocalDate dataInicio, LocalDate dataFim) {
        super(dataInicio, dataFim);
    }

    @Override
    public int nProdutosGratis(int quantidade) {
        return quantidade / 4;
    }

}