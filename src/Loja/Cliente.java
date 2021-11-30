package Loja;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Cliente implements Serializable{
    private final String nome;
    private final String morada;
    private final int telefone;
    private final String email;
    private final LocalDate dataNascimento;
    private boolean frequente;

    /**
     * Construtor
     * @param nome Nome do cliente
     * @param morada Morada do cliente
     * @param telefone Telefone do cliente
     * @param email Email do cliente
     * @param dataNascimento Data de nascimento do cliente
     * @param frequente Frequencia do cliente
     */
    public Cliente(String nome, String morada, int telefone, String email, LocalDate dataNascimento, boolean frequente) {
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.frequente = frequente;
    }

    /**
     * Get method
     * @return email
     */
    public String getmail(){
        return email;
    }

    /**
     *
     * @return String que contem os dados do cliente
     */
    public String toString() {
        return "Nome: " + nome + "\nMorada: " + morada + "\nTelefone: " + telefone + "\nEmail: " + email
                + "\nData de nascimento: " + dataNascimento;
    }
}