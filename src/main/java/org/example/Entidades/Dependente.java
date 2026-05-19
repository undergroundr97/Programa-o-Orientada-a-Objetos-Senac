package org.example.Entidades;

import org.example.Cobertura.Cobertura;

import java.time.LocalDate;

public class Dependente extends Beneficiario{
    Titular titular;
    public Dependente(String nome, String CPF, LocalDate dataNascimento, Titular titular, Cobertura cobertura ) {
        super(nome, CPF, dataNascimento);
        this.titular = titular;
        this.cobertura = cobertura;
    }

    public Double valorMensalidade(){
        return mensalidadeBase() * 0.70;
    }


}
