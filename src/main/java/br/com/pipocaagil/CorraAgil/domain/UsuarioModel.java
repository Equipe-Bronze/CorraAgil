package br.com.pipocaagil.CorraAgil.domain;

import br.com.pipocaagil.CorraAgil.DTO.DadosCadastroDTO;
import br.com.pipocaagil.CorraAgil.DTO.DadosToUpdateCadastroDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    private String nomecompleto;

    public UsuarioModel(DadosCadastroDTO createCadastroDTO) {
        this.nomecompleto = createCadastroDTO.nomecompleto();
        this.login = createCadastroDTO.login();
        this.senha = createCadastroDTO.senha();
    }

    public void updateDadosPacientes(DadosToUpdateCadastroDTO updateCadastro) {
        if (updateCadastro.nomecompleto() != null) {
            this.nomecompleto = updateCadastro.nomecompleto();
        }
        if (updateCadastro.login() != null) {
            this.login = updateCadastro.login();
        }

        if (updateCadastro.senha() != null) {
            this.senha = updateCadastro.senha();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
