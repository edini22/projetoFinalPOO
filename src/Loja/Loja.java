package Loja;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Classe que devine as listas de Clientes, Produtos, Vendas e Promoções
 */
public class Loja {
    private String dateFormat = "dd/MM/uuuu";
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
            .withResolverStyle(ResolverStyle.STRICT);
    private final File fProdutos = new File("./ficheiros\\Produtos.txt");
    private final File fClientes = new File("./ficheiros\\Clientes.txt");
    private final File fPromocoes = new File("./ficheiros\\Promocoes.txt");
    private final File fProdutosObj = new File("./ficheiros\\Produtos.obj");
    private final File fClientesObj = new File("./ficheiros\\Clientes.obj");
    private final File fPromocoesObj = new File("./ficheiros\\Promocoes.obj");
    private final File fVendasObj = new File("./ficheiros\\Vendas.obj");
    private ArrayList<Cliente> clientes;
    private ArrayList<Venda> vendas;
    private ArrayList<Produto> produtos;
    private ArrayList<Promocao> promocoes;

    /**
     * Construtor
     * Cria os ArrayList para os Clientes, Produtos, Vendas e Promoções
     */
    public Loja() {
        clientes = new ArrayList<>();
        vendas = new ArrayList<>();
        produtos = new ArrayList<>();
        promocoes = new ArrayList<>();
    }

    /**
     * Get Method
     * 
     * @return Lista de Clientes
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Get Method
     * 
     * @return Lista de Produtos
     */
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Get Method
     * 
     * @return Lista de Vendas
     */
    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    /**
     * Get Method
     * 
     * @return Lista de Promoções
     */
    public ArrayList<Promocao> getPromocoes() {
        return promocoes;
    }

    /**
     * Metodo que define a lista de Produtos da Loja
     * 
     * @param l lista de produtos
     */
    public void setListaProdutos() {
        if (!(fProdutosObj.exists() && fProdutosObj.isFile())) {
            try (FileReader fr = new FileReader(fProdutos); BufferedReader br = new BufferedReader(fr);) {
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
                            produtos.add(a);
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
                            Limpeza limpeza = new Limpeza(string[0], string[1], preco, stock, toxidade);
                            produtos.add(limpeza);
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
                            produtos.add(m);
                        } catch (Exception e) {
                            System.out.println("erro!");
                        }
                    } else
                        System.out.println("errou a referencia");

                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro de Produtos!");
            }
            writeProdutosObj();
        } else {
            try (FileInputStream fis = new FileInputStream(fProdutosObj);
                    ObjectInputStream ois = new ObjectInputStream(fis);) {
                produtos = (ArrayList<Produto>) ois.readObject();
            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Produto.obj\".");
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Produto\" nao encontrada");
            }
        }

    }

    /**
     * Metodo que define a lista de Clientes da Loja
     * 
     * @param l lista de produtos
     */
    public void setListaClientes() {
        if (!(fClientesObj.exists() && fClientesObj.isFile())) {
            try (FileReader fr = new FileReader(fClientes); BufferedReader br = new BufferedReader(fr);) {
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
                    clientes.add(cl);
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro de clientes!");
            }
            writeClientesObj();
        } else {
            try (FileInputStream fis = new FileInputStream(fClientesObj);
                    ObjectInputStream ois = new ObjectInputStream(fis);) {
                clientes = (ArrayList<Cliente>) ois.readObject();
            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Clientes.obj\".");
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Cliente\" nao encontrada");
            }
        }
    }

    /**
     * Metodo que define a lista de Vendas da Loja
     * 
     * @param l lista de produtos
     */
    public void setListaVendas() {
        if (!(fVendasObj.exists() && fVendasObj.isFile())) {
            System.out.println("Ainda nao foi inaugurada a loja!");
        } else {
            try (FileInputStream fis = new FileInputStream(fVendasObj);
                    ObjectInputStream ois = new ObjectInputStream(fis);) {
                vendas = (ArrayList<Venda>) ois.readObject();
            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Clientes.obj\".");
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Cliente\" nao encontrada");
            }
        }
    }

    /**
     * Metodo que define a lista de Promções da Loja
     * 
     * @param l lista de produtos
     */
    public void setListaPromocoes(LocalDate dataAtual) {
        if (!(fPromocoesObj.exists() && fPromocoesObj.isFile())) {
            try (FileReader fr = new FileReader(fPromocoes); BufferedReader br = new BufferedReader(fr);) {
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
                        String[] s = string[1].split("");
                        if (dataInicio.isBefore(dataAtual) && dataFim.isAfter(dataAtual)) {
                            if (string[0].equals("%") && (s[0].equals("m") || s[0].equals("a") || s[0].equals("l"))) {
                                Promocao pm = new PagaMenos(dataInicio, dataFim, string[1]);
                                promocoes.add(pm);
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

                            } else if (string[0].equals("-")
                                    && (s[0].equals("m") || s[0].equals("a") || s[0].equals("l"))) {
                                Promocao pm = new Paga3Leva4(dataInicio, dataFim, string[1]);
                                promocoes.add(pm);
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
            writePromocoesObj();
        } else {
            try (FileInputStream fis = new FileInputStream(fPromocoesObj);
                    ObjectInputStream ois = new ObjectInputStream(fis);) {
                promocoes = (ArrayList<Promocao>) ois.readObject();
                for (Promocao p : promocoes) {
                    if (p.getDataInicio().isBefore(dataAtual) && p.getDataFim().isAfter(dataAtual))
                        for (Produto produto : produtos) {
                            if (p.getId().equals(produto.getIdentificador())) {
                                produto.setPromocao(p);
                                break;
                            }
                        }
                }

            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Promocoes.obj\".");
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Promocao\" nao encontrada");
            }
        }
    }

    /**
     * Escreve a lista de Produtos no ficheiro de objetos
     * 
     * @param lista Lista de registos dos produtos da loja
     */
    public void writeProdutosObj() {
        if (produtos.size() == 0)
            return;
        else {
            try (FileOutputStream fos = new FileOutputStream(fProdutosObj);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(produtos);
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
    public void writeClientesObj() {
        if (clientes.size() == 0)
            return;
        else {
            try (FileOutputStream fos = new FileOutputStream(fClientesObj);
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(clientes);
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
    public void writeVendasObj() {
        if (vendas.size() == 0) {
            return;
        } else {
            try (FileOutputStream fos = new FileOutputStream(fVendasObj);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(vendas);
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
    public void writePromocoesObj() {
        if (promocoes.size() == 0) {
            return;
        } else {
            try (FileOutputStream fos = new FileOutputStream(fPromocoesObj);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(promocoes);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Promocoes.obj\".");
            }
        }
    }

    /**
     * Método que adiciona um cliente à lista de Clientes
     * 
     * @param c cliente a adicionar a lista
     */
    public void adicionaCliente(Cliente c) {
        clientes.add(c);
    }

    /**
     * Método que imprime a lista de clientes
     */
    public void listaClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
            System.out.println("-------------");
        }
    }

    /**
     * Método que adiciona uma venda à lista de Vendas
     * 
     * @param v venda a adicionar a lista
     */
    public void adicionaVenda(Venda v) {
        vendas.add(v);
    }

    /**
     * Método que adiciona um produto à lista de Produtos
     * 
     * @param p produto a adicionar a lista
     */
    public void adicionaProduto(Produto p) {
        produtos.add(p);
    }

    /**
     * Método que imprime a lista de Produtos
     */
    public void listarProdutos() {
        final String YELLOW = "\033[1;93m";
        final String RESET = "\033[0m";
        for (Produto produto : produtos) {
            if (produto.getStock() != 0) {
                if (produto.getPromocao() != null) {
                    System.out.print(YELLOW + produto.getIdentificador() + "\t");
                    System.out.println(produto + " \"" + produto.getPromocao().tipoPromocao() + "\"" + RESET);
                } else {
                    System.out.print(produto.getIdentificador() + "\t");
                    System.out.println(produto);
                }
            }
        }
    }

    /**
     * Método que calcula o stock total da loja
     * 
     * @return stock total
     */
    public int stock() {
        int quant = 0;
        for (Produto produto : produtos) {
            if (produto.getStock() != 0)
                quant += produto.getStock();
        }
        return quant;
    }

    /**
     * Método que faz o login de um cliente na loja
     * 
     * @param email email do cliente a dar login
     * @return c cliente que vai dar login
     */
    public Cliente efetuarLogin(String email) {
        Cliente c = null;
        for (Cliente cliente : clientes) {
            if (cliente.getMail().equals(email)) {
                c = cliente;
                break;
            }
        }
        return c;
    }

    /**
     * Meétodo que imprime a lista de Vendas
     */
    public void listaVendas() {
        if (vendas.size() != 0)
            for (Venda venda : vendas) {
                System.out.println(
                        venda + "Preço produtos: " + venda.total() + "\nPreço transporte: " + venda.precoTransporte()
                                + "\nPreço total: " + (venda.total() + venda.precoTransporte()) + "€");
                System.out.println("-------------");
            }
        else
            System.out.println("Nao registo de vendas.");
    }

    /**
     * Método que devolve um produto da lista de Produtos
     * 
     * @param id identicador do produdo
     * @return produto
     */
    public Produto find(String id) {
        Produto prod;
        for (Produto p : produtos) {
            if (p.getIdentificador().equals(id)) {
                prod = p;
                return prod;
            }
        }
        return null;
    }

}