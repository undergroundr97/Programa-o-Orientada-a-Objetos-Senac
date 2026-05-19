package org.example.Entidades;

import java.time.LocalDate;

public class Aposentado extends Beneficiario{
    public Aposentado(String nome, String CPF, LocalDate dataNascimento) {
        super(nome, CPF, dataNascimento);
    }
    public Double valorMensalidade(){
        return mensalidadeBase() * 0.80;
    }

}
