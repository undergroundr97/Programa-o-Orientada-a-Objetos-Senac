package org.example.VitaCare;

import org.example.AgendarExame.AgendarExame;
import org.example.Cobertura.Cobertura;
import org.example.Entidades.Beneficiario;
import org.example.Entidades.Dependente;
import org.example.Entidades.Titular;
import org.example.InputValidator.InputValidator;

import java.sql.SQLOutput;
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
                    System.out.println("Digite o tipo de cobertura: ");
                    System.out.println("1 - EXAME");
                    System.out.println("2 - CONSULTA");
                    System.out.println("3 - TOTAL");
                    Integer opcaoCoberturaTitular = InputValidator.getClienteInput();
                    while(opcaoCoberturaTitular < 1 && opcaoCoberturaTitular > 3){
                        System.out.println("Selecione apenas as opcoes");
                        opcaoCoberturaTitular = InputValidator.getClienteInput();
                    }
                    Cobertura coberturaTitular;
                    if(opcaoCoberturaTitular == 1){
                        coberturaTitular = Cobertura.EXAME;
                    } else if (opcaoCoberturaTitular == 2){
                        coberturaTitular= Cobertura.CONSULTA;
                    } else {
                        coberturaTitular = Cobertura.TOTAL;
                    }

                    Titular titular = new Titular(nomeTitular, cpfTitular, dataNascimento, coberturaTitular);
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
                            System.out.println("Digite o tipo de cobertura: ");
                            System.out.println("1 - EXAME");
                            System.out.println("2 - CONSULTA");
                            System.out.println("3 - TOTAL");
                            Integer tipoCobertura = InputValidator.getClienteInput();
                            while(tipoCobertura < 1 && tipoCobertura > 3){
                                System.out.println("Selecione apenas as opcoes");
                                tipoCobertura = InputValidator.getClienteInput();
                            }
                            Cobertura cobertura;
                            if(tipoCobertura == 1){
                                cobertura = Cobertura.EXAME;
                            } else if (tipoCobertura == 2){
                                cobertura = Cobertura.CONSULTA;
                            } else {
                                cobertura = Cobertura.TOTAL;
                            }

                            Dependente dependente = new Dependente(nomeDependente,cpfDependente,dataNascimentoDependente,
                                    titular, cobertura);
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
                case 3 -> {
                    if(listaUsuarios.isEmpty()){
                        System.out.println("Nenhum beneficiario encontrado!");
                        System.out.println("Retornando ao menu principal");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e){
                            e.getMessage();
                        }
                    } else {
                        System.out.println("Selecione o beneficiario para verificar cobertura");
                        Integer index = 0;
                        listaUsuarios.forEach(usuario -> {
                            System.out.println((index + 1) + " - " + usuario.getNome());
                        });
                        Integer usuarioSelecioando = InputValidator.getClienteInput();
                        while(usuarioSelecioando > listaUsuarios.size() || usuarioSelecioando < 0){
                            System.out.println("Usuario invalido, selecione apenas os disponiveis");
                            usuarioSelecioando = InputValidator.getClienteInput();
                        }
                        Beneficiario beneficiarioSelecionado = listaUsuarios.get(usuarioSelecioando - 1);
                        System.out.println("Voce selecionou: " + beneficiarioSelecionado.getNome());
                        System.out.println("A cobertura deste beneficiario e: " + beneficiarioSelecionado.getCobertura());
                        if (!((Titular) beneficiarioSelecionado).getListaDependentes().isEmpty()) {
                            System.out.println("Este usuario possui dependentes");
                            System.out.println("Cheando coberturas... ");
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.getMessage();
                            }
                            for (Dependente dependenteBeneficiario :
                                    ((Titular) beneficiarioSelecionado).getListaDependentes()) {
                                System.out.println("O dependente: " + dependenteBeneficiario.getNome() + " tem cobertura: " + dependenteBeneficiario.getCobertura());
                            }
                        }
                    }
                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 4 -> {
                    if(listaUsuarios.isEmpty()){
                        System.out.println("Nenhum beneficiario encontrado!");
                        System.out.println("Voltando ao menu...");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e){
                            e.getMessage();
                        }
                    } else {
                        System.out.println("Selecione um usuario: ");
                        listaUsuarios.forEach( usuario -> {
                            System.out.println( (listaUsuarios.indexOf(usuario) + 1) + " - " +usuario.getNome());
                        });
                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        while(usuarioSelecionado > listaUsuarios.size() || usuarioSelecionado < 0){
                            System.out.println("Usuario invalido, selecione apenas usuario da lista");
                            usuarioSelecionado = InputValidator.getClienteInput();
                        }
                        Beneficiario beneficiario = listaUsuarios.get(usuarioSelecionado - 1);
                        List<Beneficiario> listaDaFamilia = new ArrayList<>();
                        System.out.println("Você selecionou " + beneficiario.getNome());
                        listaDaFamilia.add(beneficiario);
                        if (((Titular) beneficiario).getListaDependentes().isEmpty()){
                            System.out.println("O beneficiaro nao tem dependentes");
                        } else {
                            System.out.println("O beneficiario possui " + ((Titular) beneficiario).getListaDependentes().size() + " dependentes.");
                            for (Dependente dependentes : ((Titular) beneficiario).getListaDependentes()) {
                                listaDaFamilia.add(dependentes);
                            }
                        }
                        System.out.println("Selecione o beneficiario para agendar a consulta: ");
                        int indexBeneficiario = 0;
                        listaDaFamilia.forEach(beneficiarioFamilia -> {
                            System.out.println( listaDaFamilia.indexOf(beneficiarioFamilia) + 1 + " - " + beneficiarioFamilia.getNome());
                        });
                        Integer escolhaBeneficiario = InputValidator.getClienteInput();
                        while(escolhaBeneficiario < 0 || escolhaBeneficiario > listaDaFamilia.size()){
                            System.out.println("Beneficiario invalido");
                            escolhaBeneficiario = InputValidator.getClienteInput();
                        }
                        Beneficiario beneficiarioParaConsulta = listaDaFamilia.get(escolhaBeneficiario - 1);
                        AgendarExame.criarExame(beneficiarioParaConsulta);

                    }



                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 5 ->{
                    if(AgendarExame.getListaExames().isEmpty()){
                        System.out.println("Nenhum exame agendado!");
                        System.out.println("Retornando ao menu principal");
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e ){
                            e.getMessage();
                        }
                    } else {
                        System.out.println("Mostrando dos exames agendados: ");
                        AgendarExame.getListaExames().forEach(exame -> {
                            System.out.println((AgendarExame.getListaExames().indexOf(exame) + 1) +" - " +exame.getTipoExame() + " para " + exame.getNomeSolicitante() + " dia: " + exame.getDataDoExame().format(formatter));
                        });
                        System.out.println("Digite qualquer tecla para voltar ao menu");
                        String confirma = scanner.nextLine();
                    }

                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();

                }
                default -> {
                    System.out.println("Nenhuma opcao valida selecionada!");
                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
            }

        }  while (opcaoCliente != 0);



    }

    static List<Beneficiario> listaUsuarios = new ArrayList<>();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static Scanner scanner = new Scanner(System.in);
    static {
        LocalDate dataNascimento = LocalDate.parse("10/09/1997", formatter);
        LocalDate dataNascimentoBeneficiario = LocalDate.parse("10/09/2010", formatter);
        Titular titular = new Titular("vitor", "12345567890", dataNascimento, Cobertura.TOTAL);
        Dependente dependente1 = new Dependente("vitor1", "12345556789", dataNascimentoBeneficiario, titular, Cobertura.EXAME);
        Dependente dependente2 = new Dependente("vitor2", "12345556789", dataNascimentoBeneficiario, titular, Cobertura.CONSULTA);
        titular.adicionarDependente(dependente1);
        titular.adicionarDependente(dependente2);
        listaUsuarios.add(titular);
    }
}
