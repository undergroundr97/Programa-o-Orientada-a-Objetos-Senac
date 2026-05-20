package org.example.InputValidator;

import java.util.List;
import java.util.Scanner;

public class InputValidator {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean intValidator(Scanner scanner){
        while(!scanner.hasNextInt()){
            System.out.println("Input invalido");
            scanner.next();
        }
        return true;
    }

    public static boolean doubleValidator(Scanner scanner){
        while(!scanner.hasNextDouble()){
            System.out.println("Input invalido");
            scanner.next();
        }
        return true;
    }

    public static String yesNoValidator(String yesNo){
        while(!yesNo.equalsIgnoreCase("s") && !yesNo.equalsIgnoreCase("n")){
            System.out.println("Input invalido, digite S/N");
            yesNo = scanner.next();
        }
        return yesNo;
    }

    public static Integer inputInRange0to4(Integer input){
        int inp = input;
        while(inp < 0 || inp > 4){
            System.out.println("Input Invalido");
            InputValidator.intValidator(scanner);
            inp = scanner.nextInt();
        }
        return inp;
    }

    public static String validarStringData(String string){
        while(!string.matches("^\\d{2}/\\d{2}/\\d{4}$")){
            System.out.println("Data invalida, digite no formato dd/MM/yyyy");
            string = scanner.nextLine();
        }
        return string;
    }
    public static Integer valueIn0toSize(Integer selecionado, List<?> list ){
        while(selecionado <= 0 || selecionado > list.size()){
            System.out.println("Selecao Invalida");
            selecionado = InputValidator.getClienteInput();
        }
        return selecionado;
    }



    public static Integer getClienteInput(){
        InputValidator.intValidator(scanner);
        Integer input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}