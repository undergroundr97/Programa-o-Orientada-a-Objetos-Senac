package Midias;

import Interfaces.Reproduzivel;

public class Musica extends Midia implements Reproduzivel {
    String artista;
    public Musica(String titulo, Integer duracaoEmMinutos, String artista){
        super(titulo, duracaoEmMinutos);
        this.artista = artista;
    }
    @Override
    public void darPlay() {
        System.out.println("Reproduzindo Midia...");
    }

    @Override
    public double calcularCusto() {
        return 2.0;
    }
}
