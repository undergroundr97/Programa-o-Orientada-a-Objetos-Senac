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
        CartaoCredito cartaoCredito = new CartaoCredito(digitosCartao, "Vitor", 5000.0, cvv );
        ProcessadorPegamento processadorPegamento = new ProcessadorPegamento(cartaoCredito);
        System.out.println("-------------------------------");
        System.out.println("Realizar venda de R$2000.0, saldo atual: R$" + cartaoCredito.getLimiteDisponivel());
        processadorPegamento.executarVenda(2000.0);
        System.out.println("Saldo atual do cartão: R$" + cartaoCredito.getLimiteDisponivel());
        System.out.println("-------------------------------");
        System.out.println("Realizar a venda de R$4000.0, saldo atual: R$" +cartaoCredito.getLimiteDisponivel());
        processadorPegamento.executarVenda(4000.0);
        System.out.println("-------------------------------");
        cartaoCredito.dadosCartao();
        System.out.println("-------------------------------");
        System.out.println("Estornando R$5000.0");
        processadorPegamento.estornarVenda(5000.0);
        System.out.println("Novo limite: R$" + cartaoCredito.getLimiteDisponivel());

    }
}
