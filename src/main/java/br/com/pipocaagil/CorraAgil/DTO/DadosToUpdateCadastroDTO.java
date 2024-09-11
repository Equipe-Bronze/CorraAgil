package br.com.pipocaagil.CorraAgil.DTO;

import jakarta.validation.constraints.NotNull;

public record DadosToUpdateCadastroDTO(
        @NotNull
        Long id,
//        @NotNull
//        @NotEmpty
//        @NotBlank
        String nomecompleto,
        String login,
//        @NotNull
//        @NotEmpty
//        @NotBlank
        String senha) {
}
