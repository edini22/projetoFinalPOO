package Loja;

import java.time.LocalDate;
import java.time.format.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        LocalDate dataAtual = LocalDate.now();
        Loja QuimdaEsquina = new Loja();
        String dateFormat = "dd/MM/uuuu";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        QuimdaEsquina.setListaProdutos();
        QuimdaEsquina.setListaClientes();
        QuimdaEsquina.setListaVendas();
        QuimdaEsquina.setListaPromocoes( dataAtual);
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
            while (true)
                try {
                    escolha = Integer.parseInt(stdi.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.print("Tente novamente: ");
                }
            switch (escolha) {
                case 1 -> { // UTIIZADOR
                    Cliente cliente = null;
                    int escolha2;
                    do {
                        System.out.println("\nMenu Utilizador:");
                        System.out.println("  1 - Realizar o login/Criar conta");
                        System.out.println("  2 - Realizar uma compra");
                        System.out.println("  0 - Sair/Logout");
                        System.out.print("> ");
                        while (true)
                            try {
                                escolha2 = Integer.parseInt(stdi.nextLine());
                                break;
                            } catch (NumberFormatException nfe) {
                                System.out.print("Tente novamente: ");
                            }
                        switch (escolha2) {
                            case 1:// Realizar o login/Criar conta
                                System.out.println("Cliente ja tem registo??[S/N]: ");
                                System.out.print(">");
                                Scanner std = new Scanner(System.in);
                                String registado = std.nextLine();
                                registado = registado.trim();
                                if (registado.equals("s") || registado.equals("S")) {
                                    Scanner sc = new Scanner(System.in);
                                    while (true) {
                                        System.out.print("Introduza o email: ");
                                        String email = sc.nextLine();
                                        cliente = QuimdaEsquina.efetuarLogin(email);
                                        if (cliente != null) {
                                            System.out.println(GREEN + "Login com sucesso!" + RESET);
                                            break;
                                        } else
                                            System.out.println(RED + "E-mail incorreto!" + RESET);
                                    }
                                } else if (registado.equals("n") || registado.equals("N")) {
                                    Scanner newclient = new Scanner(System.in);
                                    System.out.print("Nome: ");
                                    String nome = newclient.nextLine();// TODO fazer protecoes para o nome e assim nao
                                    // ser so numeros!
                                    System.out.print("Morada: ");
                                    String morada = newclient.nextLine();
                                    System.out.print("Telefone: ");
                                    int telefone;
                                    while (true)
                                        try {
                                            telefone = Integer.parseInt(newclient.nextLine());
                                            break;
                                        } catch (NumberFormatException nfe) {
                                            System.out.print(RED + "Telefone Invalido\n" + RESET + "Tenta novamente: ");
                                        }
                                    System.out.print("Email: ");
                                    String email = "";
                                    while (!email.contains("@")) {
                                        email = newclient.nextLine();
                                        if (!email.contains("@"))
                                            System.out.println(RED + "Email Invalido!\n" + RESET + "Tente novamente:");
                                    }
                                    String dat;
                                    System.out.print("Data de Nascimento[DD/MM/AAAA]: ");
                                    while (true) {
                                        dat = newclient.nextLine();
                                        try {
                                            LocalDate data = LocalDate.parse(dat, dateTimeFormatter);
                                            Cliente c = new Cliente(nome, morada, telefone, email, data, false);
                                            QuimdaEsquina.adicionaCliente(c);
                                            QuimdaEsquina.writeClientesObj();
                                            cliente = c;
                                            break;
                                        } catch (DateTimeParseException e) {
                                            System.out.print(RED + "Data Invalido\n" + RESET + "Tenta novamente: ");
                                        }
                                    }
                                } else {
                                    System.out.println(RED + "A resposta não é válida!" + RESET);
                                }
                                break;
                            case 2: // Realizar uma compra
                                if(LocalDate.now().isAfter(dataAtual)) {
                                    dataAtual = LocalDate.now();
                                    QuimdaEsquina.atualizaPromocoes(dataAtual);
                                }
                                if (cliente == null) {
                                    System.out.println(RED + "Efetue o login antes de realizar uma compra." + RESET);
                                    break;
                                }

                                if (QuimdaEsquina.stock() == 0)
                                    System.out.println(RED + "Não existem produtos com stock na loja!" + RESET);
                                else {
                                    Scanner sc2 = new Scanner(System.in);
                                    Venda vend = new Venda(cliente);
                                    System.out
                                            .println("Escolha a referência de um produto para adicionar à sua compra");
                                    QuimdaEsquina.listarProdutos();
                                    while (true) {
                                        if (QuimdaEsquina.stock() == 0)
                                            break;
                                        System.out.print("Referência > ");
                                        String referencia = sc2.nextLine();
                                        Produto p = QuimdaEsquina.find(referencia);
                                        if (p != null && p.getIdentificador().equals(referencia) && p.getStock() != 0) {
                                            Scanner sc3 = new Scanner(System.in);
                                            while (true) {
                                                System.out.print("Quantidade: ");
                                                int quantidade = sc3.nextInt();
                                                if (quantidade > 0 && quantidade <= p.getStock()) {
                                                    Item i = new Item(p, quantidade);
                                                    vend.adicionaItem(i);
                                                    break;
                                                } else
                                                    System.out.println(RED + "Quantidade invalida" + RESET);
                                            }
                                            if (QuimdaEsquina.stock() == 0)
                                                break;
                                            else {
                                                System.out.print("Quer adicionar mais produtos? [S/*] > ");
                                                String s = sc2.nextLine();
                                                if (!(s.equals("s") || s.equals("S"))) {
                                                    break;
                                                }
                                            }
                                        } else {
                                            System.out.println(RED + "O produto não existe! Tente de novo." + RESET);
                                        }
                                    }
                                    System.out.println(vend);
                                    QuimdaEsquina.adicionaVenda(vend);
                                    QuimdaEsquina.writeVendasObj();
                                    System.out.println(
                                            "O preço a pagar é: " + (vend.total() + vend.precoTransporte()) + " €");
                                }
                                break;
                            case 0:
                                break;
                        }
                    } while (escolha2 != 0);
                }
                case 2 -> { // ADMIN
                    int escolha3;
                    do {
                        System.out.println("\nMenu Administrador:");
                        System.out.println("  1 - Consultar as compras realizadas");
                        System.out.println("  2 - Lista clientes");
                        System.out.println("  3 - Lista Produtos");
                        System.out.println("  4 - Avançar data");
                        System.out.println("  5 - data atual");
                        System.out.println("  0 - Sair");
                        System.out.print("> ");
                        while (true)
                            try {
                                escolha3 = Integer.parseInt(stdi.nextLine());
                                break;
                            } catch (NumberFormatException nfe) {
                                System.out.print("Tente novamente: ");
                            }
                        switch (escolha3) {
                            case 1:
                                QuimdaEsquina.listaVendas();
                                break;
                            case 2:
                                QuimdaEsquina.listaClientes();
                                break;
                            case 3:
                                if(LocalDate.now().isAfter(dataAtual)) {
                                    dataAtual = LocalDate.now();
                                    QuimdaEsquina.atualizaPromocoes(dataAtual);
                                }
                                QuimdaEsquina.listarProdutos();
                                break;
                            case 4:
                                int dias ;
                                Scanner scan = new Scanner(System.in);
                                System.out.println("Insira a quantidade de dias que quer avançar: ");
                                while (true)
                                    try {
                                        dias = Integer.parseInt(scan.nextLine());
                                        break;
                                    } catch (NumberFormatException nfe) {
                                        System.out.print("Tenta novamente: ");
                                    }
                                    dataAtual = dataAtual.plusDays(dias);
                                    QuimdaEsquina.atualizaPromocoes(dataAtual);
                                break;
                            case 5:
                                System.out.println(dataAtual);
                                break;
                            case 0:
                                break;
                        }

                    } while (escolha3 != 0);
                }
                case 0 -> System.exit(0);
            }
        } while (escolha != 0);

    }
}
