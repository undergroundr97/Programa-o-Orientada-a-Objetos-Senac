package org.example.VitaCare;

import org.example.Entidades.Dependente;
import org.example.Entidades.Titular;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class VitaCare {

    public static void vitaCare(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem vindo ao cadastro da VitaCare");
        System.out.println("Digite o nome do titular: ");
        String nomeTitular = scanner.nextLine();
        System.out.println("Digite o CPF do titular(formato 9dígitos, sem pontos): ");
        String cpfTitular = scanner.nextLine();
        while(cpfTitular.length() < 9){
            System.out.println("CPF invalido!");
            cpfTitular = scanner.nextLine();
        }
        System.out.println("Digite a data de nascimento do titular: ");
        String dataNascimentoString = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
        Titular titular = new Titular(nomeTitular, cpfTitular, dataNascimento);
        System.out.println("Deseja adicionar dependentes?");
        String adicionarDependentes = scanner.nextLine();
        while(!adicionarDependentes.equalsIgnoreCase("s") && !adicionarDependentes.equalsIgnoreCase("n")){
            System.out.println("Digite S/N");
            adicionarDependentes = scanner.nextLine();
        }
            if(adicionarDependentes.equalsIgnoreCase("s")){
                System.out.println("Quantos dependentes? MAX: 3");
                while(!scanner.hasNextInt()){
                    System.out.println("Input invalido");
                    scanner.next();
                }
                Integer totalDependentes = scanner.nextInt();
                while(totalDependentes >= 3){
                    System.out.println("Digite um numero valido");
                    totalDependentes = scanner.nextInt();
                }
                scanner.nextLine();
                for (int i = 0; i <totalDependentes ; i++) {
                    System.out.println("Nome do dependente: ");
                    String nomeDependente = scanner.nextLine();
                    System.out.println("Digite o CPF do dependente(formato 9dígitos, sem pontos): ");
                    String cpfDependente = scanner.nextLine();
                    System.out.println("Digite a data nascimento");
                    String stringNascimentoDependente = scanner.nextLine();
                    LocalDate dataNascimentoDependente = LocalDate.parse(stringNascimentoDependente, formatter);
                    Dependente dependente = new Dependente(nomeDependente,cpfDependente,dataNascimentoDependente,
                            titular);
                    titular.adicionarDependente(dependente);
                }
            }
            Double  totalMensalidade = 0.0;
        System.out.println("Calculando o total mensal do plano... ");
        for (int i = 0; i < titular.getListaDependentes().size() ; i++) {
            totalMensalidade += titular.getListaDependentes().get(i).valorMensalidade();
        }
        totalMensalidade += titular.valorMensalidade();
        System.out.println("Seu plano custa mensalmente: R$" + String.format("%.2f", totalMensalidade));
    }
}
