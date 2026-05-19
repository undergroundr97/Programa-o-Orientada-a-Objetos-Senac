package org.exmaple.InputValidor;

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


    public static Integer getClienteInput(){
        InputValidator.intValidator(scanner);
        Integer input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}