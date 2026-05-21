package org.example.AgendarExame;

import org.example.Entidades.Titular;
import org.example.Enums.Cobertura;
import org.example.Entidades.Beneficiario;
import org.example.InputValidator.InputValidator;
import org.example.VitaCare.VitaCare;
import org.example.VitaCare.VitaCareMenu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AgendarExame {

    public static List<Exame> listaExames = new ArrayList<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static LocalDate dataHoje = LocalDate.now();

    public static void criarExame(Beneficiario beneficiario) {
        System.out.println("Bem vindo " + beneficiario.getNome() + " ao agendamento de exames!");
        System.out.println("Sua cobertura e: " + beneficiario.getCobertura());
        Cobertura coberturaDoBeneficiario = beneficiario.getCobertura();

        switch (coberturaDoBeneficiario) {
            case Cobertura.INTERNACAO -> {
                VitaCareMenu.exibirMenuExame(beneficiario);

                Integer beneficiarioEscolha = InputValidator.getClienteInput();
                beneficiarioEscolha = InputValidator.verificarInput1ToN(beneficiarioEscolha, 3);
                Cobertura coberturaExame = escolhaExame(beneficiarioEscolha);
                System.out.println("Datas disponíveis para realização do " + coberturaExame + ":");

                if (coberturaExame.equals(Cobertura.INTERNACAO)) {
                    LocalDate[] datas = diasInternacao();
                    System.out.println(coberturaExame + " para " + beneficiario.getNome() + " marcado(a) para o dia " + datas[0].format(formatter));
                    Exame exame = new Exame(beneficiario.getNome(), coberturaExame, datas[0], datas[1]);
                    listaExames.add(exame);
                } else {
                    LocalDate dataSelecionada = diaExame();
                    System.out.println(coberturaExame + " para " + beneficiario.getNome() + " marcado(a) para o dia " + dataSelecionada.format(formatter));
                    Exame exame = new Exame(beneficiario.getNome(), coberturaExame, dataSelecionada, new Doutor());
                    listaExames.add(exame);
                }

            }
            case Cobertura.TOTAL -> {
                VitaCareMenu.exibirMenuExame(beneficiario);
                Integer beneficiarioEscolha = InputValidator.getClienteInput();
                beneficiarioEscolha = InputValidator.verificarInput1ToN(beneficiarioEscolha, 2);
                Cobertura coberturaExame = escolhaExame(beneficiarioEscolha);

                System.out.println("Você esta agendando um: " + coberturaExame + ".");
                LocalDate dataSelecionada = diaExame();

                System.out.println(coberturaExame + " para " + beneficiario.getNome() + " marcado(a) para o dia " + dataSelecionada.format(formatter));
                Exame exame = new Exame(beneficiario.getNome(), coberturaExame, dataSelecionada, new Doutor());
                listaExames.add(exame);

            }
            case Cobertura.EXAME -> {
                VitaCareMenu.exibirMenuExame(beneficiario);

                Integer beneficiarioEscolha = InputValidator.getClienteInput();
                beneficiarioEscolha = InputValidator.verificarInput1ToN(beneficiarioEscolha, 1);
                Cobertura cobertura = escolhaExame(beneficiarioEscolha);

                LocalDate dataSelecionada = diaExame();
                System.out.println(cobertura + " para " + beneficiario.getNome() + " marcado(a) para o dia " + dataSelecionada.format(formatter));

                Exame exame = new Exame(beneficiario.getNome(), beneficiario.getCobertura(), dataSelecionada, new Doutor());
                listaExames.add(exame);
            }
            case Cobertura.CONSULTA -> {
                VitaCareMenu.exibirMenuExame(beneficiario);
                Integer beneficiarioEscolha = InputValidator.getClienteInput();
                beneficiarioEscolha = InputValidator.verificarInput1ToN(beneficiarioEscolha, 2);
                Cobertura cobertura = Cobertura.CONSULTA;

                LocalDate dataSelecionada = diaExame();
                System.out.println(cobertura + " para " + beneficiario.getNome() + " marcado(a) para o dia " + dataSelecionada.format(formatter));
                Exame exame = new Exame(beneficiario.getNome(), beneficiario.getCobertura(), dataSelecionada, new Doutor());
                listaExames.add(exame);
            }
        }
    }

    static Cobertura escolhaExame(Integer escolha) {
        Cobertura cobertura;
        switch (escolha){
            case 1 -> cobertura = Cobertura.EXAME;
            case 2 -> cobertura = Cobertura.CONSULTA;
            case 3 -> cobertura = Cobertura.INTERNACAO;
            default -> cobertura = Cobertura.EXAME;
        }
        System.out.println("Você esta agendando um: " + cobertura + ".");
        return cobertura;
    }

    static LocalDate[] diasInternacao(){
        System.out.println("Selecione o dia: ");
        System.out.println("Datas disponiveis: ");
        for (int i = 1; i < 5; i++) {
            LocalDate dataParaExame = dataHoje.plusDays(i);
            System.out.println(i + " - " + dataParaExame.format(formatter));
        }

        Integer diaSelecionado = InputValidator.getClienteInput();
        diaSelecionado = InputValidator.verificarInput1ToN(diaSelecionado, 5);
        LocalDate dataEntrada = dataHoje.plusDays(diaSelecionado);

        System.out.println("Digite a data prevista da saida da internacao");
        for (int i = 1; i < 5; i++) {
            LocalDate dataParaExame = dataHoje.plusDays((i + 10));
            System.out.println(i + " - " + dataParaExame.format(formatter));
        }
        Integer dataSaidaSelecionada = InputValidator.getClienteInput();
        dataSaidaSelecionada = InputValidator.verificarInput1ToN(dataSaidaSelecionada, 5);
        LocalDate dataSaida = dataHoje.plusDays(dataSaidaSelecionada + 10);

        LocalDate[] datas = new LocalDate[]{dataEntrada, dataSaida};
        return datas;
    }

    static LocalDate diaExame(){
        System.out.println("Selecione o dia: ");
        System.out.println("Datas disponiveis: ");
        for (int i = 1; i < 5; i++) {
            LocalDate dataParaExame = dataHoje.plusDays(i);
            System.out.println(i + " - " + dataParaExame.format(formatter) + " as 15:00");
        }
        Integer diaSelecionado = InputValidator.getClienteInput();
        diaSelecionado = InputValidator.verificarInput1ToN(diaSelecionado, 4);
        LocalDate dataSelecionada = dataHoje.plusDays(diaSelecionado);
        return dataSelecionada;
    }

    static {
        LocalDate date = LocalDate.of(2020, 05, 15);
        Exame exame = new Exame("vitor", Cobertura.CONSULTA, date, new Doutor());
        listaExames.add(exame);
    }

    public static List<Exame> getListaExames() {
        return listaExames;
    }

}
