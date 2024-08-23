package br.com.pipocaagil.CorraAgil.DTO;

import jakarta.validation.constraints.NotNull;

public record DadosToUpdateCadastroDTO(
        @NotNull
        Long id,
        String nomecompleto,
        String senha) {
}
