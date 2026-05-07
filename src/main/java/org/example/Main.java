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
        CartaoCredito cartaoCredito = new CartaoCredito(digitosCartao, "Vitor", 5000.0, cvv );
        ProcessadorPegamento processadorPegamento = new ProcessadorPegamento(cartaoCredito);
        System.out.println("Realizar venda de 4000, saldo atual: " + cartaoCredito.getLimiteDisponivel());
        processadorPegamento.executarVenda(2000.0);
        System.out.println("Saldo atual do cartão: " + cartaoCredito.getLimiteDisponivel());
        System.out.println("Realizar a venda de 4000, saldo atual: " + cartaoCredito.getLimiteDisponivel());
        processadorPegamento.executarVenda(4000.0);
        cartaoCredito.dadosCartao();


    }
}
