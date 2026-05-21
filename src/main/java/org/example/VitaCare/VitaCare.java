package org.example.VitaCare;

import org.example.AgendarExame.AgendarExame;
import org.example.Enums.Cobertura;
import org.example.Delay.DelayTimer;
import org.example.Entidades.Beneficiario;
import org.example.Entidades.Dependente;
import org.example.Entidades.Titular;
import org.example.Enums.TipoDependente;
import org.example.InputValidator.InputValidator;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VitaCare {

    static List<Beneficiario> listaUsuarios = new ArrayList<>();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static Scanner scanner = new Scanner(System.in);

    public static void vitaCare() {
        System.out.println("----------Bem Vindo ao VitaCare----------");
        VitaCareMenu.exibirMenu();
        Integer opcaoCliente = InputValidator.getClienteInput();
        do {
            switch (opcaoCliente) {
                case 0 -> {
                    return;
                }
                case 1 -> {

                    ArrayList<Object> dadosCadastro = cadastrarUsuario(scanner);

                    Titular titular = new Titular((String) dadosCadastro.get(0),(String) dadosCadastro.get(1), (LocalDate) dadosCadastro.get(2),
                            (Cobertura) dadosCadastro.get(3));
                    titular.setInternavel();

                    System.out.println("Deseja adicionar dependentes (S/N)?");
                    String adicionarDependentes = scanner.nextLine();
                    adicionarDependentes = InputValidator.verificarSimNao(adicionarDependentes);

                    if (adicionarDependentes.equalsIgnoreCase("s")) {
                        System.out.println("Quantos dependentes? MAX: 3");
                        Integer totalDependentes = InputValidator.getClienteInput();
                        totalDependentes = InputValidator.verificarInput1ToN(totalDependentes, 3);
                        for (int i = 0; i < totalDependentes; i++) {
                            ArrayList<Object> dadosDependente = dadosDependente(scanner);

                            Integer idadeDependente = (Integer) dadosDependente.get(3);
                            TipoDependente tipoDependente = (TipoDependente) dadosDependente.get(5);

                            if(filhoMaiorIdade(tipoDependente, idadeDependente)){
                                break;
                            }

                            Dependente dependente = new Dependente((String) dadosDependente.get(0), (String) dadosDependente.get(1), (LocalDate) dadosDependente.get(2),
                                    titular, (Cobertura) dadosDependente.get(4), (TipoDependente) dadosDependente.get(5));
                            titular.adicionarDependente(dependente);
                        }
                    }
                    listaUsuarios.add(titular);
                    VitaCareMenu.exibirVoltarMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 2 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("titular");
                    } else {
                        VitaCareMenu.exibirListaUsuarios(listaUsuarios, "titular");
                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        usuarioSelecionado = InputValidator.valueIn0toListSize(usuarioSelecionado, listaUsuarios);
                        Beneficiario beneficiarioSelecionado = listaUsuarios.get(usuarioSelecionado - 1);
                        exibirResumo(beneficiarioSelecionado);
                    }
                    VitaCareMenu.exibirVoltarMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 3 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("titular");
                    } else {
                        VitaCareMenu.exibirListaUsuarios(listaUsuarios, "titular");

                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        usuarioSelecionado = InputValidator.valueIn0toListSize(usuarioSelecionado, listaUsuarios);
                        Beneficiario beneficiarioSelecionado = listaUsuarios.get(usuarioSelecionado - 1);

                        exibirCobertura(beneficiarioSelecionado);
                    }
                    VitaCareMenu.exibirVoltarMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 4 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("beneficiario");
                    } else {
                        VitaCareMenu.exibirListaUsuarios(listaUsuarios, "beneficiario");

                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        usuarioSelecionado = InputValidator.valueIn0toListSize(usuarioSelecionado, listaUsuarios);
                        Beneficiario beneficiario = listaUsuarios.get(usuarioSelecionado - 1);

                        List<Beneficiario> listaDaFamilia = new ArrayList<>();
                        System.out.println("Você selecionou " + beneficiario.getNome());
                        listaDaFamilia.add(beneficiario);
                        exibirQtdEAdicionarDependentes(beneficiario, listaDaFamilia);

                        VitaCareMenu.exibirListaUsuarios(listaDaFamilia, "beneficiario");

                        Integer escolhaBeneficiario = InputValidator.getClienteInput();
                        escolhaBeneficiario = InputValidator.valueIn0toListSize(escolhaBeneficiario, listaDaFamilia);
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
                        exibirExamesAgendado();
                    }
                    VitaCareMenu.exibirVoltarMenu();
                    opcaoCliente = InputValidator.getClienteInput();

                }
                case 6 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("beneficiario");
                    } else {
                        VitaCareMenu.exibirListaUsuarios(listaUsuarios, "titular");

                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        usuarioSelecionado = InputValidator.valueIn0toListSize(usuarioSelecionado,listaUsuarios);

                        Beneficiario beneficiarioSelecionado = listaUsuarios.get(usuarioSelecionado - 1);
                        List<Dependente> listaDependentes = ((Titular) beneficiarioSelecionado).getListaDependentes();
                        boolean tamanhoListaMaiorQue3 = InputValidator.listSizeMaior3(listaDependentes);
                        if (tamanhoListaMaiorQue3) {
                            System.out.println("Impossível adicionar novo dependente");
                            DelayTimer.delay(700);
                        } else {
                            ArrayList<Object> dadosDependente = dadosDependente(scanner);
                            Integer idadeAtual = (Integer) dadosDependente.get(3);
                            TipoDependente tipoDependente = (TipoDependente) dadosDependente.get(5);

                            if(filhoMaiorIdade(tipoDependente, idadeAtual)){
                                break;
                            }
                            Dependente dependente = new Dependente((String) dadosDependente.get(0), (String) dadosDependente.get(1),
                                    (LocalDate) dadosDependente.get(2),
                                    (Titular) beneficiarioSelecionado, (Cobertura) dadosDependente.get(4), (TipoDependente) dadosDependente.get(5));

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
                    VitaCareMenu.exibirListaUsuarios(listaUsuarios, "titular");

                    Integer usuarioSelecionado = InputValidator.getClienteInput();
                    usuarioSelecionado = InputValidator.valueIn0toListSize(usuarioSelecionado, listaUsuarios);

                    Beneficiario beneficiarioSelecionado = listaUsuarios.get(usuarioSelecionado - 1);
                    VitaCareMenu.exibirMenuAposentar(beneficiarioSelecionado);
                    Integer aposentar = InputValidator.getClienteInput();
                    aposentar = InputValidator.verificarInput1ToN(aposentar, 2);
                    VitaCare.aposentarBeneficiario(aposentar, beneficiarioSelecionado);

                    VitaCareMenu.exibirVoltarMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 8 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("beneficiario");
                    }
                    VitaCareMenu.exibirListaUsuarios(listaUsuarios, "beneficiario");

                    Integer usuarioSelecionado = InputValidator.getClienteInput();
                    usuarioSelecionado = InputValidator.valueIn0toListSize(usuarioSelecionado, listaUsuarios);

                    Beneficiario beneficiario = listaUsuarios.get(usuarioSelecionado - 1);
                    List<Beneficiario> listaFamilia = new ArrayList<>();
                    listaFamilia.add(beneficiario);
                    exibirQtdEAdicionarDependentes(beneficiario, listaFamilia);

                    VitaCareMenu.exibirListaUsuarios(listaFamilia, "beneficiario");
                    Integer beneficiarioSelecionado = InputValidator.getClienteInput();
                    beneficiarioSelecionado = InputValidator.valueIn0toListSize(beneficiarioSelecionado, listaFamilia);
                    Beneficiario beneficiarioMudarCobertura = listaFamilia.get(beneficiarioSelecionado - 1);

                    Integer coberturaSelecionada;
                    boolean eTitular = beneficiarioMudarCobertura instanceof Titular;
                    if(eTitular){
                        VitaCareMenu.exibirCoberturaBase(beneficiarioMudarCobertura);
                        coberturaSelecionada = InputValidator.getClienteInput();
                        coberturaSelecionada = InputValidator.verificarInput1ToN(coberturaSelecionada,4);
                        Cobertura cobertura = escolhaCobertura(coberturaSelecionada);
                        beneficiarioMudarCobertura.setCobertura(cobertura);

                        VitaCareMenu.exibirMudarCobertura(cobertura);
                    } else {
                        VitaCareMenu.exibirCoberturaBase(beneficiarioMudarCobertura);
                        coberturaSelecionada = InputValidator.getClienteInput();
                        coberturaSelecionada = InputValidator.verificarInput1ToN(coberturaSelecionada, 3);
                        Cobertura cobertura = escolhaCobertura(coberturaSelecionada);
                        beneficiarioMudarCobertura.setCobertura(cobertura);

                        VitaCareMenu.exibirMudarCobertura(cobertura);
                    }
                    VitaCareMenu.exibirVoltarMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                case 9 -> {
                    if (listaUsuarios.isEmpty()) {
                        VitaCareMenu.exibirMensagemListaVazia("Titular");
                    } else {
                        VitaCareMenu.exibirListaUsuarios(listaUsuarios, "Titular");
                        Integer usuarioSelecionado = InputValidator.getClienteInput();
                        usuarioSelecionado = InputValidator.valueIn0toListSize(usuarioSelecionado, listaUsuarios);

                        Beneficiario beneficiario = listaUsuarios.get(usuarioSelecionado - 1);
                        List<Beneficiario> listaFamilia = new ArrayList<>();
                        listaFamilia.add(beneficiario);
                        exibirQtdEAdicionarDependentes(beneficiario, listaFamilia);

                        VitaCareMenu.exibirListaUsuarios(listaFamilia, "beneficiario: ");
                        Integer usuarioParaModificar = InputValidator.getClienteInput();
                        usuarioParaModificar = InputValidator.valueIn0toListSize(usuarioParaModificar, listaFamilia);
                        Beneficiario beneficiarioParaModificar = listaFamilia.get(usuarioParaModificar - 1);

                        System.out.println("Você selecionou: " + beneficiarioParaModificar.getNome());
                        ArrayList<Object> dadosDependente = dadosDependente(scanner, 9);

                        beneficiarioParaModificar.setNome((String)dadosDependente.get(0));
                        beneficiarioParaModificar.setDataNascimento((LocalDate) dadosDependente.get(1));
                        beneficiarioParaModificar.setCobertura((Cobertura) dadosDependente.get(2));
                        System.out.println("Dados modificados com sucesso");
                    }

                    VitaCareMenu.exibirVoltarMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
                default -> {
                    System.out.println("Nenhuma opcao valida selecionada!");
                    VitaCareMenu.exibirVoltarMenu();
                    opcaoCliente = InputValidator.getClienteInput();
                }
            }

        } while (opcaoCliente != 0);

    }

    static Cobertura escolhaCobertura(Integer escolha){
        Cobertura cobertura;
        if (escolha == 1) {
            cobertura = Cobertura.EXAME;
        } else if (escolha == 2) {
            cobertura = Cobertura.CONSULTA;
        } else {
            cobertura = Cobertura.TOTAL;
        }
        return cobertura;
    }

    static TipoDependente escolhaDependente(Integer escolha) {
        TipoDependente tipo;
        if (escolha == 1) {
            tipo = TipoDependente.FILHO;
        } else {
            tipo = TipoDependente.CONJUGE;
        }
        return tipo;
    }

    static void aposentarBeneficiario(Integer escolha, Beneficiario beneficiarioSelecionado) {
        switch (escolha) {
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

    }

    static ArrayList <Object> dadosDependente(Scanner scanner, Integer num){
        ArrayList<Object> dadosDependente = new ArrayList<>(){};

        System.out.println("Nome do dependente: ");
        String nomeDependente = scanner.nextLine();

        System.out.println("Digite a data nascimento");
        String stringNascimentoDependente = scanner.nextLine();
        stringNascimentoDependente = InputValidator.validarStringData(stringNascimentoDependente);
        LocalDate dataNascimentoDependente = LocalDate.parse(stringNascimentoDependente, formatter);

        VitaCareMenu.exibirCoberturaBase();
        Integer tipoCobertura = InputValidator.getClienteInput();
        tipoCobertura = InputValidator.verificarInput1ToN(tipoCobertura, 3);
        Cobertura cobertura = escolhaCobertura(tipoCobertura);
        dadosDependente.add(nomeDependente);
        dadosDependente.add(dataNascimentoDependente);
        dadosDependente.add(cobertura);

        return dadosDependente;
    }

    static ArrayList<Object> dadosDependente(Scanner scanner) {
        ArrayList<Object> dadosDependente = new ArrayList<>(){};

        VitaCareMenu.exibirMenuFilhoConjuge();
        Integer tipoDependenteSelecionado = InputValidator.getClienteInput();
        tipoDependenteSelecionado = InputValidator.verificarInput1ToN(tipoDependenteSelecionado, 2);
        TipoDependente tipoDependente = escolhaDependente(tipoDependenteSelecionado);

        System.out.println("Nome do dependente: ");
        String nomeDependente = scanner.nextLine();

        System.out.println("Digite o CPF do dependente(formato 9dígitos, sem pontos): ");
        String cpfDependente = InputValidator.validarCpf(scanner);

        System.out.println("Digite a data nascimento");
        String stringNascimentoDependente = scanner.nextLine();
        stringNascimentoDependente = InputValidator.validarStringData(stringNascimentoDependente);

        LocalDate dataNascimentoDependente = LocalDate.parse(stringNascimentoDependente, formatter);
        Integer idadeAtual = (Year.now().getValue()) - dataNascimentoDependente.getYear();

        VitaCareMenu.exibirCoberturaBase();
        Integer tipoCobertura = InputValidator.getClienteInput();
        tipoCobertura = InputValidator.verificarInput1ToN(tipoCobertura,3);
        Cobertura cobertura = escolhaCobertura(tipoCobertura);

        dadosDependente.add(nomeDependente);
        dadosDependente.add(cpfDependente);
        dadosDependente.add(dataNascimentoDependente);
        dadosDependente.add(idadeAtual);
        dadosDependente.add(cobertura);
        dadosDependente.add(tipoDependente);

        return dadosDependente;
    }

    static void exibirQtdEAdicionarDependentes(Beneficiario beneficiario, List<Beneficiario> lista){
        if (((Titular) beneficiario).getListaDependentes().isEmpty()) {
            System.out.println("O beneficiaro nao tem dependentes");
        } else {
            System.out.println("O beneficiario possui " + ((Titular) beneficiario).getListaDependentes().size() + " dependentes.");
            for (Dependente dependentes : ((Titular) beneficiario).getListaDependentes()) {
                lista.add(dependentes);
            }
        }
    }

    static void exibirResumo(Beneficiario beneficiarioSelecionado) {

        List<Beneficiario> listaDependentes = new ArrayList<>();
        Double mensalidadeDoBeneficiario = 0.0;

        System.out.println("Gerando resumo para: " + beneficiarioSelecionado.getNome());
        DelayTimer.delay(700);
        System.out.println("------------------------------------");
        System.out.println("Titular: " + beneficiarioSelecionado.getNome());
        System.out.println("CPF: " + beneficiarioSelecionado.getCPF());
        System.out.println("Mensalidade: " + ((Titular) beneficiarioSelecionado).valorMensalidade());
        DelayTimer.delay(700);

        mensalidadeDoBeneficiario += ((Titular) beneficiarioSelecionado).valorMensalidade();

        exibirQtdEAdicionarDependentes(beneficiarioSelecionado, listaDependentes);

        if (!listaDependentes.isEmpty()) {
            System.out.println("Mostrando dependentes...");
            DelayTimer.delay(700);
            for (Beneficiario dependenteBeneficiario : listaDependentes) {
                if (listaDependentes.indexOf(dependenteBeneficiario) == 0) {
                    System.out.println();
                } else {
                    System.out.println("------------------------------------");
                }

                System.out.println("Dependente: " + dependenteBeneficiario.getNome());
                System.out.println("CPF: " + dependenteBeneficiario.getCPF());
                System.out.println("Tipo beneficiario: " + ((Dependente) dependenteBeneficiario).getTipoDependente());
                System.out.println("Mensalidade: " + String.format("R$ %.2f", ((Dependente) dependenteBeneficiario).valorMensalidade()));
                mensalidadeDoBeneficiario += ((Dependente) dependenteBeneficiario).valorMensalidade();
                DelayTimer.delay(700);
            }
        }
        System.out.println("------------------------------------");
        System.out.println("Calculando mensalidade...");
        DelayTimer.delay(700);
        System.out.println("Mensalidade para o titular: " + beneficiarioSelecionado.getNome() + " e de: R$ " + mensalidadeDoBeneficiario);
        DelayTimer.delay(700);
    }

    static void exibirCobertura(Beneficiario beneficiarioSelecionado) {
        List<Beneficiario> listaBeneficiarios = new ArrayList<>();
        System.out.println("Voce selecionou: " + beneficiarioSelecionado.getNome());
        System.out.println("A cobertura deste beneficiario e: " + beneficiarioSelecionado.getCobertura());
        exibirQtdEAdicionarDependentes(beneficiarioSelecionado, listaBeneficiarios);
        if (!listaBeneficiarios.isEmpty()) {
            System.out.println("Checando coberturas... ");
            DelayTimer.delay(700);
            for (Dependente dependenteBeneficiario :
                    ((Titular) beneficiarioSelecionado).getListaDependentes()) {
                System.out.println("O dependente: " + dependenteBeneficiario.getNome() + " tem cobertura: " + dependenteBeneficiario.getCobertura());
                DelayTimer.delay(700);
            }
        }
    }

    static void exibirExamesAgendado() {
        System.out.println("Mostrando dos exames agendados: ");
        VitaCareMenu.exibirListaExames();
    }

    static ArrayList<Object> cadastrarUsuario(Scanner scanner) {

        ArrayList<Object> dadosUsuarios = new ArrayList<>();

        System.out.println("Bem vindo ao cadastro da VitaCare");
        System.out.println("Digite o nome do titular: ");
        String nomeTitular = scanner.nextLine();
        dadosUsuarios.add(nomeTitular);

        System.out.println("Digite o CPF do titular(formato 9dígitos, sem pontos): ");
        String cpfTitular = InputValidator.validarCpf(scanner);
        dadosUsuarios.add(cpfTitular);

        System.out.println("Digite a data de nascimento do titular (formato dd/MM/yyyy): ");
        String dataNascimentoString = scanner.nextLine();
        dataNascimentoString = InputValidator.validarStringData(dataNascimentoString);
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
        dadosUsuarios.add(dataNascimento);

        VitaCareMenu.exibirCoberturaBase();
        Integer opcaoCoberturaTitular = InputValidator.getClienteInput();
        opcaoCoberturaTitular = InputValidator.verificarInput1ToN(opcaoCoberturaTitular, 3);

        Cobertura coberturaTitular = escolhaCobertura(opcaoCoberturaTitular);
        dadosUsuarios.add(coberturaTitular);

        return dadosUsuarios;
    }

    static boolean filhoMaiorIdade (TipoDependente tipoDependente, Integer idade){
        if (tipoDependente.equals(TipoDependente.FILHO) && idade > 25) {
            System.out.println("Filhos só podem ser dependentes até 24anos!");
            return true;
        } else {
            return false;
        }
    }

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
