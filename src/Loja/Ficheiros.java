package Loja;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class Ficheiros {
    private File produtos ;
    public Ficheiros(){
        produtos = new File("./ficheiros\\Produtos.txt");
    }
    public List<Produto> setLista(){
        List<Produto> p = new ArrayList<>();
    try (FileReader fr = new FileReader(produtos); BufferedReader br = new BufferedReader(fr);) {//TODO verificar se nao existe o ficheiro de objetos!
        String line;
        while ((line = br.readLine()) != null) {
            line.trim();
            String[] string = line.split(";");
            string[0] = string[0].replaceAll("\\s+", "");
            string[1] = string[1].strip();
            char[] ref = string[0].toCharArray();
            if (ref[0] == 'a') {
                if(string.length != 6) System.exit(1);
                double preco = 0;
                int stock = 0;
                int calorias = 0;
                int pGordura = 0;
                try {
                    preco = Double.parseDouble(string[2]);
                    stock = Integer.parseInt(string[3]);
                    calorias = Integer.parseInt(string[4]);
                    pGordura = Integer.parseInt(string[5]);

                } catch (Exception e) {
                    System.out.println("erro!");
                }
                Alimentar a = new Alimentar(string[0], string[1], preco, stock, calorias, pGordura);
                p.add(a);
            }
            else if(ref[0] == 'l'){
                if(string.length != 5) System.exit(1);
                double preco = 0;
                int stock = 0;
                int toxidade = 0;
                try {
                    preco = Double.parseDouble(string[2]);
                    stock = Integer.parseInt(string[3]);
                    toxidade = Integer.parseInt(string[4]);

                } catch (Exception e) {
                    System.out.println("erro!");
                }
                Limpeza l = new Limpeza(string[0], string[1], preco, stock, toxidade);
                p.add(l);
            }
            else if(ref[0] == 'm'){
                if(string.length != 8) System.exit(1);
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
                } catch (Exception e) {
                    System.out.println("erro!");
                }
                Mobiliario m = new Mobiliario(string[0], string[1], preco, stock, peso, dimensao);
                p.add(m);
            }
            else System.out.println("errou a referencia");

        }
    } catch (IOException e) {
        System.out.println("Erro ao ler o ficheiro!");
    }
    return p;
}
}
