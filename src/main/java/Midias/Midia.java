package Midias;

import Utilitario.ConversorTempo;

public abstract class Midia {
    private String titulo;
    private Integer duracaoEmMinutos;

    public Midia(String titulo, Integer duracaoEmMinutos){
        this.titulo = titulo;
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public  void exibirDetalhes(){
        System.out.println("O titulo da midia e: " + titulo);
        ConversorTempo.formatarMinutos(this.duracaoEmMinutos);
    }
    public abstract double calcularCusto();


}
