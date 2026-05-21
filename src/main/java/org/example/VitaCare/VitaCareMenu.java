package org.example.VitaCare;

import org.example.AgendarExame.AgendarExame;
import org.example.AgendarExame.Exame;
import org.example.Delay.DelayTimer;
import org.example.Entidades.Beneficiario;
import org.example.Entidades.Titular;
import org.example.Enums.Cobertura;


import java.util.List;

import static org.example.VitaCare.VitaCare.formatter;

public class VitaCareMenu {
    public static void exibirMenu() {
        System.out.println("1 - Cadastrar Novo Titular");
        System.out.println("2 - Gerar Resumo para um Titular");
        System.out.println("3 - Verificar Coberturas de um Beneficiario");
        System.out.println("4 - Agendar Consulta para Beneficiario");
        System.out.println("5 - Verificar exames agendados no sistema");
        System.out.println("6 - Adicionar Beneficiario a um Titular Existente");
        System.out.println("7 - Aposentar Titular");
        System.out.println("8 - Gerenciar coberturas de beneficiarios");
        System.out.println("9 - Modificar dados de um beneficiario");
        System.out.println("0 - Sair");
    }

    public static void exibirCoberturaBase() {
        System.out.println("Digite o tipo de cobertura: ");
        System.out.println("1 - EXAME");
        System.out.println("2 - CONSULTA");
        System.out.println("3 - TOTAL");
    }
    public static void exibirCoberturaBase(Beneficiario beneficiario){
        System.out.println("O beneficiario selecionado foi: " + beneficiario.getNome());
        System.out.println("A cobertura atual do beneficiario e: " + beneficiario.getCobertura());
        System.out.println("Selecione a cobertura: ");
        if(beneficiario.getCobertura().equals(Cobertura.INTERNACAO)){
            System.out.println("Digite o tipo de cobertura: ");
            System.out.println("1 - EXAME");
            System.out.println("2 - CONSULTA");
            System.out.println("3 - TOTAL");
            System.out.println("4 - INTERNACAO");
        } else {
            System.out.println("Digite o tipo de cobertura: ");
            System.out.println("1 - EXAME");
            System.out.println("2 - CONSULTA");
            System.out.println("3 - TOTAL");
        }
    }

    public static void exibirVoltarMenu() {
        System.out.println("Redirecionando ao menu... ");
        DelayTimer.delay(1000);
        VitaCareMenu.exibirMenu();
    }

    public static void exibirMensagemListaVazia(String string) {
        System.out.println("Nenhum + " + string + " cadastrado!");
    }

    public static void exibirMenuFilhoConjuge() {
        System.out.println("Digite o tipo do dependente:");
        System.out.println("1 - FILHO");
        System.out.println("2 - CONJUGE");
    }

    public static void exibirMenuAposentar(Beneficiario beneficiarioSelecionado) {
        System.out.println("O titular selecioando foi: " + beneficiarioSelecionado.getNome());
        System.out.println("O estado atual do benficario e: " + ((Titular) beneficiarioSelecionado).getAposentado());
        System.out.println("O que deseja fazer?");
        System.out.println("1 - APOSENTAR");
        System.out.println("2 - DESAPOSENTAR");
    }

    public static void exibirListaExames(){
        List<Exame> exames = AgendarExame.getListaExames();
         exames.forEach(exame -> {
            System.out.print((AgendarExame.getListaExames().indexOf(exame) + 1) + " - " + exame.getTipoExame() + " para " + exame.getNomeSolicitante() + " dia: " + exame.getDataDoExame().format(formatter));
            if (exame.getDataSaida() != null) {
                System.out.print(", data experada de saída: " + exame.getDataSaida().format(formatter));
            };
            System.out.println(" Doutor: " + exame.getDoutor().getNome() + ", Especializacao: " + exame.getDoutor().getEspecializacao() + ".");
        });
    }

    public static void exibirListaUsuarios(List<Beneficiario> listaUsuarios, String string) {
        System.out.println("Selecione um " + string);
        listaUsuarios.forEach(usuario -> {
            System.out.println(listaUsuarios.indexOf(usuario) + 1 + " - " + usuario.getNome());
        });
    }

    public static void exibirMudarCobertura(Cobertura cobertura) {
        System.out.println("Você escolheu: " + cobertura);
        System.out.println("Mudando a cobertura...");
        DelayTimer.delay(700);
    }
}