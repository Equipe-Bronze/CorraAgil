package br.com.pipocaagil.CorraAgil.services;

import br.com.pipocaagil.CorraAgil.DTO.CadastroDto;
import br.com.pipocaagil.CorraAgil.mapper.DozerMapper;
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
    public CadastroDto findById(Long id) {
        // Message Logger Spring Data
        logger.info("Procurando uma pessoa no cadastro");

        CadastroModel entityModel = cadastroRepository.findById(id).orElseThrow();
        return DozerMapper.parserObject(entityModel, CadastroDto.class);
    }

    /**
     * Method to get all cadastro
     *
     * @return
     */
    public List<CadastroDto> findAll() {
        // Message Logger Spring Data
        logger.info("Procurando todas pessoa da lista de cadastro");

        return DozerMapper.parserListObject(cadastroRepository.findAll(), CadastroDto.class);
    }

    /**
     * Method to create new cadastro
     *
     * @param addCadastroDTO
     * @return
     */
    public CadastroDto create(CadastroDto addCadastroDTO) {
        // Message Logger Spring Data
        logger.info("Creiando uma pessoa na lista de cadastro!");
        CadastroModel entityModel = DozerMapper.parserObject(addCadastroDTO, CadastroModel.class);
        CadastroDto entityDTO = DozerMapper.parserObject(cadastroRepository.save(entityModel), CadastroDto.class);

        return entityDTO;
    }

    /**
     * Method to update cadastro
     *
     * @param updateCadastroDTO
     * @return
     */
    public CadastroDto update(CadastroDto updateCadastroDTO) {
        // Message Logger Spring Data
        logger.info("Atualiznado uma pessoa na lista de cadastro");

        CadastroModel entityUpdateCadastroModel = cadastroRepository.findById(updateCadastroDTO.getId()).orElseThrow();

        entityUpdateCadastroModel.setEmail(updateCadastroDTO.getEmail());
        entityUpdateCadastroModel.setNomecompleto(updateCadastroDTO.getNomecompleto());
        entityUpdateCadastroModel.setSenha(updateCadastroDTO.getSenha());

        return DozerMapper.parserObject(cadastroRepository.save(entityUpdateCadastroModel), CadastroDto.class);
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
