package br.com.pipocaagil.CorraAgil.services;

import br.com.pipocaagil.CorraAgil.model.CadastroModel;
import br.com.pipocaagil.CorraAgil.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CadastroServices {
    private Logger logger = Logger.getLogger(CadastroServices.class.getName());

    @Autowired
    CadastroRepository cadastroRepository;

    /**
     * Method to get cadastro by id
     *
     * @param id
     * @return
     */
    public CadastroModel findById(Long id) {
        // Message Logger Spring Data
        logger.info("Procurando uma pessoa no cadastro");

        return cadastroRepository.findById(id).orElseThrow();
    }

    /**
     * Method to get all cadastro
     *
     * @return
     */
    public List<CadastroModel> findAll() {
        // Message Logger Spring Data
        logger.info("Procurando todas pessoa da lista de cadastro");

        return cadastroRepository.findAll();
    }

    /**
     * Method to create new cadastro
     *
     * @param addCadastro
     * @return
     */
    public CadastroModel create(CadastroModel addCadastro) {
        // Message Logger Spring Data
        logger.info("Creiando uma pessoa na lista de cadastro!");

        return cadastroRepository.save(addCadastro);
    }

    /**
     * Method to update cadastro
     *
     * @param updateCadastro
     * @return
     */
    public CadastroModel update(CadastroModel updateCadastro) {
        // Message Logger Spring Data
        logger.info("Atualiznado uma pessoa na lista de cadastro");

        CadastroModel auxUpdateCadastroModel = cadastroRepository.findById(updateCadastro.getId()).orElseThrow();

        auxUpdateCadastroModel.setEmail(updateCadastro.getEmail());
        auxUpdateCadastroModel.setNomecompleto(updateCadastro.getNomecompleto());
        auxUpdateCadastroModel.setSenha(updateCadastro.getSenha());

        return cadastroRepository.save(updateCadastro);
    }

    /**
     * Method to delete cadastro
     *
     * @param deleteCadastro
     */
    public void delete(Long deleteCadastro) {
        logger.info("Deletando uma pessoa na lista de cadastro");
        CadastroModel auxDeleteCadastro = cadastroRepository.findById(deleteCadastro).orElseThrow();

        cadastroRepository.delete(auxDeleteCadastro);
    }
}
