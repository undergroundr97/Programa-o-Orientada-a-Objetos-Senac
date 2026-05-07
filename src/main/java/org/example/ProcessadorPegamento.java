package org.example;

public class ProcessadorPegamento {
    private CartaoCredito cartaoCredito;

    public ProcessadorPegamento(CartaoCredito cartaoCredito){
        this.cartaoCredito = cartaoCredito;
    }

    public void executarVenda(Double valor){
        if(cartaoCredito.getLimiteDisponivel() < valor){
            System.out.println("Transação Negada");
        } else {
          cartaoCredito.removerLimite(valor);
        }
    }

    public void estornarVenda(Double valor){
        cartaoCredito.adicionarLimite(valor);
    }
}
