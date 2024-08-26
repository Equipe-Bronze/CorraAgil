package br.com.pipocaagil.CorraAgil.controller;

import br.com.pipocaagil.CorraAgil.DTO.*;
import br.com.pipocaagil.CorraAgil.infra.security.TokenService;
import br.com.pipocaagil.CorraAgil.model.CadastroModel;
import br.com.pipocaagil.CorraAgil.repositories.CadastroRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/corragil/v1")
@RequiredArgsConstructor
public class CadastroController {
    private final CadastroRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<DadosCadastroList>> findAll(@PageableDefault(size = 10) Pageable paginacao) {
        // Adaptando Page List de todos os cadastro
        var pageList = repository.findAll(paginacao).map(DadosCadastroList::new);

        // Return HttpStatus 200 com os dados de Page List
        return ResponseEntity.ok(pageList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> createCadastro(@RequestBody @Valid DadosCadastroDTO createCadastro,
                                            UriComponentsBuilder uriComponentsBuilder) {
        // Criando variável de um novo cadastro do object reference(CadastroModel), nova entidade no DB
        CadastroModel entityModel = repository.save(new CadastroModel(createCadastro));

        // Variável URI encapsulada com Location('endereço ou Header') para FrontEnd
        var uri = uriComponentsBuilder.path("/api/corragil/v1/{id}").buildAndExpand(entityModel.getId()).toUri();

        // Response HttpStatus 201 com URI e BODY do Objeto novo criado
        return ResponseEntity.created(uri).body(new ResponseBodyStatusCadastroDTO(entityModel));

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid DadosToUpdateCadastroDTO updateCadastro) {
        // Criando variável da entidade por busca do id deo objeto de referência
        CadastroModel entityModel = repository.getReferenceById(updateCadastro.id());

        // Chamada do método passando por parâmetro a entidade para atualizar o cadastro de acordo com o id no DB
        entityModel.updateDadosPacientes(updateCadastro);

        // Return HttpStatus 200 com dados criado no record para o Header
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyStatusCadastroDTO(entityModel));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        // Criando busca por id da variável do objeto de referência da entidade
        CadastroModel entityDelete = repository.findById(id).orElseThrow();

        // Delete direto do DB dos dados da entidade
        repository.delete(entityDelete);

        // Return HttpStatus 204
        return ResponseEntity.noContent().build();
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
