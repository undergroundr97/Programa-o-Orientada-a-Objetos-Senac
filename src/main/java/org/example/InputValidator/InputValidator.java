package org.example.InputValidator;

import java.time.DateTimeException;
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

    public static Integer valueIn0toSize(Integer selecionado, List<?> list) {
        while (selecionado <= 0 || selecionado > list.size()) {
            System.out.println("Selecao Invalida");
            selecionado = InputValidator.getClienteInput();
        }
        return selecionado;
    }


    public static Integer getClienteInput() {
        InputValidator.intValidator(scanner);
        Integer input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

}