package br.com.pipocaagil.CorraAgil.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DadosToUpdateCadastroDTO(
        @NotNull
        Long id,
//        @NotNull
//        @NotEmpty
//        @NotBlank
        String nomecompleto,
//        @NotNull
//        @NotEmpty
//        @NotBlank
        String senha) {
}
