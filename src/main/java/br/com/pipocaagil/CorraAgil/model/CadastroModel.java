package br.com.pipocaagil.CorraAgil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "corragil")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CadastroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomecompleto")
    @NotBlank
    @Pattern(regexp = "^[A-Z]+(.)*")
    private String nomecompleto;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "senha")
    @Length(min = 8)
    @NotBlank
    private String senha;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CadastroModel that = (CadastroModel) o;
        return id.equals(that.id) && nomecompleto.equals(that.nomecompleto) && email.equals(that.email) && senha.equals(that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomecompleto, email, senha);
    }
}
