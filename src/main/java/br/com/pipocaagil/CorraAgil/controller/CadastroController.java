package br.com.pipocaagil.CorraAgil.controller;

import br.com.pipocaagil.CorraAgil.DTO.*;
import br.com.pipocaagil.CorraAgil.infra.security.TokenService;
import br.com.pipocaagil.CorraAgil.model.CadastroModel;
import br.com.pipocaagil.CorraAgil.repositories.CadastroRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/corragil/v1")
@RequiredArgsConstructor
public class CadastroController {
    private final CadastroRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DadosCadastroList> findAll() {
        return repository.findAll().stream().map(DadosCadastroList::new).toList();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void createCadastro(@RequestBody @Valid DadosCadastroDTO createCadastro) {
        repository.save(new CadastroModel(createCadastro));
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void update(@RequestBody @Valid DadosToUpdateCadastroDTO updateCadastro) {
        CadastroModel entityModel = repository.getReferenceById(updateCadastro.id());
        entityModel.updateDadosPacientes(updateCadastro);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        CadastroModel entityDelete = repository.findById(id).orElseThrow();
        repository.delete(entityDelete);
//        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestBodyDTO) {
        CadastroModel userEntityCadastroModel = this.repository.findByEmail(loginRequestBodyDTO.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (passwordEncoder.matches(userEntityCadastroModel.getSenha(), loginRequestBodyDTO.password())) {
            String token = this.tokenService.generateToken(userEntityCadastroModel);
            return ResponseEntity.ok(new ResponseDTO(userEntityCadastroModel.getNomecompleto(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody RegisterRequestDTO registerRequestBodyDTO) {
        Optional<CadastroModel> userEntityCadastroModel = this.repository.findByEmail(registerRequestBodyDTO.email());

        if (userEntityCadastroModel.isEmpty()) {
            CadastroModel newUserEntity = new CadastroModel();
            newUserEntity.setSenha(passwordEncoder.encode(registerRequestBodyDTO.password()));
            newUserEntity.setEmail(registerRequestBodyDTO.email());
            newUserEntity.setNomecompleto(registerRequestBodyDTO.name());
            repository.save(newUserEntity);

            // Geração do Token para o usuário
            String tokenEntity = this.tokenService.generateToken(newUserEntity);
            return ResponseEntity.ok(new ResponseDTO(newUserEntity.getNomecompleto(), tokenEntity));

        }
        return ResponseEntity.badRequest().build();
    }
}
