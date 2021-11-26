package Loja;

import java.io.BufferedWriter;
import java.io.FileWriter;

// Promoçoes mudam de um dia para o outro
// Por isso temos que colocar uma class do dia atual e que de para mudar o dia
// para simular a transiçao do dia usando a class do java

public abstract class Promocao {
//     private Produto produto;

//     public Promocao(Produto produto){
//         this.produto = produto;
//     }
}

class PagaMenos extends Promocao {

    public PagaMenos(){

    }

}

// try(FileWriter fw = new FileWriter("","w");BufferedWriter bw = new BufferedWriter(fw)) {Importante
//     bw.write("c");
// } catch (Exception e) {
//     //TODO: handle exception
// }

class Paga3Leva4 extends Promocao {

    public Paga3Leva4(){

    }

}