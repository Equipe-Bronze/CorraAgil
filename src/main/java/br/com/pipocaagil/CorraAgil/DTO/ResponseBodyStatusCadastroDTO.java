package br.com.pipocaagil.CorraAgil.DTO;

import br.com.pipocaagil.CorraAgil.domain.UsuarioModel;

public record ResponseBodyStatusCadastroDTO(
        Long id,
        String nomecompleto,
        String email) {

    public ResponseBodyStatusCadastroDTO(UsuarioModel cadastroModel) {
        this(cadastroModel.getId(), cadastroModel.getNomecompleto(), cadastroModel.getLogin());
    }
}
