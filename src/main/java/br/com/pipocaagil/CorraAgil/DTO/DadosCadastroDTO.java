package br.com.pipocaagil.CorraAgil.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroDTO(
        @NotBlank
        String nomecompleto,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String senha) {
}
