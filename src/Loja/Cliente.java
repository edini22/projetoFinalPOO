package Loja;
import java.time.LocalDate;
import java.util.*;

public class Cliente {
    private String nome;
    private String morada;
    private int telefone;
    private String email;
    private LocalDate dataNascimento;
    private boolean frequente;

    public Cliente(String nome, String morada, int telefone, String email, LocalDate dataNascimento, boolean frequente) {
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.frequente = frequente;
    }

    public String getmail(){
        return email;
    }

    public String toString() {
        return "Nome: " + nome + "\nMorada: " + morada + "\nTelefone: " + telefone + "\nEmail: " + email
                + "\n Data de nascimento: " + dataNascimento;
    }
}