package br.com.pipocaagil.corraagil.Cadastro;

public class CadastroNotFoundException extends RuntimeException {
    public CadastroNotFoundException(String message) {
        super(message);
    }
}