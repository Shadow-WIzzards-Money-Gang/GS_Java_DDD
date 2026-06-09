package br.com.fiap.space.domain.exceptions;

public class AlcanceSensorInvalidoException extends BaseException {
    public AlcanceSensorInvalidoException() {
        super("O alcance do sensor deve ser estritamente maior que zero.");
    }
}
