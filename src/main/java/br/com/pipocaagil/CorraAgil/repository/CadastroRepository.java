package br.com.pipocaagil.CorraAgil.repository;

import br.com.pipocaagil.CorraAgil.model.CadastroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<CadastroModel,Long > {
}
