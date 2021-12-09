package Loja;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Classe que devine as listas de Clientes, Produtos, Vendas e Promocoes
 */
public class Loja {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
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
     * Cria os ArrayList para os Clientes, Produtos, Vendas e Promocoes
     */
    public Loja() {
        clientes = new ArrayList<>();
        vendas = new ArrayList<>();
        produtos = new ArrayList<>();
        promocoes = new ArrayList<>();
    }

    /**
     * Metodo que define a lista de Produtos da Loja
     */
    public void setListaProdutos() {
        if (!(fProdutosObj.exists() && fProdutosObj.isFile())) {
            try (FileReader fr = new FileReader(fProdutos); BufferedReader br = new BufferedReader(fr)) {
                String line;
                int linha = 0;
                while ((line = br.readLine()) != null) {
                    linha++;
                    String[] string = line.split(";");
                    string[0] = string[0].replaceAll("\\s+", "");
                    string[1] = string[1].strip();
                    char[] ref = string[0].toCharArray();
                    if (ref[0] == 'a') {
                        if (string.length != 6) {
                            System.out.printf("Numero de parametros incorretos no ficheiro \"Produtos.txt\" na linha %d(divida os parametros com um ';')\n", linha);
                            System.exit(1);
                        }
                        double preco;
                        int stock;
                        int calorias;
                        int pGordura;
                        try {
                            preco = Double.parseDouble(string[2]);
                            stock = Integer.parseInt(string[3]);
                            calorias = Integer.parseInt(string[4]);
                            pGordura = Integer.parseInt(string[5]);
                            Alimentar a = new Alimentar(string[0], string[1], preco, stock, calorias, pGordura);
                            produtos.add(a);
                        } catch (Exception e) {
                            System.out.printf("Erro num dos parrametros [2:5] na linha %d do ficheiro \"Produtos.txt\"\n",linha);
                            System.exit(1);
                        }
                    } else if (ref[0] == 'l') {
                        if (string.length != 5) {
                            System.out.printf("Numero de parametros incorretos no ficheiro \"Produtos.txt\" na linha %d(divida os parametros com um ';')\n",linha);
                            System.exit(1);
                        }
                        double preco;
                        int stock;
                        int toxidade;
                        try {
                            preco = Double.parseDouble(string[2]);
                            stock = Integer.parseInt(string[3]);
                            toxidade = Integer.parseInt(string[4]);
                            Limpeza limpeza = new Limpeza(string[0], string[1], preco, stock, toxidade);
                            produtos.add(limpeza);
                        } catch (Exception e) {
                            System.out.printf("Erro num dos parrametros [2:4] na linha %d do ficheiro \"Produtos.txt\"\n",linha);
                            System.exit(1);
                        }

                    } else if (ref[0] == 'm') {
                        if (string.length != 8) {
                            System.out.printf("Numero de parametros incorretos no ficheiro \"Produtos.txt\" na linha %d(divida os parametros com um ';')\n",linha);
                            System.exit(1);
                        }
                        int[] dimensao = new int[3];
                        int stock;
                        double preco;
                        double peso;
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
                            System.out.printf("Erro num dos parrametros [2:7] na linha %d do ficheiro \"Produtos.txt\"\n",linha);
                            System.exit(1);
                        }
                    } else {
                        System.out.printf("Erro no parametro 0 na linha %d  do ficheiro \"Produtos.txt\"\n", linha);
                        System.exit(3);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro \"Produtos.txt\"!\n");
                System.exit(4);
            }
            writeProdutosObj();
        } else {
            try (FileInputStream fis = new FileInputStream(fProdutosObj);
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                produtos = (ArrayList<Produto>) ois.readObject();
            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Produto.obj\".");
                System.exit(2);
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Produto\" nao encontrada");
                System.exit(1);
            }
        }

    }

    /**
     * Metodo que define a lista de Clientes da Loja
     */
    public void setListaClientes() {
        if (!(fClientesObj.exists() && fClientesObj.isFile())) {
            try (FileReader fr = new FileReader(fClientes); BufferedReader br = new BufferedReader(fr)) {
                String line;
                int linha = 0;
                while ((line = br.readLine()) != null) {
                    linha++;
                    String[] string = line.split(";");
                    if (string.length != 6){
                        System.out.printf("Numero de parametros incorretos no ficheiro \"Clientes.txt\" na linha %d(divida os parametros com um ';')\n",linha);
                        System.exit(1);
                    }

                    string[0] = string[0].strip();
                    string[1] = string[1].strip();
                    int telefone;
                    if (!string[3].contains("@")) {
                        System.out.printf("Email Invalido na linha %d!\n",linha);
                        System.exit(1);
                    }
                    string[3] = string[3].replaceAll("\\s+", "");
                    string[5] = string[5].toLowerCase();
                    string[5] = string[5].replaceAll("\\s+", "");
                    try {
                        telefone = Integer.parseInt(string[2]);
                        LocalDate data = LocalDate.parse(string[4], dateTimeFormatter);
                        boolean frequente  = false;
                        if (string[5].equals("true")) {
                            frequente = true;
                        } else if (!string[5].equals("false")) {
                            System.out.printf(
                                    "Erro no Cliente ser frequente ou nao na linha %d, verifique se tem true ou false no ultimo parametro!\n",linha);
                            System.exit(1);
                        }
                        Cliente cl = new Cliente(string[0], string[1], telefone, string[3], data, frequente);
                        clientes.add(cl);
                    } catch (DateTimeParseException e) {
                        System.out.printf("Data Invalido na linha %d(formato: dd/mm/aaaa)\n",linha);
                        System.exit(1);
                    } catch (Exception e) {
                        System.out.printf("Telefone de um cliente invalido na linha %d!\n",linha);
                        System.exit(1);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro de clientes!");
                System.exit(2);
            }
            writeClientesObj();
        } else {
            try (FileInputStream fis = new FileInputStream(fClientesObj);
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                clientes = (ArrayList<Cliente>) ois.readObject();
            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Clientes.obj\".");
                System.exit(2);
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Cliente\" nao encontrada");
                System.exit(1);
            }
        }
    }

    /**
     * Metodo que define a lista de Vendas da Loja
     */
    public void setListaVendas() {
        if (!(fVendasObj.exists() && fVendasObj.isFile())) {
            System.out.println("Ainda nao foi inaugurada a loja!");
        } else {
            try (FileInputStream fis = new FileInputStream(fVendasObj);
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                vendas = (ArrayList<Venda>) ois.readObject();
            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Clientes.obj\".");
                System.exit(2);
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Cliente\" nao encontrada");
                System.exit(1);
            }
        }
    }

    /**
     * Metodo que define a lista de Promcoes da Loja
     */
    public void setListaPromocoes(LocalDate dataAtual) {
        if (!(fPromocoesObj.exists() && fPromocoesObj.isFile())) {
            try (FileReader fr = new FileReader(fPromocoes); BufferedReader br = new BufferedReader(fr)) {
                String line;
                int linha = 0;
                while ((line = br.readLine()) != null) {
                    linha++;
                    String[] string = line.split(";");
                    if (string.length != 4){
                        System.out.printf("Numero de parametros incorretos no ficheiro \"Promoçoes.txt\" na linha %d(divida os parametros com um ';')\n",linha);
                        System.exit(1);
                    }

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
                        Produto p = null;
                        for (Produto produto : produtos)
                            if (string[1].equals(produto.getIdentificador())) {
                                id = true;
                                p = produto;
                                break;
                            }
                        if (!id) {
                            System.out.printf("Identificador de Produto nas Promocoes incorreto na linha %d!\n",linha);
                            System.exit(2);
                        }
                            if (string[0].equals("%") && (s[0].equals("m") || s[0].equals("a") || s[0].equals("l"))) {
                                PagaMenos pagaM = new PagaMenos(dataInicio, dataFim, string[1]);
                                if ((dataInicio.isBefore(dataAtual) || dataInicio.isEqual(dataAtual)) && (dataFim.isAfter(dataAtual) || dataFim.isEqual(dataAtual)))
                                    p.setPromocao(pagaM);
                                promocoes.add(pagaM);
                            } else if (string[0].equals("-")
                                    && (s[0].equals("m") || s[0].equals("a") || s[0].equals("l"))) {
                                Paga3Leva4 pagaL = new Paga3Leva4(dataInicio, dataFim, string[1]);
                                promocoes.add(pagaL);
                                if ((dataInicio.isBefore(dataAtual) || dataInicio.isEqual(dataAtual)) && (dataFim.isAfter(dataAtual) || dataFim.isEqual(dataAtual)))
                                    p.setPromocao(pagaL);
                            } else {
                                System.out.printf(
                                        "erro no tipo de promocao ou no tipo de produto na linha %d!\n",linha);
                                System.exit(1);
                            }
                    } catch (DateTimeParseException e) {
                        System.out.printf("Data Invalido no ficheiro \"Promocoes.txt\" na linha %d\n",linha);
                        System.exit(3);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro de clientes!");
                System.exit(1);
            }
            writePromocoesObj();
        } else {
            try (FileInputStream fis = new FileInputStream(fPromocoesObj);
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                promocoes = (ArrayList<Promocao>) ois.readObject();
                for (Promocao p : promocoes) {
                    if ((p.getDataInicio().isBefore(dataAtual) || p.getDataInicio().isEqual(dataAtual)) && (p.getDataFim().isAfter(dataAtual) || p.getDataFim().isEqual(dataAtual)))
                        for (Produto produto : produtos) {
                            if (p.getId().equals(produto.getIdentificador())) {
                                produto.setPromocao(p);
                                break;
                            }
                        }
                }

            } catch (IOException ioe) {
                System.out.println("Erro ao ler o ficheiro \"Promocoes.obj\".");
                System.exit(2);
            } catch (ClassNotFoundException cnf) {
                System.out.println("Classe \"Promocao\" nao encontrada");
                System.exit(1);
            }
        }
    }

    /**
     * Escreve a lista de Produtos no ficheiro de objetos
     */
    public void writeProdutosObj() {
        if (produtos.size() != 0) {
            try (FileOutputStream fos = new FileOutputStream(fProdutosObj);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(produtos);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Produtos.obj\".");
            }
        }
    }

    /**
     * Escreve a lista de Clietes no ficheiro de objetos
     */
    public void writeClientesObj() {
        if (clientes.size() != 0){
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
     */
    public void writeVendasObj() {
        if (vendas.size() != 0) {
            try (FileOutputStream fos = new FileOutputStream(fVendasObj);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(vendas);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Vendas.obj\".");
            }
        }
    }

    /**
     * Escreve a lista de Promocoes no ficheiro de objetos
     */
    public void writePromocoesObj() {
        if (promocoes.size() != 0) {
            try (FileOutputStream fos = new FileOutputStream(fPromocoesObj);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(promocoes);
            } catch (IOException ioe) {
                System.out.println("Erro ao escrever no ficheiro \"Promocoes.obj\".");
            }
        }
    }

    /**
     * Metodo que atualiza as Promocoes se o dia mudar
     * @param dataAtual data a verificar as promocoes
     */
    public void atualizaPromocoes(LocalDate dataAtual){
        for(Produto p: produtos) {
            if (p.getPromocao() != null) {
                if (!((p.getPromocao().getDataInicio().isBefore(dataAtual) || p.getPromocao().getDataInicio().isEqual(dataAtual)) && (p.getPromocao().getDataFim().isAfter(dataAtual) || p.getPromocao().getDataFim().isEqual(dataAtual)))) {
                    p.setPromocao(null);
                }
                else continue;
            }
            for(Promocao promocao : promocoes){
                if((promocao.getDataInicio().isBefore(dataAtual) || promocao.getDataInicio().isEqual(dataAtual)) && (promocao.getDataFim().isAfter(dataAtual) || promocao.getDataFim().isEqual(dataAtual)) && promocao.getId().equals(p.getIdentificador())){
                    p.setPromocao(promocao);
                }
            }
        }
    }

    /**
     * Metodo que adiciona um cliente a lista de Clientes
     * 
     * @param c cliente a adicionar a lista
     */
    public void adicionaCliente(Cliente c) {
        clientes.add(c);
    }

    /**
     * Metodo que imprime a lista de clientes
     */
    public void listaClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
            System.out.println("-------------");
        }
    }

    /**
     * Metodo que adiciona uma venda a lista de Vendas
     * 
     * @param v venda a adicionar a lista
     */
    public void adicionaVenda(Venda v) {
        vendas.add(v);
    }

    /**
     * Metodo que imprime a lista de Produtos
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
     * Metodo que calcula o stock total da loja
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
     * Metodo que faz o login de um cliente na loja
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
     * Metodo que imprime a lista de Vendas
     */
    public void listaVendas() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        if (vendas.size() != 0)
            for (Venda venda : vendas) {
                System.out.println(
                        venda + "Preço produtos: " + df.format(venda.total()) + "€" + "\nPreço transporte: " + venda.precoTransporte()
                                + "€" + "\nPreço total: " + df.format(venda.total() + venda.precoTransporte()) + "€");
                System.out.println("-------------");
            }
        else
            System.out.println("Nao ha registo de vendas.");
    }

    /**
     * Metodo que devolve um produto da lista de Produtos
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
