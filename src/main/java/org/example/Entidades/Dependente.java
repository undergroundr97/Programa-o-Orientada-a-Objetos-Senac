package org.example.Entidades;

import org.example.Enums.Cobertura;
import org.example.Enums.TipoDependente;

import java.time.LocalDate;

public class Dependente extends Beneficiario{
    private Titular titular;
    private TipoDependente tipoDependente;
    public Dependente(String nome, String CPF, LocalDate dataNascimento, Titular titular, Cobertura cobertura, TipoDependente tipoDependente) {
        super(nome, CPF, dataNascimento);
        this.titular = titular;
        this.cobertura = cobertura;
        this.tipoDependente = tipoDependente;
    }

    public Double valorMensalidade(){
        return mensalidadeBase() * 0.70;
    }

    public TipoDependente getTipoDependente() {
        return tipoDependente;
    }
}
