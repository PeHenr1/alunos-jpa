package br.edu.ifsp.aissa.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String ra;
    private String email;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;

    public Aluno() {}

    public Aluno(String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public BigDecimal getNota1() { return nota1; }
    public void setNota1(BigDecimal nota1) { this.nota1 = nota1; }
    public BigDecimal getNota2() { return nota2; }
    public void setNota2(BigDecimal nota2) { this.nota2 = nota2; }
    public BigDecimal getNota3() { return nota3; }
    public void setNota3(BigDecimal nota3) { this.nota3 = nota3; }

    public BigDecimal calcularMedia() {
        return nota1.add(nota2).add(nota3).divide(BigDecimal.valueOf(3), 2, BigDecimal.ROUND_HALF_UP);
    }

    public String getSituacao() {
        BigDecimal media = calcularMedia();
        if (media.compareTo(BigDecimal.valueOf(4)) < 0) {
            return "Reprovado";
        } else if (media.compareTo(BigDecimal.valueOf(6)) < 0) {
            return "Recuperação";
        } else {
            return "Aprovado";
        }
    }
}
