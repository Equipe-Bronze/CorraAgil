package br.com.pipocaagil.CorraAgil.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosLoginRequestDTO(
        @NotBlank(message = "O login é obrigatório")
        @Size(max = 100, message = "O login deve ter no máximo 100 caracteres")
        String login,
        @NotBlank(message = "A senha é obrigatória")
        @Size(max = 255, message = "A senha deve ter no máximo 255 caracteres")
        String senha) {
}
