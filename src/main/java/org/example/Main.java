package org.example;


public class Main {
    static void main() {
            String digitosCartao = "";
        for (int i = 0; i < 4 ; i++) {
            int quatroDigitosAleatorios = (int) (Math.random() * 1000);
            String digitos = String.valueOf(quatroDigitosAleatorios);
            if(digitos.length() <= 3){
               digitos = String.format("%4s", digitos).replace(' ', '0');
            }
            digitosCartao += digitos + " ";
        }
        String cvv = "";
        for (int i = 0; i < 1 ; i++) {
            int tresDigitosAleatorios = (int) (Math.random() * 1000);
            String digitos = String.valueOf(tresDigitosAleatorios);
            if(digitos.length() < 3){
                digitos = String.format("%3s", digitos).replace(' ', '0');
            }
            cvv = digitos;
        }
        System.out.println("CVV: " + cvv);
        System.out.println("NUMERO CARTAO " + digitosCartao);
//        CartaoCredito cartaoCredito = new CartaoCredito(digitosCartao, "Vitor", 5000.0, "331" )

    }
}
