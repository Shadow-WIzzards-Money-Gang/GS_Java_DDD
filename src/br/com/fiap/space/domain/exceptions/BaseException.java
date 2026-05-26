package br.com.fiap.space.domain.exceptions;

import br.com.fiap.space.util.Cor;

public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(
            Cor.VERMELHO.getCodigo() +
            message +
            Cor.RESET.getCodigo()
        );
    }
}
