package org.example.VitaCare;

import org.example.Entidades.Beneficiario;
import org.example.Entidades.Dependente;
import org.example.Entidades.Titular;
import org.example.InputValidator.InputValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;


public class VitaCare {
    public static void vitaCare(){
        VitaCareMenu.exibirMenu();
        Integer opcaoCliente = InputValidator.getClienteInput();
        do {
            switch (opcaoCliente) {
                case 0 -> {

                }
                case 1 -> {
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
                    listaUsuarios.add(titular);
                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 2 -> {
                    if(listaUsuarios.isEmpty()){
                        System.out.println("Nenhum beneficiário cadastrado ainda!");
                        System.out.println("Voltando ao menu...");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e){
                            e.getMessage();
                        }

                    } else {
                        System.out.println("Selecione um usuário para calcular a sua mensalidade: ");
                        Integer index = 0;
                        listaUsuarios.forEach( usuario -> {
                            System.out.println((index + 1) + " - " + usuario.getNome());
                        });
                        Integer usuarioSelecioando = InputValidator.getClienteInput();
                        Beneficiario beneficiarioSelecionado = listaUsuarios.get(usuarioSelecioando - 1);
                        Double mensalidadeDoBeneficiario = 0.0;
                        System.out.println("Você selecionou: " + beneficiarioSelecionado.getNome());
                        if(beneficiarioSelecionado instanceof Titular){
                            System.out.println("O usuario possui: " + ((Titular) beneficiarioSelecionado).getListaDependentes().size() + " dependentes");
                            if(!((Titular) beneficiarioSelecionado).getListaDependentes().isEmpty()){
                                for (Dependente dependenteBeneficiario :
                                        ((Titular) beneficiarioSelecionado).getListaDependentes()) {
                                    mensalidadeDoBeneficiario += dependenteBeneficiario.valorMensalidade();
                                    System.out.println("Mensalidade do dependente: " + dependenteBeneficiario.valorMensalidade());
                                }
                            }
                        }
                        mensalidadeDoBeneficiario += ((Titular) beneficiarioSelecionado).valorMensalidade();
                        System.out.println("Mensalidade do titular: " + ((Titular) beneficiarioSelecionado).valorMensalidade());

                        System.out.println("Calculando mensalidade...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e){
                            e.getMessage();
                        }
                        System.out.println("Sua mensalidade e de: " + mensalidadeDoBeneficiario);
                    }
                    System.out.println("Redirecionando ao menu... ");
                    try{
                        Thread.sleep(500);
                    } catch (InterruptedException e){
                        e.getMessage();
                    }
                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
            }

        }  while (opcaoCliente != 0);



    }

    static List<Beneficiario> listaUsuarios = new ArrayList<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static Scanner scanner = new Scanner(System.in);
    static {
        LocalDate dataNascimento = LocalDate.parse("10/09/1997", formatter);
        LocalDate dataNascimentoBeneficiario = LocalDate.parse("10/09/2010", formatter);
        Titular titular = new Titular("vitor", "12345567890", dataNascimento);
        Dependente dependente1 = new Dependente("vitor1", "12345556789", dataNascimentoBeneficiario, titular);
        titular.adicionarDependente(dependente1);
        listaUsuarios.add(titular);
    }
}
