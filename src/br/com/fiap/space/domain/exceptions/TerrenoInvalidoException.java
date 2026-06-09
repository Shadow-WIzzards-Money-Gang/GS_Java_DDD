package br.com.fiap.space.domain.exceptions;

public class TerrenoInvalidoException extends BaseException {
    public TerrenoInvalidoException() {
        super("Sonda com rodas não pode entrar em uma cratera profunda.");
    }
}
