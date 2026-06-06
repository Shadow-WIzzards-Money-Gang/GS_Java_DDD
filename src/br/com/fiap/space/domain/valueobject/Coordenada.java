package br.com.fiap.space.domain.valueobject;

import br.com.fiap.space.domain.exceptions.CoordenadasInvalidasException;

public class Coordenada {

    private Integer eixoX;
    private Integer eixoY;

    public Coordenada(Integer eixoX, Integer eixoY) {

        if (eixoX < 0 || eixoY < 0) {
            throw new CoordenadasInvalidasException();
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
