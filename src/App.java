import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        
    }
}

class Loja{
    private List<Cliente> clientes;
    private List<Venda> vendas;

    public Loja(){
        clientes = new ArrayList<>();
        vendas = new ArrayList<>();
    }

}

class Venda{
    private Cliente consumidor;
    private List<Produto> produtos;
    private Promocao promocao;

    public Venda(Cliente consumidor){
        this.consumidor = consumidor;
    }

    public void adicionaProduto(Produto p){
        produtos.add(p);
    }
}

abstract class Promocao{

}

class PagaMenos extends Promocao{

}

class Paga3Leva4 extends Promocao{
    
}

class Cliente{
    private String nome;
    private String morada;
    private int telefone;
    private String email;
    private Date dataNascimento;
    private String regularidade;

    public Cliente(String nome,String morada, int telefone, String email, Date dataNascimento, String regularidade){
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.regularidade = regularidade;
    }
}

class Produto{
    private int identificador;
    private String nome;
    private double preco;
    private int stock;

    public Produto(int identificador,String nome,double preco,int stock){
        this.identificador = identificador;
        this.nome = nome;
        this.preco = preco;
        this.stock = stock;
    }
}

class Alimentar extends Produto{
    private int calorias;
    private int pGordura;

    public Alimentar(int identificador,String nome,double preco,int stock,int calorias,int pGordura){
        super(identificador, nome, preco, stock);
        this.calorias = calorias;
        this.pGordura = pGordura;
    }
}

class Limpeza extends Produto{
    private int toxidade;

    public Limpeza(int identificador,String nome,double preco,int stock,int toxidade){
        super(identificador, nome, preco, stock);
        this.toxidade = toxidade;
    }
}

class Mobiliario extends Produto{
    private double peso;
    private int[] dimensao;

    public Mobiliario(int identificador,String nome,double preco,int stock,double peso,int[] dimensao){
        super(identificador, nome, preco, stock);
        this.dimensao = new int[3];//TODO Fazer proteçoes acerca do tamanho do array
        this.peso = peso;
        this.dimensao = dimensao;
    }
}