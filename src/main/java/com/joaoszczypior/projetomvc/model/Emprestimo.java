package com.joaoszczypior.projetomvc.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Emprestimo {
    private String nomeTitular;
    private String nomeLivro;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataEmprestimo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataDevolucao;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataDevolvido;

    public Emprestimo() {
    }

    public Emprestimo(String nomeTitular, String nomeLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolvido) {
        this.nomeTitular = nomeTitular;
        this.nomeLivro = nomeLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataEmprestimo != null ? dataEmprestimo.plusDays(5) : null;
        this.dataDevolvido = dataDevolvido;
    }

    public boolean registrarRecebimentoDevolucao(){
        this.dataDevolucao = LocalDateTime.now();
        return true;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDateTime getDataDevolvido() {
        return dataDevolvido;
    }

    public void setDataDevolvido(LocalDateTime dataDevolvido) {
        this.dataDevolvido = dataDevolvido;
    }
}
