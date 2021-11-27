package Loja;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Ficheiros {
    String dateFormat = "dd/MM/uuuu";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
            .withResolverStyle(ResolverStyle.STRICT);

    private File produtos;
    private File clientes;
    private File promocoes;
    private File produtosObj;
    private File clientesObj;
    private File promocoesObj;
    private File vendasObj;

    public Ficheiros() {
        produtos = new File("./ficheiros\\Produtos.txt");
        clientes = new File("./ficheiros\\Clientes.txt");
        promocoes = new File("./ficheiros\\Promocoes.txt");
        produtosObj = new File("./ficheiros\\Produtos.obj");
        clientesObj = new File("./ficheiros\\Clientes.obj");
        promocoesObj = new File("./ficheiros\\Promocoes.obj");
        vendasObj = new File("./ficheiros\\Vendas.obj");
    }

    public ArrayList<Produto> listaProdutos() {
        ArrayList<Produto> p = new ArrayList<>();

        if (!(produtosObj.exists() && produtosObj.isFile())) {
            try (FileReader fr = new FileReader(produtos); BufferedReader br = new BufferedReader(fr);) {// TODO
                                                                                                         // verificar se
                                                                                                         // nao existe
                                                                                                         // o// ficheiro
                                                                                                         // deobjetos!
                String line;
                while ((line = br.readLine()) != null) {
                    String[] string = line.split(";");
                    string[0] = string[0].replaceAll("\\s+", "");
                    string[1] = string[1].strip();
                    char[] ref = string[0].toCharArray();
                    if (ref[0] == 'a') {
                        if (string.length != 6)
                            System.exit(1);
                        double preco = 0;
                        int stock = 0;
                        int calorias = 0;
                        int pGordura = 0;
                        try {
                            preco = Double.parseDouble(string[2]);
                            stock = Integer.parseInt(string[3]);
                            calorias = Integer.parseInt(string[4]);
                            pGordura = Integer.parseInt(string[5]);
                            Alimentar a = new Alimentar(string[0], string[1], preco, stock, calorias, pGordura);
                            p.add(a);
                        } catch (Exception e) {
                            System.out.println("erro!");
                        }
                    } else if (ref[0] == 'l') {
                        if (string.length != 5)
                            System.exit(1);
                        double preco = 0;
                        int stock = 0;
                        int toxidade = 0;
                        try {
                            preco = Double.parseDouble(string[2]);
                            stock = Integer.parseInt(string[3]);
                            toxidade = Integer.parseInt(string[4]);
                            Limpeza l = new Limpeza(string[0], string[1], preco, stock, toxidade);
                            p.add(l);
                        } catch (Exception e) {
                            System.out.println("erro!");
                        }

                    } else if (ref[0] == 'm') {
                        if (string.length != 8)
                            System.exit(1);
                        int[] dimensao = new int[3];
                        int stock = 0;
                        double preco = 0;
                        double peso = 0;
                        try {
                            preco = Double.parseDouble(string[2]);
                            stock = Integer.parseInt(string[3]);
                            peso = Double.parseDouble(string[4]);
                            dimensao[0] = Integer.parseInt(string[5]);
                            dimensao[1] = Integer.parseInt(string[6]);
                            dimensao[2] = Integer.parseInt(string[7]);
                            Mobiliario m = new Mobiliario(string[0], string[1], preco, stock, peso, dimensao);
                            p.add(m);
                        } catch (Exception e) {
                            System.out.println("erro!");
                        }
                    } else
                        System.out.println("errou a referencia");

                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro de Produtos!");
            }
        } else {
            try {
                FileInputStream fis = new FileInputStream(produtosObj);
                ObjectInputStream ois = new ObjectInputStream(fis);
                p = (ArrayList<Produto>) ois.readObject();
                ois.close();
            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Produto.obj\".");
                p = null;
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Produto\" nao encontrada");
                p = null;
            }
        }
        return p;
    }

    public ArrayList<Cliente> listaClientes() {
        ArrayList<Cliente> c = new ArrayList<>();
        if (!(clientesObj.exists() && clientesObj.isFile())) {
            try (FileReader fr = new FileReader(clientes); BufferedReader br = new BufferedReader(fr);) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] string = line.split(";");
                    if (string.length != 6)
                        System.exit(1);
                    string[0] = string[0].strip();
                    string[1] = string[1].strip();
                    int telefone = 0;
                    if (!string[3].contains("@")) {
                        System.out.println("Email Invalido!\n");
                        System.exit(1);
                    }
                    string[3] = string[3].replaceAll("\\s+", "");
                    try {
                        telefone = Integer.parseInt(string[2]);
                        LocalDate data = LocalDate.parse(string[4], dateTimeFormatter);// TODO bug pois se colocar
                                                                                       // 5/10/2000 da erro pois o
                                                                                       // formato tem de se dd/... ou
                                                                                       // seja 05 e nao 5
                    } catch (DateTimeParseException e) {
                        System.out.print("Data Invalido\n");
                        System.exit(3);
                    } catch (Exception e) {
                        System.out.println("Telefone de um cliente invalido!");
                        System.exit(3);
                    }
                    string[5] = string[5].toLowerCase();
                    string[5] = string[5].replaceAll("\\s+", "");
                    boolean frequente = false;
                    if (string[5].equals("true")) {
                        frequente = true;
                    } else if (string[5].equals("false")) {

                    } else {
                        System.out.println(
                                "erro no Cliente ser frequente ou nao, verifique se tem true ou false no ultimo parametro!");
                        System.exit(1);
                    }

                    LocalDate data = LocalDate.parse(string[4], dateTimeFormatter);
                    Cliente cl = new Cliente(string[0], string[1], telefone, string[3], data, frequente);
                    c.add(cl);
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro de clientes!");
            }
        } else {
            try {
                FileInputStream fis = new FileInputStream(clientesObj);
                ObjectInputStream ois = new ObjectInputStream(fis);
                c = (ArrayList<Cliente>) ois.readObject();
                ois.close();
            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Clientes.obj\".");
                c = null;
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Cliente\" nao encontrada");
                c = null;
            }
        }
        return c;
    }

    public ArrayList<Venda> listaVendas(){
        ArrayList<Venda> v = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(vendasObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            v = (ArrayList<Venda>) ois.readObject();
            ois.close();
        } catch (IOException ioe) {
            System.out.println("Erro ao ler o ficheiro \"Clientes.obj\".");
            v = null;
        } catch (ClassNotFoundException cnf) {
            System.out.println("Classe \"Cliente\" nao encontrada");
            v = null;
        }
        return v;
    }

    public ArrayList<Promocao> listaPromocoes(){
        return null;
    }

    public void writeProdutosObj(ArrayList<Produto> lista) { 
        if (lista.size() == 0)
            return;
        else {

            try (FileOutputStream fos = new FileOutputStream(produtosObj);
                ObjectOutputStream oos = new ObjectOutputStream(fos);){
                oos.writeObject(lista);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Produtos.obj\".");
            }
        }
    }

    public void writeClientesObj(ArrayList<Cliente> lista) {
        if (lista.size() == 0)
            return;
        else {
            try (FileOutputStream fos = new FileOutputStream(clientesObj);
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(lista);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Clientes.obj\".");
            }
        }
    }

    public void writeVendasObj(ArrayList<Venda> lista) {
        if (lista.size() == 0) {
            return;
        } else {
            try (FileOutputStream fos = new FileOutputStream(vendasObj);
                ObjectOutputStream oos = new ObjectOutputStream(fos);){
                oos.writeObject(lista);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Vendas.obj\".");
            }
        }
    }

    public void writePromocoesObj(ArrayList<Promocao> lista) {
        if (lista.size() == 0) {
            return;
        } else {
            try(FileOutputStream fos = new FileOutputStream(promocoesObj);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(lista);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Promocoes.obj\".");
            }
        }
    }
}
