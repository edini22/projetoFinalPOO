package Loja;

import java.io.Serializable;

// Promoçoes mudam de um dia para o outro
// Por isso temos que colocar uma class do dia atual e que de para mudar o dia
// para simular a transiçao do dia usando a class do java

public abstract class Promocao implements Serializable {

    public static void main(String[] args) {
        Promocao p = new Paga3Leva4();
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "   ");
            System.out.println(p.nProdutosGratis(i));
        }
        
    }

    public double percentagemDesconto(int quantidade) {
        return 0;
    }

    public int nProdutosGratis(int quantidade){
        return 0;
    }
}

class PagaMenos extends Promocao {
    private double maxPromocao = 0.50;
    private double promo = 0.05;

    @Override
    public double percentagemDesconto(int quantidade) {
        if (quantidade == 1) {
            return 1;
        } else if (quantidade > 1 && quantidade < 10) {
            return (quantidade - 1) * promo;
        } else {
            return maxPromocao;
        }
    }

}

// try(FileWriter fw = new FileWriter("","w");BufferedWriter bw = new
// BufferedWriter(fw)) { Importante
// bw.write("c");
// } catch (Exception e) {
// //TOO HOT TO HANDLE
// }

class Paga3Leva4 extends Promocao {

    @Override
    public int nProdutosGratis(int quantidade){
        return quantidade/4;
    }

}