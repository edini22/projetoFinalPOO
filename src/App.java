import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Loja QuimdaEsquina = new Loja();
        QuimdaEsquina.AdicionaCliente("RicFazers", "Casa", 935632589, "email@ricfazers.pt", new Date("10/10/2021"),
                "frequente");
        int escolha;
        Scanner stdin = new Scanner(System.in);
        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Realizar o login");
            System.out.println("2 - Realizar uma compra");
            System.out.println("3 - Consultar as compras realizadas");
            System.out.println("4 - Lista clientes");
            System.out.println("5 - Adicionar Cliente");
            System.out.println("0 - Sair");
            escolha = stdin.nextInt();
            switch (escolha) {
            case 1:
                break;
            case 2:

                break;
            case 3:
                QuimdaEsquina.ListaVendas();
                break;
            case 4:
                QuimdaEsquina.ListaClientes();
                break;
            case 5:
                Scanner newclient = new Scanner(System.in);
                Scanner scanint = new Scanner(System.in);
                System.out.print("Nome: ");
                String nome = newclient.nextLine();
                System.out.print("Morada: ");
                String morada = newclient.nextLine();
                System.out.print("Telefone: ");
                int telefone = scanint.nextInt();
                System.out.print("Email: ");
                String email = newclient.nextLine();
                System.out.print("Data de Nascimento: ");
                String dat = newclient.nextLine();
                Date dataNascimento = new Date(dat);
                System.out.print("Regularidade: ");
                String regularidade = newclient.nextLine();
                QuimdaEsquina.AdicionaCliente(nome, morada, telefone, email, dataNascimento, regularidade);
                break;
            case 0:
                System.exit(0);
            }
        } while (escolha != 0);

    }
}

// Promoçoes mudam de um dia para o outro
// Por isso temos que colocar uma class do dia atual e que de para mudar o dia
// para simular a transiçao do dia usando a class do java

class Loja {
    private List<Cliente> clientes;
    private List<Venda> vendas;
    private Date data;// para as promoçoes

    public Loja() {
        clientes = new ArrayList<>();
        vendas = new ArrayList<>();
        data = new Date();
    }

    public void AdicionaCliente(String nome, String morada, int telefone, String email, Date dataNascimento,
            String regularidade) {
        Cliente c = new Cliente(nome, morada, telefone, email, dataNascimento, regularidade);
        clientes.add(c);
    }

    public void ListaClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
            System.out.println("-------------");
        }
    }

    public void ListaVendas(){
        for (Venda venda : vendas) {
            System.out.println(venda);
            System.out.println("-------------");
        }
    }

}

class Venda {
    private Cliente consumidor;
    private List<Produto> produtos;
    private Promocao promocao;

    public Venda(Cliente consumidor) {
        this.consumidor = consumidor;
    }

    public void adicionaProduto(Produto p) {
        produtos.add(p);
    }
    
    private String listaProdutos(){
        String lista = "";
        for(Produto produto : produtos){
            lista += "\t >" + produto + "\n";
        }
        return lista;
    }

    public String toString(){
        return "Cliente: " + consumidor + "\nCesto de Compras:\n" + listaProdutos();
    }

}

abstract class Promocao {

}

class PagaMenos extends Promocao {

}

class Paga3Leva4 extends Promocao {

}

class Cliente {
    private String nome;
    private String morada;
    private int telefone;
    private String email;
    private Date dataNascimento;
    private String regularidade;

    public Cliente(String nome, String morada, int telefone, String email, Date dataNascimento, String regularidade) {
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.regularidade = regularidade;
    }

    public String toString() {
        return "Nome: " + nome + "\nMorada: " + morada + "\nTelefone: " + telefone + "\nEmail: " + email
                + "\n Data de nascimento: " + dataNascimento + "\nRegularidade: " + regularidade;
    }
}

class Produto {
    private int identificador;
    private String nome;
    private double preco;
    private int stock;

    public Produto(int identificador, String nome, double preco, int stock) {
        this.identificador = identificador;
        this.nome = nome;
        this.preco = preco;
        this.stock = stock;
    }
}

class Alimentar extends Produto {
    private int calorias;
    private int pGordura;

    public Alimentar(int identificador, String nome, double preco, int stock, int calorias, int pGordura) {
        super(identificador, nome, preco, stock);
        this.calorias = calorias;
        this.pGordura = pGordura;
    }
}

class Limpeza extends Produto {
    private int toxidade;

    public Limpeza(int identificador, String nome, double preco, int stock, int toxidade) {
        super(identificador, nome, preco, stock);
        this.toxidade = toxidade;
    }
}

class Mobiliario extends Produto {
    private double peso;
    private int[] dimensao;

    public Mobiliario(int identificador, String nome, double preco, int stock, double peso, int[] dimensao) {
        super(identificador, nome, preco, stock);
        this.dimensao = new int[3];// TODO Fazer proteçoes acerca do tamanho do array
        this.peso = peso;
        this.dimensao = dimensao;
    }
}