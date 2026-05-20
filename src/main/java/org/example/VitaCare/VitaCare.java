package org.example.VitaCare;

import org.example.AgendarExame.AgendarExame;
import org.example.Enums.Cobertura;
import org.example.Delay.DelayTimer;
import org.example.Entidades.Beneficiario;
import org.example.Entidades.Dependente;
import org.example.Entidades.Titular;
import org.example.Enums.TipoDependente;
import org.example.InputValidator.InputValidator;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VitaCare {
    public static void vitaCare() {
        System.out.println("----------Bem Vindo ao VitaCare----------");
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
                    while (cpfTitular.length() < 9 || cpfTitular.length() > 10) {
                        System.out.println("CPF invalido!");
                        cpfTitular = scanner.nextLine();
                    }
                    System.out.println("Digite a data de nascimento do titular (formato dd/MM/yyyy): ");
                    String dataNascimentoString = scanner.nextLine();
                    dataNascimentoString = InputValidator.validarStringData(dataNascimentoString);
                    LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
                    VitaCareMenu.exibirCoberturaBase();
                    Integer opcaoCoberturaTitular = InputValidator.getClienteInput();
                    while (opcaoCoberturaTitular < 1 || opcaoCoberturaTitular > 3) {
                        System.out.println("Selecione apenas as opcoes");
                        opcaoCoberturaTitular = InputValidator.getClienteInput();
                    }
                    Cobertura coberturaTitular;
                    if (opcaoCoberturaTitular == 1) {
                        coberturaTitular = Cobertura.EXAME;
                    } else if (opcaoCoberturaTitular == 2) {
                        coberturaTitular = Cobertura.CONSULTA;
                    } else {
                        coberturaTitular = Cobertura.TOTAL;
                    }

                    Titular titular = new Titular(nomeTitular, cpfTitular, dataNascimento, coberturaTitular);
                    titular.setInternavel();
                    System.out.println("Deseja adicionar dependentes (S/N)?");
                    String adicionarDependentes = scanner.nextLine();
                    while (!adicionarDependentes.equalsIgnoreCase("s") && !adicionarDependentes.equalsIgnoreCase("n")) {
                        System.out.println("Digite S/N");
                        adicionarDependentes = scanner.nextLine();
                    }
                    if (adicionarDependentes.equalsIgnoreCase("s")) {
                        System.out.println("Quantos dependentes? MAX: 3");
                        Integer totalDependentes = InputValidator.getClienteInput();
                        while (totalDependentes > 3) {
                            System.out.println("Digite um numero valido");
                            totalDependentes = InputValidator.getClienteInput();
                        }
                        for (int i = 0; i < totalDependentes; i++) {
                            System.out.println("Digite o tipo do dependente:");
                            TipoDependente tipoDependente;
                            System.out.println("1 - FILHO");
                            System.out.println("2 - CONJUGE");
                            Integer tipoDependenteSelecionado = InputValidator.getClienteInput();
                            while (tipoDependenteSelecionado < 1 || tipoDependenteSelecionado > 2) {
                                System.out.println("OpcaoInvalida");
                                tipoDependenteSelecionado = InputValidator.getClienteInput();
                            }
                            if (tipoDependenteSelecionado == 1) {
                                tipoDependente = TipoDependente.FILHO;
                            } else {
                                tipoDependente = TipoDependente.CONJUGE;
                            }
                            System.out.println("Nome do dependente: ");
                            String nomeDependente = scanner.nextLine();
                            System.out.println("Digite o CPF do dependente(formato 9dígitos, sem pontos): ");
                            String cpfDependente = scanner.nextLine();
                            System.out.println("Digite a data nascimento");
                            String stringNascimentoDependente = scanner.nextLine();
                            LocalDate dataNascimentoDependente = LocalDate.parse(stringNascimentoDependente, formatter);
                            Integer idadeAtual = (Year.now().getValue()) - dataNascimentoDependente.getYear();
                            if (tipoDependente.equals(TipoDependente.FILHO) && idadeAtual > 25) {
                                System.out.println("Filhos só podem ser dependentes até 24anos!");
                                break;
                            }
                            VitaCareMenu.exibirCoberturaBase();
                            Integer tipoCobertura = InputValidator.getClienteInput();
                            while (tipoCobertura < 1 || tipoCobertura > 3) {
                                System.out.println("Selecione apenas as opcoes");
                                tipoCobertura = InputValidator.getClienteInput();
                            }
                            Cobertura cobertura;
                            if (tipoCobertura == 1) {
                                cobertura = Cobertura.EXAME;
                            } else if (tipoCobertura == 2) {
                                cobertura = Cobertura.CONSULTA;
                            } else {
                                cobertura = Cobertura.TOTAL;
                            }
                            Dependente dependente = new Dependente(nomeDependente, cpfDependente, dataNascimentoDependente,
                                    titular, cobertura, tipoDependente);
                            titular.adicionarDependente(dependente);
                        }
                    }
                    listaUsuarios.add(titular);
                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 2 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("titular");
                    } else {
                        System.out.println("Selecione um titular para gerar resumo:  ");
                        listaUsuarios.forEach(usuario -> {
                            System.out.println(listaUsuarios.indexOf(usuario) + 1 + " - " + usuario.getNome());
                        });
                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        usuarioSelecionado = InputValidator.valueIn0toSize(usuarioSelecionado, listaUsuarios);
                        Beneficiario beneficiarioSelecionado = listaUsuarios.get(usuarioSelecionado - 1);
                        Double mensalidadeDoBeneficiario = 0.0;
                        System.out.println("Gerando resumo para: " + beneficiarioSelecionado.getNome());
                        DelayTimer.delay(700);
                        System.out.println("------------------------------------");
                        System.out.println("Titular: " + beneficiarioSelecionado.getNome());
                        System.out.println("CPF: " + beneficiarioSelecionado.getCPF());
                        System.out.println("Mensalidade: " + ((Titular) beneficiarioSelecionado).valorMensalidade());
                        DelayTimer.delay(700);
                        if (!((Titular) beneficiarioSelecionado).getListaDependentes().isEmpty()) {
                            System.out.println("O titular possui: " + ((Titular) beneficiarioSelecionado).getListaDependentes().size() + " " +
                                    "dependentes");
                        }
                        mensalidadeDoBeneficiario += ((Titular) beneficiarioSelecionado).valorMensalidade();
                        if (beneficiarioSelecionado instanceof Titular) {
                            if (!((Titular) beneficiarioSelecionado).getListaDependentes().isEmpty()) {
                                System.out.println("Mostrando dependentes...");
                                DelayTimer.delay(1000);
                                for (Dependente dependenteBeneficiario : ((Titular) beneficiarioSelecionado).getListaDependentes()) {
                                    if (((Titular) beneficiarioSelecionado).getListaDependentes().indexOf(dependenteBeneficiario) == 0) {
                                        System.out.println();
                                    } else {
                                        System.out.println("------------------------------------");
                                    }

                                    System.out.println("Dependente: " + dependenteBeneficiario.getNome());
                                    System.out.println("CPF: " + dependenteBeneficiario.getCPF());
                                    System.out.println("Tipo beneficiario: " + dependenteBeneficiario.getTipoDependente());
                                    System.out.println("Mensalidade: " + String.format("R$ %.2f", dependenteBeneficiario.valorMensalidade()));
                                    mensalidadeDoBeneficiario += dependenteBeneficiario.valorMensalidade();
                                    DelayTimer.delay(700);
                                }
                            }
                        }
                        System.out.println("------------------------------------");
                        System.out.println("Calculando mensalidade...");
                        DelayTimer.delay(700);
                        System.out.println("Mensalidade para o titular: " + beneficiarioSelecionado.getNome() + " e de: R$ " + mensalidadeDoBeneficiario);
                        DelayTimer.delay(700);
                        System.out.println("Digite qualquer tecla para voltar ao menur principal");
                        scanner.nextLine();
                    }
                    System.out.println("Redirecionando ao menu... ");
                    DelayTimer.delay(700);
                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 3 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("titular");
                    } else {
                        System.out.println("Selecione o beneficiario para verificar cobertura");
                        listaUsuarios.forEach(usuario -> {
                            System.out.println((listaUsuarios.indexOf(usuario) + 1) + " - " + usuario.getNome());
                        });
                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        usuarioSelecionado = InputValidator.valueIn0toSize(usuarioSelecionado, listaUsuarios);
                        Beneficiario beneficiarioSelecionado = listaUsuarios.get(usuarioSelecionado - 1);
                        System.out.println("Voce selecionou: " + beneficiarioSelecionado.getNome());
                        System.out.println("A cobertura deste beneficiario e: " + beneficiarioSelecionado.getCobertura());
                        if (!((Titular) beneficiarioSelecionado).getListaDependentes().isEmpty()) {
                            System.out.println("Este usuario possui dependentes");
                            System.out.println("Checando coberturas... ");
                            DelayTimer.delay(700);
                            for (Dependente dependenteBeneficiario :
                                    ((Titular) beneficiarioSelecionado).getListaDependentes()) {
                                System.out.println("O dependente: " + dependenteBeneficiario.getNome() + " tem cobertura: " + dependenteBeneficiario.getCobertura());
                                DelayTimer.delay(700);
                            }
                        }
                    }
                    System.out.println("Voltando ao menu...");
                    DelayTimer.delay(1000);
                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 4 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("beneficiario");
                    } else {
                        System.out.println("Selecione um usuario: ");
                        listaUsuarios.forEach(usuario -> {
                            System.out.println((listaUsuarios.indexOf(usuario) + 1) + " - " + usuario.getNome());
                        });
                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        usuarioSelecionado = InputValidator.valueIn0toSize(usuarioSelecionado, listaUsuarios);
                        Beneficiario beneficiario = listaUsuarios.get(usuarioSelecionado - 1);
                        List<Beneficiario> listaDaFamilia = new ArrayList<>();
                        System.out.println("Você selecionou " + beneficiario.getNome());
                        listaDaFamilia.add(beneficiario);
                        if (((Titular) beneficiario).getListaDependentes().isEmpty()) {
                            System.out.println("O beneficiaro nao tem dependentes");
                        } else {
                            System.out.println("O beneficiario possui " + ((Titular) beneficiario).getListaDependentes().size() + " dependentes.");
                            for (Dependente dependentes : ((Titular) beneficiario).getListaDependentes()) {
                                listaDaFamilia.add(dependentes);
                            }
                        }
                        System.out.println("Selecione o beneficiario para agendar a consulta: ");
                        listaDaFamilia.forEach(beneficiarioFamilia -> {
                            System.out.println(listaDaFamilia.indexOf(beneficiarioFamilia) + 1 + " - " + beneficiarioFamilia.getNome());
                        });
                        Integer escolhaBeneficiario = InputValidator.getClienteInput();
                        while (escolhaBeneficiario < 0 || escolhaBeneficiario > listaDaFamilia.size()) {
                            System.out.println("Beneficiario invalido");
                            escolhaBeneficiario = InputValidator.getClienteInput();
                        }
                        Beneficiario beneficiarioParaConsulta = listaDaFamilia.get(escolhaBeneficiario - 1);
                        AgendarExame.criarExame(beneficiarioParaConsulta);

                    }

                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 5 -> {
                    if (AgendarExame.getListaExames().isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("Exame");
                    } else {
                        System.out.println("Mostrando dos exames agendados: ");
                        AgendarExame.getListaExames().forEach(exame -> {
                            System.out.print((AgendarExame.getListaExames().indexOf(exame) + 1) + " - " + exame.getTipoExame() + " para " + exame.getNomeSolicitante() + " dia: " + exame.getDataDoExame().format(formatter));
                            if (exame.getDataSaida() != null) {
                                System.out.print(", data experada de saída: " + exame.getDataSaida().format(formatter));
                            }
                            ;
                            System.out.println(" Doutor: " + exame.getDoutor().getNome() + ", Especializacao: " + exame.getDoutor().getEspecializacao() + ".");
                        });
                        System.out.println("Digite qualquer tecla para voltar ao menu");
                        String confirma = scanner.nextLine();
                    }

                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();

                }
                case 6 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("beneficiario");
                    } else {
                        System.out.println("Selecione um titular: ");
                        System.out.println("0 - VOLTAR");
                        listaUsuarios.forEach(usuario -> {
                            System.out.println((listaUsuarios.indexOf(usuario) + 1) + " - " + usuario.getNome());
                        });
                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        if (usuarioSelecionado.equals(0)) {
                            System.out.println("Voltando ao menu...");
                            VitaCareMenu.exibirMenu();
                            opcaoCliente = InputValidator.getClienteInput();
                            break;
                        }
                        usuarioSelecionado = InputValidator.valueIn0toSize(usuarioSelecionado, listaUsuarios);
                        Beneficiario beneficiarioSelecionado = listaUsuarios.get(usuarioSelecionado - 1);
                        if (((Titular) beneficiarioSelecionado).getListaDependentes().size() >= 3) {
                            System.out.println("Impossível adicionar novo dependente");
                            DelayTimer.delay(700);
                        } else {
                            System.out.println("Digite o tipo do dependente:");
                            TipoDependente tipoDependente;
                            System.out.println("1 - FILHO");
                            System.out.println("2 - CONJUGE");
                            Integer tipoDependenteSelecionado = InputValidator.getClienteInput();
                            while (tipoDependenteSelecionado < 1 || tipoDependenteSelecionado > 2) {
                                System.out.println("OpcaoInvalida");
                                tipoDependenteSelecionado = InputValidator.getClienteInput();
                            }
                            if (tipoDependenteSelecionado == 1) {
                                tipoDependente = TipoDependente.FILHO;
                            } else {
                                tipoDependente = TipoDependente.CONJUGE;
                            }
                            System.out.println("Nome do dependente: ");
                            String nomeDependente = scanner.nextLine();
                            System.out.println("Digite o CPF do dependente(formato 9dígitos, sem pontos): ");
                            String cpfDependente = scanner.nextLine();
                            while (cpfDependente.length() < 9 || cpfDependente.length() > 10) {
                                System.out.println("CPF Invalido");
                                cpfDependente = scanner.nextLine();
                            }
                            System.out.println("Digite a data nascimento");
                            String stringNascimentoDependente = scanner.nextLine();
                            stringNascimentoDependente = InputValidator.validarStringData(stringNascimentoDependente);
                            LocalDate dataNascimentoDependente = LocalDate.parse(stringNascimentoDependente, formatter);
                            Integer idadeAtual = (Year.now().getValue()) - dataNascimentoDependente.getYear();
                            if (tipoDependente.equals(TipoDependente.FILHO) && idadeAtual > 25) {
                                System.out.println("Filhos só podem ser dependentes até 24anos!");
                                break;
                            }
                            VitaCareMenu.exibirCoberturaBase();
                            Integer tipoCobertura = InputValidator.getClienteInput();
                            while (tipoCobertura < 1 || tipoCobertura > 3) {
                                System.out.println("Selecione apenas as opcoes");
                                tipoCobertura = InputValidator.getClienteInput();
                            }
                            Cobertura cobertura;
                            if (tipoCobertura == 1) {
                                cobertura = Cobertura.EXAME;
                            } else if (tipoCobertura == 2) {
                                cobertura = Cobertura.CONSULTA;
                            } else {
                                cobertura = Cobertura.TOTAL;
                            }
                            Dependente dependente = new Dependente(nomeDependente, cpfDependente, dataNascimentoDependente,
                                    (Titular) beneficiarioSelecionado, cobertura, tipoDependente);
                            ((Titular) beneficiarioSelecionado).adicionarDependente(dependente);
                        }

                        System.out.println("Voltando ao menu...");
                        DelayTimer.delay(700);
                        VitaCareMenu.exibirMenu();
                        opcaoCliente = InputValidator.getClienteInput();

                    }
                }
                case 7 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("titular");
                    }
                    System.out.println("Selecione o titular para aposentar/desaposentar");
                    listaUsuarios.forEach(usuario -> {
                        System.out.println((listaUsuarios.indexOf(usuario) + 1) + " - " + usuario.getNome());
                    });
                    Integer usuarioSelecionado = InputValidator.getClienteInput();
                    usuarioSelecionado = InputValidator.valueIn0toSize(usuarioSelecionado, listaUsuarios);
                    Beneficiario beneficiarioSelecionado = listaUsuarios.get(usuarioSelecionado - 1);
                    System.out.println("O titular selecioando foi: " + beneficiarioSelecionado.getNome());
                    System.out.println("O estado atual do benficario e: " + ((Titular) beneficiarioSelecionado).getAposentado());
                    System.out.println("O que deseja fazer?");
                    System.out.println("1 - APOSENTAR");
                    System.out.println("2 - DESAPOSENTAR");
                    Integer aposentar = InputValidator.getClienteInput();
                    while (aposentar < 1 || aposentar > 2) {
                        System.out.println("Selecione uma opcao valida");
                        aposentar = InputValidator.getClienteInput();
                    }
                    switch (aposentar) {
                        case 1 -> {
                            if (((Titular) beneficiarioSelecionado).getAposentado().equals("Aposentado")) {
                                System.out.println(beneficiarioSelecionado.getNome() + " ja esta aposentado");
                            } else {
                                System.out.println("Aposentando beneficiario " + beneficiarioSelecionado.getNome());
                                ((Titular) beneficiarioSelecionado).setAposentado(true);
                            }
                        }
                        case 2 -> {
                            if (((Titular) beneficiarioSelecionado).getAposentado().equals("Nao aposentado")) {
                                System.out.println(beneficiarioSelecionado.getNome() + " ja consta como nao aposentado");
                            } else {
                                System.out.println("Aposentando benficario " + beneficiarioSelecionado.getNome());
                                ((Titular) beneficiarioSelecionado).setAposentado(false);
                            }
                        }
                    }
                    System.out.println("Voltando ao menu...");
                    DelayTimer.delay(700);
                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 8 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("beneficiario");
                    }
                    System.out.println("Selecione um titular para gerenciar as coberturas disponiveis");
                    listaUsuarios.forEach(beneficiario -> {
                        System.out.println((listaUsuarios.indexOf(beneficiario) + 1) + " - " + beneficiario.getNome());
                    });
                    Integer usuarioSelecionado = InputValidator.getClienteInput();
                    usuarioSelecionado = InputValidator.valueIn0toSize(usuarioSelecionado, listaUsuarios);

                    Beneficiario beneficiario = listaUsuarios.get(usuarioSelecionado - 1);
                    List<Beneficiario> listaFamilia = new ArrayList<>();
                    listaFamilia.add(beneficiario);
                    if (((Titular) beneficiario).getListaDependentes().isEmpty()) {
                        System.out.println("Usuario não tem dependentes");
                    } else {
                        ((Titular) beneficiario).getListaDependentes().forEach(dependente -> {
                            listaFamilia.add(dependente);
                        });
                    }
                    System.out.println("Selecione o titular/dependente para gerenciar a cobertura: ");
                    listaFamilia.forEach(beneficiarios -> {
                        System.out.println((listaFamilia.indexOf(beneficiarios) + 1) + " - " + beneficiarios.getNome());
                    });
                    Integer beneficiarioSelecionado = InputValidator.getClienteInput();
                    beneficiarioSelecionado = InputValidator.valueIn0toSize(beneficiarioSelecionado, listaFamilia);
                    Beneficiario beneficiarioMudarCobertura = listaFamilia.get(beneficiarioSelecionado - 1);
                    System.out.println("O beneficiario selecionado foi: " + beneficiarioMudarCobertura.getNome());
                    System.out.println("A cobertura atual do beneficiario e: " + beneficiarioMudarCobertura.getCobertura());
                    System.out.println("Selecione a cobertura: ");
                    Integer coberturaSelecionada;
                    if (beneficiarioMudarCobertura instanceof Titular) {
                        System.out.println("Digite o tipo de cobertura: ");
                        System.out.println("1 - EXAME");
                        System.out.println("2 - CONSULTA");
                        System.out.println("3 - TOTAL");
                        System.out.println("4 - INTERNACAO");
                        Cobertura cobertura;
                        coberturaSelecionada = InputValidator.getClienteInput();
                        while (coberturaSelecionada <= 0 || coberturaSelecionada > 4) {
                            System.out.println("Opcao Invalida");
                            coberturaSelecionada = InputValidator.getClienteInput();
                        }
                        switch (coberturaSelecionada) {
                            case 1 -> {
                                cobertura = Cobertura.EXAME;
                                beneficiarioMudarCobertura.setCobertura(cobertura);
                                System.out.println("A cobertura foi modificara para: " + cobertura);
                            }
                            case 2 -> {
                                cobertura = Cobertura.CONSULTA;
                                beneficiarioMudarCobertura.setCobertura(cobertura);
                                System.out.println("A cobertura foi modificara para: " + cobertura);
                            }
                            case 3 -> {
                                cobertura = Cobertura.TOTAL;
                                beneficiarioMudarCobertura.setCobertura(cobertura);
                                System.out.println("A cobertura foi modificara para: " + cobertura);
                            }
                            case 4 -> {
                                cobertura = Cobertura.INTERNACAO;
                                beneficiarioMudarCobertura.setCobertura(cobertura);
                                System.out.println("A cobertura foi modificara para: " + cobertura);
                            }
                        }
                        System.out.println("Mudando a cobertura...");
                        DelayTimer.delay(700);
                    } else {
                        VitaCareMenu.exibirCoberturaBase();
                        Cobertura cobertura;
                        coberturaSelecionada = InputValidator.getClienteInput();
                        while (coberturaSelecionada <= 0 || coberturaSelecionada > 3) {
                            System.out.println("Selecao Invalida");
                            coberturaSelecionada = InputValidator.getClienteInput();
                        }
                        switch (coberturaSelecionada) {
                            case 1 -> {
                                cobertura = Cobertura.EXAME;
                                beneficiarioMudarCobertura.setCobertura(cobertura);
                                System.out.println("A cobertura foi modificara para: " + cobertura);
                            }
                            case 2 -> {
                                cobertura = Cobertura.CONSULTA;
                                beneficiarioMudarCobertura.setCobertura(cobertura);
                                System.out.println("A cobertura foi modificara para: " + cobertura);
                            }
                            case 3 -> {
                                cobertura = Cobertura.TOTAL;
                                beneficiarioMudarCobertura.setCobertura(cobertura);
                                System.out.println("A cobertura foi modificara para: " + cobertura);
                            }
                        }
                        System.out.println("Mudando a cobertura...");
                        DelayTimer.delay(700);
                    }
                    System.out.println("Voltando ao menu...");
                    DelayTimer.delay(700);
                    VitaCareMenu.exibirMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 9 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("Titular");
                    } else {
                        System.out.println("Selecione um titular: ");
                        listaUsuarios.forEach(titular -> {
                            System.out.println((listaUsuarios.indexOf(titular) + 1) + " - " + titular.getNome());
                        });
                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        usuarioSelecionado = InputValidator.valueIn0toSize(usuarioSelecionado, listaUsuarios);
                        Beneficiario beneficiario = listaUsuarios.get(usuarioSelecionado - 1);
                        List<Beneficiario> listaFamilia = new ArrayList<>();
                        listaFamilia.add(beneficiario);
                        ((Titular) beneficiario).getListaDependentes().forEach(dependente -> {
                            listaFamilia.add(dependente);
                        });
                        System.out.println("Escolha um beneficiario para modificar os dados: ");
                        listaFamilia.forEach(usuarioFamilia -> {
                            System.out.println((listaFamilia.indexOf(usuarioFamilia) + 1) + " - " + usuarioFamilia.getNome());
                        });
                        Integer usuarioParaModificar = InputValidator.getClienteInput();
                        usuarioParaModificar = InputValidator.valueIn0toSize(usuarioParaModificar, listaFamilia);
                        Beneficiario beneficiarioParaModificar = listaFamilia.get(usuarioParaModificar - 1);
                        System.out.println("Você selecionou: " + beneficiarioParaModificar.getNome());
                        System.out.println("Novo nome: ");
                        String novoNome = scanner.nextLine();
                        beneficiarioParaModificar.setNome(novoNome);
                        System.out.println("Nova data de nascimento (format dd/MM/yyyy): ");
                        String novaDataNascimento = scanner.nextLine();
                        novaDataNascimento = InputValidator.validarStringData(novaDataNascimento);
                        LocalDate novaData = LocalDate.parse(novaDataNascimento, formatter);
                        beneficiarioParaModificar.setDataNascimento(novaData);
                        System.out.println("Voltando ao menu...");
                        DelayTimer.delay(700);
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

        } while (opcaoCliente != 0);

    }

    static List<Beneficiario> listaUsuarios = new ArrayList<>();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static Scanner scanner = new Scanner(System.in);

    static {
        LocalDate dataNascimento = LocalDate.parse("10/09/1997", formatter);
        LocalDate dataNascimentoBeneficiario = LocalDate.parse("10/09/2010", formatter);
        Titular titular = new Titular("vitoriNTERNACAO", "12345567890", dataNascimento, Cobertura.INTERNACAO);
        Dependente dependente1 = new Dependente("vitorEXAME", "12345556789", dataNascimentoBeneficiario, titular, Cobertura.EXAME, TipoDependente.FILHO);
        Dependente dependente2 = new Dependente("vitorCONSULTA", "12345556789", dataNascimentoBeneficiario, titular, Cobertura.CONSULTA, TipoDependente.FILHO);
        Dependente dependente3 = new Dependente("vitorCONSULTA", "12345556789", dataNascimentoBeneficiario, titular, Cobertura.CONSULTA,
                TipoDependente.FILHO);
        titular.adicionarDependente(dependente1);
        titular.adicionarDependente(dependente3);
        titular.adicionarDependente(dependente2);
        listaUsuarios.add(titular);
    }
}
