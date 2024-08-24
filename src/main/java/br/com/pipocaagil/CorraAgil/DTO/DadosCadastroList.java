package br.com.pipocaagil.CorraAgil.DTO;

import br.com.pipocaagil.CorraAgil.model.CadastroModel;

public record DadosCadastroList(
        Long id,
        String nomecompleto,
        String email) {
    public DadosCadastroList(CadastroModel cadastroModelList) {
        this(cadastroModelList.getId(), cadastroModelList.getNomecompleto(), cadastroModelList.getEmail());
    }
}
