package br.com.pipocaagil.CorraAgil.controller;

import br.com.pipocaagil.CorraAgil.DTO.CadastroDto;
import br.com.pipocaagil.CorraAgil.model.CadastroModel;
import br.com.pipocaagil.CorraAgil.repository.CadastroRepository;
import br.com.pipocaagil.CorraAgil.services.CadastroServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
    @Autowired
    CadastroServices services;

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CadastroDto findById(@PathVariable(value = "id") Long id) throws Exception {
        return services.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CadastroDto> findAll() {
        return services.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CadastroDto> saveCadastro(@RequestBody @Valid CadastroDto createCadastro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.create(createCadastro));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CadastroDto update(@RequestBody CadastroDto updateCadastro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.update(updateCadastro)).getBody();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long deleteCadastro) {
        services.delete(deleteCadastro);
        return ResponseEntity.noContent().build();
    }
}
