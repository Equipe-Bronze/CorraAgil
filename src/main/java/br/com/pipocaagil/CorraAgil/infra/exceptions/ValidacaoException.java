package br.com.pipocaagil.CorraAgil.infra.exceptions;

public class ValidacaoException extends Throwable {
    public ValidacaoException() {
        super("Email já cadastrado para outro usuário!");
    }

    public ValidacaoException(String message) {
        super(message);
    }
}
