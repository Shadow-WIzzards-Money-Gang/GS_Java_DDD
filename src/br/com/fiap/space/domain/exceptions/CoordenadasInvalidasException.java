package br.com.fiap.space.domain.exceptions;

public class CoordenadasInvalidasException extends BaseException {
    public CoordenadasInvalidasException() {
        super("As coordenadas devem ser maiores ou iguais a 0.");
    }
}
