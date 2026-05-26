package br.com.fiap.space.domain.valueobject;

import br.com.fiap.space.domain.exceptions.CoordernadasInvalidasException;

public class Coordernada {

    private Integer eixoX;
    private Integer eixoY;

    public Coordernada(Integer eixoX, Integer eixoY) {

        if (eixoX < 0 || eixoY < 0) {
            throw new CoordernadasInvalidasException();
        }

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
