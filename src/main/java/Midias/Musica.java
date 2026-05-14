package Midias;

import Interfaces.Reproduzivel;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Musica extends Midia implements Reproduzivel {
    String artista;
    public Musica(String titulo, Integer duracaoEmMinutos, String artista){
        super(titulo, duracaoEmMinutos);
        this.artista = artista;
    }
    @Override
    public void darPlay() {
        ArrayList<String> notas = new ArrayList<>();
//        notas.add("\u2669");
//        notas.add("\u266A");
//        notas.add("\u266B");
//        notas.add("\u266C");
//        notas.add("\u266F");
        notas.add("\u2588");
        notas.add("\u2587");
        notas.add("\u2586");
        notas.add("\u2585");
        notas.add("\u2582");
        notas.add("\u2583");
        notas.add(" ");
        notas.add(" ");
        System.out.println("Tocando musica");
        try {
            PrintStream out16 = new PrintStream(System.out, true, "UTF-8");
            for (int i = 0; i < 50; i++) {
                int random = (int) (Math.random() * notas.size());
                String toPrint = notas.get(random);
                out16.print(toPrint);
                Thread.sleep(250);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double calcularCusto() {
        return 2.0;
    }
}
