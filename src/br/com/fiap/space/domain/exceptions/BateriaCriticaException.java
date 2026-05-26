package br.com.fiap.space.domain.exceptions;

public class BateriaCriticaException extends BaseException {
    public BateriaCriticaException() {
        super("Bateria insuficiente para realizar a operação.");
    }
}
