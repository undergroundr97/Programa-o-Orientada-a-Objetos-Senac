package org.example;


import Interfaces.Baixavel;
import Interfaces.Reproduzivel;
import Midias.Filme;
import Midias.Musica;

public class Main {
    static void main() {
        Filme filme = new Filme("A casa dos mortos", 139, "4K");
        Musica musica = new Musica("Um minuto para o fim do mundo", 5, "CPM22");

        processarPlayer(filme);

        processarPlayer(musica);

        processarDownlaod(filme);


    }

    static void processarPlayer(Reproduzivel item){
        item.darPlay();
    }
    static void processarDownlaod(Baixavel item){
        item.realizarDownload();
    }

}
