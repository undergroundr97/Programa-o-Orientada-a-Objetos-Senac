package org.example.AgendarExame;

import org.example.Enums.Cobertura;

import java.time.LocalDate;

public class Exame {
    private String nomeSolicitante;
    private LocalDate dataDoExame;
    private Cobertura tipoExame;
    private LocalDate dataSaida;
    private Doutor doutor;

    public Exame(String nomeSolicitante, Cobertura tipoExame, LocalDate dataDoExame, Doutor doutor){
        this.nomeSolicitante = nomeSolicitante;
        this.tipoExame = tipoExame;
        this.dataDoExame = dataDoExame;
        this.doutor = doutor;
    }

    public Exame(String nomeSolicitante, Cobertura tipoExame, LocalDate dataDoExame, LocalDate dataSaida){
        this.nomeSolicitante = nomeSolicitante;
        this.tipoExame = tipoExame;
        this.dataDoExame = dataDoExame;
        this.dataSaida = dataSaida;
    }

    public Doutor getDoutor() {
        return doutor;
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
