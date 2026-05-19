package org.example.Entidades;

import java.time.LocalDate;

public class Dependente extends Beneficiario{
    Titular titular;
    public Dependente(String nome, String CPF, LocalDate dataNascimento, Titular titular) {
        super(nome, CPF, dataNascimento);
        this.titular = titular;
    }

    public Double valorMensalidade(){
        return mensalidadeBase() * 0.70;
    }


}
