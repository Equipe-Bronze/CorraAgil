package br.com.pipocaagil.CorraAgil.repositories;

import br.com.pipocaagil.CorraAgil.domain.CadastroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CadastroRepository extends JpaRepository<CadastroModel, Long> {
    Optional<CadastroModel> findByEmail(String loginEmail);
}
