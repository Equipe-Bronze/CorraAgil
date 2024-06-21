package br.com.pipocaagil.CorraAgil.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="Cadastro")
public class CadastroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomecompleto;

    @Column(unique = true)
    private String email;
    @Email
    @Length(min = 8)
    private String senha;
}
