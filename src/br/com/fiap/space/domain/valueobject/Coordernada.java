package br.com.fiap.space.domain.valueobject;

public class Coordernada {

    private Integer eixoX;
    private Integer eixoY;

    public Coordernada(Integer eixoX, Integer eixoY) {
        this.eixoX = eixoX;
        this.eixoY = eixoY;
    }

    public Integer getEixoX() {
        return eixoX;
    }

    public Integer getEixoY() {
        return eixoY;
    }
}
