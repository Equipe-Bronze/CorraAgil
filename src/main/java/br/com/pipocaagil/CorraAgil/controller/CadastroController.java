package br.com.pipocaagil.CorraAgil.controller;

import br.com.pipocaagil.CorraAgil.DTO.CadastroDTO;
import br.com.pipocaagil.CorraAgil.DTO.LoginRequestDTO;
import br.com.pipocaagil.CorraAgil.DTO.RegisterRequestDTO;
import br.com.pipocaagil.CorraAgil.DTO.ResponseDTO;
import br.com.pipocaagil.CorraAgil.infra.security.TokenService;
import br.com.pipocaagil.CorraAgil.model.CadastroModel;
import br.com.pipocaagil.CorraAgil.repositories.CadastroRepository;
import br.com.pipocaagil.CorraAgil.services.CadastroServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/corragil/v1")
@RequiredArgsConstructor
public class CadastroController {
    private final CadastroServices services;
    private final CadastroRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokerService;

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

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestBodyDTO) {
        CadastroModel userEntityCadastroModel = this.repository.findByEmail(loginRequestBodyDTO.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (passwordEncoder.matches(userEntityCadastroModel.getSenha(), loginRequestBodyDTO.password())) {
            String token = this.tokerService.generateToken(userEntityCadastroModel);
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
            String tokenEntity = this.tokerService.generateToken(newUserEntity);
            return ResponseEntity.ok(new ResponseDTO(newUserEntity.getNomecompleto(), tokenEntity));

        }
        return ResponseEntity.badRequest().build();
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
