package br.com.fiap.space.domain.exceptions;

public class CoordernadasInvalidasException extends BaseException {
    public CoordernadasInvalidasException() {
        super("As coordenadas devem ser maiores ou iguais a 0.");
    }
}
