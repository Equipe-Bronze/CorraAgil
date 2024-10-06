package br.com.pipocaagil.CorraAgil.Cadastro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadastroRepository  extends JpaRepository< CadastroModel, Long> {
    Optional<CadastroModel> findByEmail(String email);
}
