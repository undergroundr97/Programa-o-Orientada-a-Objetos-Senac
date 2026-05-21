package org.example.InputValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class InputValidator {

    public static Scanner scanner = new Scanner(System.in);

    public static boolean intValidator(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Input invalido");
            scanner.next();
        }
        return true;
    }

    public static String validarCpf(Scanner scanner) {
       String string = scanner.nextLine();
        while (string.length() < 9 || string.length() > 10) {
            System.out.println("CPF invalido!");
            string = scanner.nextLine();
        }
        return string;
    }

    public static String validarStringData(String string) throws DateTimeParseException {
        boolean teste = false;
        while (!teste) {
            try {
                LocalDate.parse(string, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (LocalDate.parse(string, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear() < 1900) {
                    throw new DateTimeParseException("Ano inconsistente", string, 0);
                }
                teste = true;

            } catch (DateTimeParseException e) {
                e.getMessage();
                System.out.println("Data invalida, digite no formato dd/MM/yyyy");
                string = scanner.nextLine();
            }
        }
        return string;
    }

    public static Integer valueIn0toListSize(Integer selecionado, List<?> list) {
        while (selecionado <= 0 || selecionado > list.size()) {
            System.out.println("Selecao Invalida");
            selecionado = InputValidator.getClienteInput();
        }
        return selecionado;
    }

    public static Integer verificarInput1To2(Integer input) {{
            while (input < 1 || input > 2) {
                System.out.println("Input Invalido");
                input = InputValidator.getClienteInput();
            }
            return input;
        }
    }

    public static Integer verificarInput1To3(Integer input) {
        while (input < 1 || input > 3) {
            System.out.println("Input Invalido");
            input = InputValidator.getClienteInput();
        }
        return input;
    }

    public static Integer verificarInput1To4(Integer input) {
        while (input < 1 || input > 4) {
            System.out.println("Input Invalido");
            input = InputValidator.getClienteInput();
        }
        return input;
    }

    public static String verificarSimNao(String string){
        while (!string.equalsIgnoreCase("s") && !string.equalsIgnoreCase("n")) {
            System.out.println("Digite S/N");
            string = scanner.nextLine();
        }
        return string;
    }

    public static Integer getClienteInput() {
        InputValidator.intValidator(scanner);
        Integer input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static boolean listaVazia(List<?> lista){
        if(lista.isEmpty()){
            System.out.println("Nenhum usuario encontrado");
            return true;
        } else {
            return false;
        }
    }

    public static boolean listSizeMaior3(List<?> lista){
        if (lista.size() >= 3){
            return true;
        } else {
            return false;
        }
    }

}