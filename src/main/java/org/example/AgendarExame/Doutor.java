package org.example.AgendarExame;

public class Doutor {
    private String nome;
    private String especializacao;

    public Doutor(String nome, String especializacao){
        this.nome = nome;
        this.especializacao = especializacao;
    }

    public String getNome() {
        return nome;
    }
    public String getEspecializacao() {
        return especializacao;
    }
}
