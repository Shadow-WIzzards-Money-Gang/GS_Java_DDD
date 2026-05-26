package br.com.fiap.space.domain.exceptions;

public class SondaNaoEncontradaException extends BaseException {
    public SondaNaoEncontradaException() {
        super("Sonda não encontrada.");
    }
}
