package org.example.Entidades;

import org.example.Cobertura.Cobertura;

import java.time.LocalDate;
import java.util.ArrayList;

public class Titular extends Beneficiario{
    private Boolean aposentado = false;

    private ArrayList<Dependente> listaDependentes = new ArrayList<>();
    public Titular(String nome, String CPF, LocalDate dataNascimento, Cobertura cobertura) {
        super(nome, CPF, dataNascimento);
        this.cobertura = cobertura;
    }

    public void adicionarDependente(Dependente dependente){
        if(listaDependentes.size() < 3){
            listaDependentes.add(dependente);
        } else {
            System.out.println("Você atingiu o numero máximo de dependentes");
        }
    }

    public ArrayList<Dependente> getListaDependentes() {
        return listaDependentes;
    }

    public Double valorMensalidade(){
        if(aposentado) {
            return mensalidadeBase() * 0.80;
        } else {
            return mensalidadeBase();
        }
    }

}
