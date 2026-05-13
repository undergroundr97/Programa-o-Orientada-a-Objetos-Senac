package Midias;

import Interfaces.Baixavel;
import Interfaces.Reproduzivel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Filme extends Midia implements Reproduzivel, Baixavel {
    private String qualidade;
    public Filme(String titulo, Integer duracaoEmMinutos, String qualidade){
        super(titulo,duracaoEmMinutos);
        this.qualidade = qualidade;
    }



    @Override
    public void realizarDownload() {
        File file = new File("./src/main/java/Interfaces/Download");
        File downloadedFile = new File(file, "Download.txt");
        if(!file.exists()){
            file.mkdir();
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(downloadedFile))){
            for (int i = 0; i < 100 ; i++) {
                double random = (int) (Math.random()  * 13);
                if(i >=90){
                    System.out.println("DOWNLOAD CONFLUIDO!");
                    break;
                }
                i += random;
                System.out.println(i + "%...");
                Thread.sleep(500);
            }
            bw.append("DownloadConcluido :D");
        } catch (IOException e){

            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void darPlay() {
        System.out.println("Reproduzindo Midia...");
    }

    @Override
    public double calcularCusto() {
        if(qualidade.equals("4K")){
            return 15;
        } else {
            return 10;
        }
    }
}
