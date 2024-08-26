package br.com.pipocaagil.CorraAgil.DTO;

import br.com.pipocaagil.CorraAgil.model.CadastroModel;

public record ResponseBodyStatusCadastroDTO(
        Long id,
        String nomecompleto,
        String email) {

    public ResponseBodyStatusCadastroDTO(CadastroModel cadastroModel) {
        this(cadastroModel.getId(), cadastroModel.getNomecompleto(), cadastroModel.getEmail());
    }
}
