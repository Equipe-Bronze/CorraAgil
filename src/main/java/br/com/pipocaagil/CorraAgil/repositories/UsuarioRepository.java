package br.com.pipocaagil.CorraAgil.repositories;

import br.com.pipocaagil.CorraAgil.domain.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    UserDetails findByLogin(String login);
}
