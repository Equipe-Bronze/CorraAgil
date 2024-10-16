package br.com.pipocaagil.CorraAgil.Cadastro;

public class CadastroNotFoundException extends RuntimeException {
    public CadastroNotFoundException(String message) {
        super(message);
    }
}