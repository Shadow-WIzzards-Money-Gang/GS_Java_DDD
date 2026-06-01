package br.com.fiap.space.domain.exceptions;

public class TerrenoInvalidoException extends BaseException {
    public TerrenoInvalidoException() {
        super("Terreno inacessível: sondas com rodas não podem navegar em crateras profundas.");
    }
}
