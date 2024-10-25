package br.com.pipocaagil.corraagil.Cadastro;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

@Repository
    public interface CadastroRepository  extends JpaRepository< CadastroModel, Long> {
        CadastroModel findByEmail(String email);
    }
