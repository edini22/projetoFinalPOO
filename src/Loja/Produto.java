package Loja;

public abstract class Produto {
    private String identificador;
    private String nome;
    private double preco;
    private int stock;

    public Produto(String identificador, String nome, double preco, int stock) {
        this.identificador = identificador;
        this.nome = nome;
        this.preco = preco;
        this.stock = stock;
    }

    public void retiraStock(int n){
        stock -= n;
    }

    public String toString(){
        return "nome: " + nome + " preço: " + preco + " stock: " + stock;
    }

    public String getNome(){
        return nome;
    }

    public double getPreco(){
        return preco;
    }

    public int getStock(){
        return stock;
    }

    public String fatura(){
        return identificador + "  " + nome  + " " + preco + "€";
    }

    public String getIdentificador(){
        return identificador;
    }
}

class Alimentar extends Produto {
    private int calorias;
    private int pGordura;

    public Alimentar(String identificador, String nome, double preco, int stock, int calorias, int pGordura) {
        super(identificador, nome, preco, stock);
        this.calorias = calorias;
        this.pGordura = pGordura;
    }

    @Override
    public String toString(){
        return "Nome: " + getNome() + " preço: " + getPreco() + " stock: " + getStock() + " calorias: " + calorias + " % de Gordura: " + pGordura;
    }
}

class Limpeza extends Produto {
    private int toxidade;

    public Limpeza(String identificador, String nome, double preco, int stock, int toxidade) {
        super(identificador, nome, preco, stock);
        this.toxidade = toxidade;
    }

    @Override
    public String toString(){
        return "Nome: " + getNome() + " preço: " + getPreco() + " stock: " + getStock() + " toxidade: " + toxidade;
    }
}

class Mobiliario extends Produto {
    private double peso;
    private int[] dimensao;

    public Mobiliario(String identificador, String nome, double preco, int stock, double peso, int[] dimensao) {
        super(identificador, nome, preco, stock);
        this.dimensao = new int[3];// TODO Fazer proteçoes acerca do tamanho do array
        this.peso = peso;
        this.dimensao = dimensao;
    }

    @Override
    public String toString(){
        return "Nome: " + getNome() + " preço: " + getPreco() + " stock: " + getStock() + " calorias: "+ " peso: " + peso + "Dimensoes: " + dimensao;
    }
    
}