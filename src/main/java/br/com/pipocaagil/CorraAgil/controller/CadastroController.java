package br.com.pipocaagil.CorraAgil.controller;

import br.com.pipocaagil.CorraAgil.DTO.CadastroDTO;
import br.com.pipocaagil.CorraAgil.services.CadastroServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corragil/v1")
public class CadastroController {
    @Autowired
    CadastroServices services;

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CadastroDTO findById(@PathVariable(value = "id") Long id) throws Exception {
        return services.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CadastroDTO> findAll() {
        return services.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CadastroDTO> saveCadastro(@Valid @RequestBody CadastroDTO createCadastro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.create(createCadastro));
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CadastroDTO update(@Valid @RequestBody CadastroDTO updateCadastro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.update(updateCadastro)).getBody();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long deleteCadastro) {
        services.delete(deleteCadastro);
        return ResponseEntity.noContent().build();
    }
}
