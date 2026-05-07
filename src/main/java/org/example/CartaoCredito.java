package org.example;

public class CartaoCredito {
    private String numeroCartao;
    private String titular;
    private Double limiteDisponivel;
    private String cvv;

    public CartaoCredito(String numeroCartao, String titular, Double limiteDisponivel, String cvv) {
        this.numeroCartao = numeroCartao;
        this.titular = titular;
        this.limiteDisponivel = limiteDisponivel;
        this.cvv = cvv;
        }

        public void alterarLimite(Double novoLimite){
            if(novoLimite < 0 ){
                System.out.println("Valor incorreto!");
            } else {
                this.limiteDisponivel += novoLimite;
            }
        }
        public void removerLimite(Double valor){
            limiteDisponivel -= valor;
        }
        public void adicionarLimite(Double valor){
            limiteDisponivel += valor;
        }

    public Double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void dadosCartao(){
        System.out.println("Dados do cartão: ");
        System.out.println("NUMERO CARTAO: " + getNumeroCartao());
        System.out.println("TITULAR: " + getTitular());
        System.out.println("SALDO DISPONIVEL: " +  getLimiteDisponivel());
    }

}
