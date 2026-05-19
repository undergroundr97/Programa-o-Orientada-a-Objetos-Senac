package org.example.Entidades;

import org.example.Cobertura.Cobertura;

import java.time.LocalDate;
import java.time.Year;
import java.util.Date;

public abstract class Beneficiario {
    private String nome;
    private String CPF;
    private LocalDate dataNascimento;
    Cobertura cobertura = Cobertura.TOTAL;

    public Beneficiario(String nome, String  CPF, LocalDate dataNascimento){
        this.nome = nome;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double mensalidadeBase(){
        Integer year =  Year.now().getValue();
        Integer anoNascimento = dataNascimento.getYear();
        if( (year - anoNascimento) < 18 ){
            return 180.00;
        } else if ( (year - anoNascimento) >= 18 && (year-anoNascimento ) < 59){
            return 340.00;
        } else {
            return 620.00;
        }
    }

}
