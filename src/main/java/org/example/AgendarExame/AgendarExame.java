package org.example.AgendarExame;

import org.example.Cobertura.Cobertura;
import org.example.Entidades.Beneficiario;
import org.example.InputValidator.InputValidator;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.example.VitaCare.VitaCare.formatter;

public class AgendarExame {

    public static List<Exame> listaExames = new ArrayList<>();
    static {
        LocalDate date = LocalDate.of(2020, 05, 15);
        Exame exame = new Exame("vitor", Cobertura.CONSULTA, date);
        listaExames.add(exame);
    }
    public static List<Exame> getListaExames() {
        return listaExames;
    }

    public static void criarExame(Beneficiario beneficiario){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Bem vindo " + beneficiario.getNome() + " ao agendamento de exames!");
        System.out.println("Sua cobertura e: " + beneficiario.getCobertura());
        switch(beneficiario.getCobertura()){
            case Cobertura.TOTAL -> {
                    System.out.println("Você pode realizar Exames e Consultas!");
                    System.out.println("O que quer agendar?");
                    System.out.println("1 - EXAME");
                    System.out.println("2 - CONSULTA");
                    System.out.println("3 - Cancelar");
                    Integer beneficiarioEscolha = InputValidator.getClienteInput();
                    if(beneficiarioEscolha.equals(3)){
                        return;
                    }
                    while(beneficiarioEscolha < 1  || beneficiarioEscolha > 3) {
                        System.out.println("Por favor, escolha 1, 2 ou 3");
                        beneficiarioEscolha = InputValidator.getClienteInput();
                    }
                    String escolhaStringExame;
                    if(beneficiarioEscolha == 1){
                        escolhaStringExame = "Exame";
                    } else {
                        escolhaStringExame = "Consulta";
                    }
                    System.out.println("Você esta agendando um: " + escolhaStringExame+ ".");
                    System.out.println("Datas disponíveis para realização do " + escolhaStringExame+ ":");
                    LocalDate dataHoje = LocalDate.now();
                    System.out.println("Selecione o dia: ");
                    System.out.println("Datas disponiveis: ");
                    for (int i = 1; i < 5 ; i++) {
                        LocalDate dataParaExame = dataHoje.plusDays(i);
                        System.out.println(i + " - " + dataParaExame.format(formatter) + " as 15:00");
                    }
                    Integer diaSelecionado = InputValidator.getClienteInput();
                    while(diaSelecionado < 1 || diaSelecionado > 5){
                        System.out.println("Escolha uma das opcoes disponiveis");
                        diaSelecionado = InputValidator.getClienteInput();
                    }
                    LocalDate dataSelecionada = dataHoje.plusDays(diaSelecionado);
                System.out.println(escolhaStringExame + " para " + beneficiario.getNome() + " marcado(a) para o dia " + dataSelecionada.format(formatter));
                    Exame exame = new Exame(beneficiario.getNome(), beneficiario.getCobertura(), dataSelecionada);
                    listaExames.add(exame);

        }
            case Cobertura.EXAME -> {
                System.out.println("Você pode realizar Exames");
                System.out.println("O que quer agendar?");
                System.out.println("1 - EXAME");
                System.out.println("2 - Cancelar");

                Integer beneficiarioEscolha = InputValidator.getClienteInput();
                if(beneficiarioEscolha.equals(2)){
                    return;
                }
                while(beneficiarioEscolha < 1  || beneficiarioEscolha > 2) {
                    System.out.println("Por favor, escolha 1 ou 2");
                    beneficiarioEscolha = InputValidator.getClienteInput();
                }
                String escolhaStringExame;
                if(beneficiarioEscolha == 1){
                    escolhaStringExame = "Exame";
                } else {
                    escolhaStringExame = "";
                }
                System.out.println("Você esta agendando um: " + escolhaStringExame+ ".");
                System.out.println("Datas disponíveis para realização do " + escolhaStringExame+ ":");
                LocalDate dataHoje = LocalDate.now();
                System.out.println("Selecione o dia: ");
                System.out.println("Datas disponiveis: ");
                for (int i = 1; i < 5 ; i++) {
                    LocalDate dataParaExame = dataHoje.plusDays(i);
                    System.out.println(i + " - " + dataParaExame.format(formatter) + " as 15:00");
                }
                Integer diaSelecionado = InputValidator.getClienteInput();
                while(diaSelecionado < 1 || diaSelecionado > 5){
                    System.out.println("Escolha uma das opcoes disponiveis");
                    diaSelecionado = InputValidator.getClienteInput();
                }
                LocalDate dataSelecionada = dataHoje.plusDays(diaSelecionado);
                System.out.println(escolhaStringExame + " para " + beneficiario.getNome() + " marcado(a) para o dia " + dataSelecionada.format(formatter));
                Exame exame = new Exame(beneficiario.getNome(), beneficiario.getCobertura(), dataSelecionada);
                listaExames.add(exame);
            }
            case Cobertura.CONSULTA -> {
                System.out.println("Você pode realizar Consultas");
                System.out.println("O que quer agendar?");
                System.out.println("1 - Consulta");
                System.out.println("2 - Cancelar");

                Integer beneficiarioEscolha = InputValidator.getClienteInput();
                if(beneficiarioEscolha.equals(2)){
                    return;
                }
                while(beneficiarioEscolha < 1  || beneficiarioEscolha > 2) {
                    System.out.println("Por favor, escolha 1 ou 2");
                    beneficiarioEscolha = InputValidator.getClienteInput();
                }
                String escolhaStringExame;
                if(beneficiarioEscolha == 1){
                    escolhaStringExame = "Consulta";
                } else {
                    escolhaStringExame = "";
                }
                System.out.println("Você esta agendando um: " + escolhaStringExame+ ".");
                System.out.println("Datas disponíveis para realização do " + escolhaStringExame+ ":");
                LocalDate dataHoje = LocalDate.now();
                System.out.println("Selecione o dia: ");
                System.out.println("Datas disponiveis: ");
                for (int i = 1; i < 5 ; i++) {
                    LocalDate dataParaExame = dataHoje.plusDays(i);
                    System.out.println(i + " - " + dataParaExame.format(formatter) + " as 15:00");
                }
                Integer diaSelecionado = InputValidator.getClienteInput();
                while(diaSelecionado < 1 || diaSelecionado > 5){
                    System.out.println("Escolha uma das opcoes disponiveis");
                    diaSelecionado = InputValidator.getClienteInput();
                }
                LocalDate dataSelecionada = dataHoje.plusDays(diaSelecionado);
                System.out.println(escolhaStringExame + " para " + beneficiario.getNome() + " marcado(a) para o dia " + dataSelecionada.format(formatter));
                Exame exame = new Exame(beneficiario.getNome(), beneficiario.getCobertura(), dataSelecionada);
                listaExames.add(exame);
            }
        }

    }
}
