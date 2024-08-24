package br.com.pipocaagil.CorraAgil.model;

import br.com.pipocaagil.CorraAgil.DTO.DadosCadastroDTO;
import br.com.pipocaagil.CorraAgil.DTO.DadosToUpdateCadastroDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Corragil")
@Table(name = "corragil")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CadastroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomecompleto;
    private String email;
    private String senha;

    public CadastroModel(DadosCadastroDTO createCadastroDTO) {
        this.nomecompleto = createCadastroDTO.nomecompleto();
        this.email = createCadastroDTO.email();
        this.senha = createCadastroDTO.senha();
    }

    public void updateDadosPacientes(DadosToUpdateCadastroDTO updateCadastro) {
        if (updateCadastro.nomecompleto() != null) {
            this.nomecompleto = updateCadastro.nomecompleto();
        }

        if (updateCadastro.senha() != null) {
            this.senha = updateCadastro.senha();
        }
    }
}
