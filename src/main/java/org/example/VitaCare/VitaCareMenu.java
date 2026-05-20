package org.example.VitaCare;

import org.example.Delay.DelayTimer;
import org.example.Entidades.Beneficiario;

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

    public static void exibirMensagemListaVazia(String string) {
        System.out.println("Nenhum + " + string + " cadastrado!");
        System.out.println("Voltando ao menu...");
        DelayTimer.delay(700);
    }
}
