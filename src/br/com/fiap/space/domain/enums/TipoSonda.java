package br.com.fiap.space.domain.enums;

public enum TipoSonda {
    EXPLORADORA("Exploradora"),
    MINERADORA("Mineradora");

    private String nome;

    TipoSonda(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
