package br.com.fiap.space.domain.exceptions;

public class CapacidadeBateriaInvalidaException extends BaseException {
    public CapacidadeBateriaInvalidaException() {
        super("A capacidade da bateria deve ser maior ou igual a 0.");
    }
}
