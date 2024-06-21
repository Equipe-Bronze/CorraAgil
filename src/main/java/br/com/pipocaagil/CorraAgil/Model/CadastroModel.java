package br.com.pipocaagil.CorraAgil.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String nomecompleto;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Length(min = 8)
    private String senha;
}
