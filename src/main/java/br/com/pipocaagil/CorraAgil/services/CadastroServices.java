package br.com.pipocaagil.CorraAgil.services;

import br.com.pipocaagil.CorraAgil.DTO.CadastroDto;
import br.com.pipocaagil.CorraAgil.mapper.DozerMapper;
import br.com.pipocaagil.CorraAgil.model.CadastroModel;
import br.com.pipocaagil.CorraAgil.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroServices {
    @Autowired
    CadastroRepository cadastroRepository;

    /**
     * Method to get cadastro by id
     *
     * @param id
     * @return
     */
    public CadastroDto findById(Long id) {

        CadastroModel entityModel = cadastroRepository.findById(id).orElseThrow();
        return DozerMapper.parserObject(entityModel, CadastroDto.class);
    }

    /**
     * Method to get all cadastro
     *
     * @return
     */
    public List<CadastroDto> findAll() {
        return DozerMapper.parserListObject(cadastroRepository.findAll(), CadastroDto.class);
    }

    /**
     * Method to create new cadastro
     *
     * @param addCadastroDTO
     * @return
     */
    public CadastroDto create(CadastroDto addCadastroDTO) {
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
        CadastroModel auxDeleteCadastro = cadastroRepository.findById(deleteCadastro).orElseThrow();

        cadastroRepository.delete(auxDeleteCadastro);
    }
}
