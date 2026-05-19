package org.example.AgendarExame;

import org.example.Cobertura.Cobertura;

import java.time.LocalDate;

public class Exame {
    private String nomeSolicitante;
    private LocalDate dataDoExame;
    private Cobertura tipoExame;
    public Exame(String nomeSolicitante, Cobertura tipoExame, LocalDate dataDoExame){
        this.nomeSolicitante = nomeSolicitante;
        this.tipoExame = tipoExame;
        this.dataDoExame = dataDoExame;
    }
}
