package Loja;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 * Classe que trata da leitura dos ficheiros e faz o parsing desses dados
 * Opta pela leitura dos ficheiros de objetos quando estes existem, caso
 * contrario são lidos os ficheitos *.txt
 */
public class Ficheiros {
    String dateFormat = "dd/MM/uuuu";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
            .withResolverStyle(ResolverStyle.STRICT);

    private final File produtos;
    private final File clientes;
    private final File promocoes;
    private final File produtosObj;
    private final File clientesObj;
    private final File promocoesObj;
    private final File vendasObj;

    /**
     * Construtor
     * Define a localizacao dos ficheiros a usar na leitura e/ou escrita dos dados
     */
    public Ficheiros() {
        this.produtos = new File("./ficheiros\\Produtos.txt");
        this.clientes = new File("./ficheiros\\Clientes.txt");
        this.promocoes = new File("./ficheiros\\Promocoes.txt");
        this.produtosObj = new File("./ficheiros\\Produtos.obj");
        this.clientesObj = new File("./ficheiros\\Clientes.obj");
        this.promocoesObj = new File("./ficheiros\\Promocoes.obj");
        this.vendasObj = new File("./ficheiros\\Vendas.obj");
    }

    /**
     * Método que lê o ficheiro Produtos.obj que contém um ArrayList<Produto>
     * Caso Produtos.obj não exista, o ficheiro Produtos.txt é lido e é feito o
     * parsing dessa informação
     * 
     * @return Lista de produtos lida a partir dos ficheiros
     */
    public ArrayList<Produto> listaProdutos() {
        ArrayList<Produto> p = new ArrayList<>();

        if (!(produtosObj.exists() && produtosObj.isFile())) {
            try (FileReader fr = new FileReader(produtos); BufferedReader br = new BufferedReader(fr);) {
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
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Produto\" nao encontrada");
            }
        }
        return p;
    }

    /**
     * Método que lê o ficheiro Clientes.obj que contém um ArrayList<Cliente>
     * Caso Cliente.obj não exista, o ficheiro Clientes.txt é lido e é feito o
     * parsing dessa informação
     * 
     * @return Lista de clientes lida a partir dos ficheiros
     */
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
                        LocalDate data = LocalDate.parse(string[4], dateTimeFormatter);
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
            try (FileInputStream fis = new FileInputStream(clientesObj);
                    ObjectInputStream ois = new ObjectInputStream(fis);) {
                c = (ArrayList<Cliente>) ois.readObject();
            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Clientes.obj\".");
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Cliente\" nao encontrada");
            }
        }
        return c;
    }

    /**
     * Método que lê o ficheiro Vendas.obj que contém um ArrayList<Venda>
     * Se este ficheiro não existir significa que a loja ainda não foi inaugorada
     * (não há um registo de vendas)
     * 
     * @return Lista de vendas lida a partir do ficheiro Vendas.obj
     */
    public ArrayList<Venda> listaVendas() {
        ArrayList<Venda> v = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(vendasObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            v = (ArrayList<Venda>) ois.readObject();
            ois.close();
        } catch (IOException ioe) {
            System.out.println("Erro ao ler o ficheiro \"Clientes.obj\".");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Classe \"Cliente\" nao encontrada");
        }
        return v;
    }

    /**
     * Método que lê o ficheiro Produtos.obj que contém um ArrayList<Produto>
     * Se este ficheiro não existir significa que a loja ainda não foi inaugorada
     * (não há um registo de promoções)
     * 
     * @return Lista de vendas lida a partir do ficheiros
     */
    public ArrayList<Promocao> listaPromocoes(ArrayList<Produto> produtos, LocalDate dataAtual) {
        ArrayList<Promocao> p = new ArrayList<>();
        if (!(promocoesObj.exists() && promocoesObj.isFile())) {
            try (FileReader fr = new FileReader(promocoes); BufferedReader br = new BufferedReader(fr);) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] string = line.split(";");
                    if (string.length != 4)
                        System.exit(1);
                    string[0] = string[0].strip();
                    boolean id = false;
                    try {
                        string[2] = string[2].replaceAll("\\s+", "");
                        string[3] = string[3].replaceAll("\\s+", "");
                        LocalDate dataInicio = LocalDate.parse(string[2], dateTimeFormatter);
                        LocalDate dataFim = LocalDate.parse(string[3], dateTimeFormatter);
                        string[1] = string[1].toLowerCase();
                        string[1] = string[1].replaceAll("\\s+", "");
                        if (dataInicio.isBefore(dataAtual) && dataFim.isAfter(dataAtual)) {
                            if (string[0].equals("%")) {
                                Promocao pm = new PagaMenos(dataInicio, dataFim,string[1]);
                                p.add(pm);
                                for (Produto produto : produtos) {
                                    if (string[1].equals(produto.getIdentificador())) {
                                        produto.setPromocao(pm);
                                        id = true;
                                    }
                                }
                                if (id == false) {
                                    System.out.println("Identificador de Produto nas Promocoes incorreto!");
                                    System.exit(2);
                                }

                            } else if (string[0].equals("-")) {
                                Promocao pm = new Paga3Leva4(dataInicio, dataFim,string[1]);
                                p.add(pm);
                                for (Produto produto : produtos) {
                                    if (string[1].equals(produto.getIdentificador())) {
                                        produto.setPromocao(pm);
                                        id = true;
                                    }
                                }
                                if (id == false) {
                                    System.out.println("Identificador de Produto nas Promocoes incorreto!");
                                    System.exit(2);
                                }
                            } else {
                                System.out.println(
                                        "erro no tipo de promocao!");
                                System.exit(1);
                            }
                        }

                    } catch (DateTimeParseException e) {
                        System.out.print("Data Invalido\n");
                        System.exit(3);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro de clientes!");
            }
        } else {
            try (FileInputStream fis = new FileInputStream(clientesObj);
                    ObjectInputStream ois = new ObjectInputStream(fis);) {
                p = (ArrayList<Promocao>) ois.readObject();
            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Clientes.obj\".");
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Cliente\" nao encontrada");
            }
        }
        return p;
    }

    /**
     * Escreve a lista de Produtos no ficheiro de objetos
     * 
     * @param lista Lista de registos dos produtos da loja
     */
    public void writeProdutosObj(ArrayList<Produto> lista) {
        if (lista.size() == 0)
            return;
        else {

            try (FileOutputStream fos = new FileOutputStream(produtosObj);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(lista);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Produtos.obj\".");
            }
        }
    }

    /**
     * Escreve a lista de Clietes no ficheiro de objetos
     * 
     * @param lista lista de clientes da loja
     */
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

    /**
     * Escreve a lista de Vendas no ficheiro de objetos
     * 
     * @param lista Lista de Vendas da loja
     */
    public void writeVendasObj(ArrayList<Venda> lista) {
        if (lista.size() == 0) {
            return;
        } else {
            try (FileOutputStream fos = new FileOutputStream(vendasObj);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(lista);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Vendas.obj\".");
            }
        }
    }

    /**
     * Escreve a lista de Promocoes no ficheiro de objetos
     * 
     * @param lista Lista de Promocoes da loja
     */
    public void writePromocoesObj(ArrayList<Promocao> lista) {
        if (lista.size() == 0) {
            return;
        } else {
            try (FileOutputStream fos = new FileOutputStream(promocoesObj);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(lista);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Promocoes.obj\".");
            }
        }
    }
}
