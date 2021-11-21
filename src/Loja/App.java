package Loja;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Loja QuimdaEsquina = new Loja();
        Cliente ric = new Cliente("RicFazers", "Casa", 935632589, "email@ricfazers.pt", new Date("10/10/2021"),
        true);
        QuimdaEsquina.AdicionaCliente(ric);
        Alimentar a = new Alimentar("a007", "nome", 10, 2, 1000, 100);
        QuimdaEsquina.adicionaProduto(a);
        final String RESET = "\033[0m";
        final String RED = "\033[0;31m";
        final String GREEN = "\033[0;32m";
        int escolha;
        Scanner stdi = new Scanner(System.in);
        do {
            System.out.println("\nMenu:");
            System.out.println("  1 - Realizar o login como utilizador");
            System.out.println("  2 - Administrar loja");
            System.out.println("  0 - Sair");
            System.out.print("> ");

            escolha = stdi.nextInt();
            switch (escolha) {
                case 1: //UTIIZADOR
                    Cliente cliente = null;
                    int escolha2;
                    do {
                        System.out.println("\nMenu Utilizador:");
                        System.out.println("  1 - Realizar o login/Criar conta");
                        System.out.println("  2 - Realizar uma compra");
                        System.out.println("  0 - Sair/Logout");
                        System.out.print("> ");
                        escolha2 = stdi.nextInt();
                        switch (escolha2) {
                            case 1://Realizar o login/Criar conta
                                System.out.println("Cliente ja tem registo??[S/N]: ");
                                System.out.print(">");
                                Scanner std = new Scanner(System.in);
                                String registado = std.nextLine();
                                registado = registado.trim();
                                if(registado.equals("s") || registado.equals("S")){
                                    Scanner sc = new Scanner(System.in);
                                    while(true){
                                        System.out.print("Introduza o email: ");
                                        String email = sc.nextLine();
                                        cliente = QuimdaEsquina.efetuarLogin(email);
                                        if(cliente != null){
                                            System.out.println(GREEN + "Login com sucesso!" + RESET);
                                            break;
                                        }
                                        else System.out.println(RED + "E-mail incorreto!" + RESET);
                                    }
                                }else if(registado.equals("n")|| registado.equals("N")){
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
                                    Cliente c = new Cliente(nome, morada, telefone, email, dataNascimento, false);
                                    QuimdaEsquina.AdicionaCliente(c);
                                    cliente = c;
                                }else{
                                    System.out.println(RED + "A resposta não é válida!" + RESET);
                                }
                                break;
                            case 2: //Realizar uma compra
                                if(cliente == null){
                                    System.out.println(RED + "Efetue o login antes de realizar uma compra." + RESET);
                                    break;
                                }

                                if(QuimdaEsquina.stock() == 0)
                                    System.out.println(RED + "Não existem produtos com stock na loja!" + RESET);
                                else{
                                    Scanner sc2 = new Scanner(System.in);
                                    Venda vend = new Venda(cliente);
                                    System.out.println("Escolha a referência de um produto para adicionar à sua compra");
                                    QuimdaEsquina.listarProdutos();
                                    while(true){
                                        if(QuimdaEsquina.stock() == 0)break;
                                        System.out.print("Referência > ");
                                        String referencia = sc2.nextLine();
                                        Produto p = QuimdaEsquina.find(referencia);
                                        if(p != null && p.getIdentificador().equals(referencia)){
                                            Scanner sc3 = new Scanner(System.in);
                                            while(true){
                                                System.out.print("Quantidade: ");
                                                int quantidade = sc3.nextInt();
                                                if(quantidade >0 && quantidade <= p.getStock()){
                                                    for(int i = 0;i<quantidade;i++) vend.adicionaProduto(p);
                                                    p.retiraStock(quantidade); 
                                                    break;
                                                }
                                                else System.out.println(RED + "Quantidade invalida" + RESET);
                                            }
                                            if(QuimdaEsquina.stock() == 0)
                                                continue;
                                            else{
                                                System.out.print("Quer adicionar mais produtos? [S/*] > ");
                                                String s = sc2.nextLine();
                                                if(s.equals("s") || s.equals("S")){
                                                    continue;
                                                }else break;
                                            }
                                        }
                                        else{
                                            System.out.println(RED + "O produto não existe! Tente de novo." + RESET);
                                            }
                                    }
                                    QuimdaEsquina.adicionaVenda(vend);
                                    System.out.println("O preço a pagar é: " + vend.total() + "€");
                                }
                                break;
                            case 0:
                            break;
                        }
                    }while(escolha2 != 0);
                break;

                case 2: //ADMIN
                int escolha3;
                do{
                    System.out.println("\nMenu Administrador:");
                    System.out.println("  1 - Consultar as compras realizadas");
                    System.out.println("  2 - Lista clientes");
                    System.out.println("  3 - Lista Produtos");
                    System.out.println("  0 - Sair");
                    System.out.print("> ");
                    escolha3 = stdi.nextInt();
                    switch (escolha3) {
                        case 1:
                            QuimdaEsquina.ListaVendas();
                            break;

                        case 2:
                            QuimdaEsquina.ListaClientes();
                            
                            break;
                        case 3:
                            QuimdaEsquina.listarProdutos();
                            break;
                        case 0:
                            break;
                    }

                }while(escolha3 != 0);
                break;

                case 0:
                System.exit(0);
            }
        } while (escolha != 0);

    }
}
