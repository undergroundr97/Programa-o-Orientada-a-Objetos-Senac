package org.example.AgendarExame;

public class Doutor {

    private String nome;
    private String especializacao;

    public Doutor() {
        this.nome = gerarNomeDoutor()[0];
        this.especializacao = gerarNomeDoutor()[1];
    }

    static String[] gerarNomeDoutor() {
        String[] nomeDoutores = new String[]{"Fulano", "Siclano", "Deutrano", "Zezin", "Beltrano", "Mariazinha"};
        Integer nomeAleatorio = (int) (Math.random() * nomeDoutores.length);
        String[] especialidades = new String[]{"Cardiologia", "Dermatologia", "Pediatria", "Ortopedia", "Neurologia", "Ginecologia", "Oftalmologia"
                , "Psiquiatria", "Endocrinologia", "Gastroenterologia", "Oncologia", "Urologia", "Otorrinolaringologia", "Reumatologia", "Nefrologia"};
        Integer especialidadeAleatoria = (int) (Math.random() * nomeDoutores.length);
        String[] stringNomeDoutor = new String[]{nomeDoutores[nomeAleatorio], especialidades[especialidadeAleatoria]};
        return stringNomeDoutor;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecializacao() {
        return especializacao;
    }
}
