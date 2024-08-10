package br.com.pipocaagil.CorraAgil.repositories;

import br.com.pipocaagil.CorraAgil.model.CadastroModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CadastroRepository extends JpaRepository<CadastroModel, Long> {
    Optional<CadastroModel> findByEmail(String loginEmail);
}
