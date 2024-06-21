package br.com.pipocaagil.CorraAgil.Repository;

import br.com.pipocaagil.CorraAgil.Model.CadastroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<CadastroModel,Long > {
}
