package br.com.fiap.space.domain.enums;

public enum Recurso {

    GELO("Gelo", 1.0),
    REGOLITO("Regolito", 2.0),
    TITANIO("Titanio", 3.0);

    private String nome;
    private Double pesoPorUnidade;

    Recurso(String nome, Double pesoPorUnidade){
        this.nome = nome;
        this.pesoPorUnidade = pesoPorUnidade;
    }

    public String getNome() {
        return nome;
    }

    public Double getPesoPorUnidade() {
        return pesoPorUnidade;
    }
}
