package org.example.AgendarExame;

import org.example.Cobertura.Cobertura;

import java.time.LocalDate;

public class Exame {
    private String nomeSolicitante;
    private LocalDate dataDoExame;
    private Cobertura tipoExame;
    private LocalDate dataSaida;
    public Exame(String nomeSolicitante, Cobertura tipoExame, LocalDate dataDoExame){
        this.nomeSolicitante = nomeSolicitante;
        this.tipoExame = tipoExame;
        this.dataDoExame = dataDoExame;
    }
    public Exame(String nomeSolicitante, Cobertura tipoExame, LocalDate dataDoExame, LocalDate dataSaida){
        this.nomeSolicitante = nomeSolicitante;
        this.tipoExame = tipoExame;
        this.dataDoExame = dataDoExame;
        this.dataSaida = dataSaida;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public LocalDate getDataDoExame() {
        return dataDoExame;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public Cobertura getTipoExame() {
        return tipoExame;
    }
}
