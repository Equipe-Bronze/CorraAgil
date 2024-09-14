package br.com.pipocaagil.CorraAgil.controller;

import br.com.pipocaagil.CorraAgil.DTO.DadosCadastroDTO;
import br.com.pipocaagil.CorraAgil.DTO.DadosCadastroListDTO;
import br.com.pipocaagil.CorraAgil.DTO.DadosToUpdateCadastroDTO;
import br.com.pipocaagil.CorraAgil.DTO.ResponseBodyStatusCadastroDTO;
import br.com.pipocaagil.CorraAgil.domain.UsuarioModel;
import br.com.pipocaagil.CorraAgil.DTO.DadosLoginRequestDTO;
import br.com.pipocaagil.CorraAgil.infra.DadosTokenJWTDTO;
import br.com.pipocaagil.CorraAgil.infra.security.TokenService;
import br.com.pipocaagil.CorraAgil.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/corragil/v1")
public class AutenticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<DadosCadastroListDTO>> findAll(@PageableDefault(size = 10) Pageable paginacao) {
        // Adaptando Page List de todos os cadastro
        var pageList = repository.findAll(paginacao).map(DadosCadastroListDTO::new);

        // Return HttpStatus 200 com os dados de Page List
        return ResponseEntity.ok(pageList);
    }

    @GetMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> findById(@PathVariable Long id) {
        // Criando busca por id da variável do objeto de referência da entidade
        UsuarioModel entityById = repository.getReferenceById(id);

        // Return HttpStatus 200
        return ResponseEntity.ok(new ResponseBodyStatusCadastroDTO(entityById));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> createCadastro(@RequestBody @Valid DadosCadastroDTO createCadastro,
                                            UriComponentsBuilder uriComponentsBuilder) {
        // Criando variável de um novo cadastro do object reference(UsuarioLogin), nova entidade no DB
        UsuarioModel entityModel = repository.save(new UsuarioModel(createCadastro));

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
        UsuarioModel entityModel = repository.getReferenceById(updateCadastro.id());

        // Chamada do método passando por parâmetro a entidade para atualizar o cadastro de acordo com o id no DB
        entityModel.updateDadosPacientes(updateCadastro);

        // Return HttpStatus 200 com dados criado no record para o Header
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyStatusCadastroDTO(entityModel));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        // Criando busca por id da variável do objeto de referência da entidade
        UsuarioModel entityDelete = repository.findById(id).orElseThrow();

        // Delete direto do DB dos dados da entidade
        repository.delete(entityDelete);

        // Return HttpStatus 204
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody @Valid DadosLoginRequestDTO dadosLoginRequestDTO) {
        // Variável 'token' do login e senha do usuario para classe do Spring
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosLoginRequestDTO.login(), dadosLoginRequestDTO.senha());

        // Conversão da variável 'token' para um método de Objeto reference para retornar o usuário autenticado no sistema com o token
        var authentication = manager.authenticate(authenticationToken);

        // Criando JSON de token para return
        var tokenJWT = tokenService.generateToken((UsuarioModel) authentication.getPrincipal());

        // Return do Token com validação de 2h para usuário Logado e autenticado acessar app
        return ResponseEntity.ok(new DadosTokenJWTDTO(tokenJWT));
    }

    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody @Valid DadosCadastroDTO dadosRegisterRequestDTO) {

        // Verificação se o email (login) está cadastrado
        Optional<UserDetails> entityModel = Optional.ofNullable(repository.findByLogin(dadosRegisterRequestDTO.login()));
        System.out.println(entityModel);

        if (entityModel.isEmpty()) {
            System.out.println(entityModel);
            // Novo Usuário
            UsuarioModel newEntity = new UsuarioModel();

            // Hash da senha usando BCryptPasswordEncoder
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(dadosRegisterRequestDTO.senha());

            newEntity.setSenha(encodedPassword);
            newEntity.setLogin(dadosRegisterRequestDTO.login()); // Presumindo que o login é o email
            newEntity.setNomecompleto(dadosRegisterRequestDTO.nomecompleto());

            // Salvar novo usuário no banco de dados
            repository.save(newEntity);

            // Opcionalmente, gerar um token JWT após o registro, se necessário
            String tokenJWT = tokenService.generateToken(newEntity);

            // Retornar o token JWT
            return ResponseEntity.ok(new DadosTokenJWTDTO(tokenJWT));
        } else {
            // Retornar uma resposta indicando que o usuário já está registrado
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Usuário já está registrado com este login.");
        }
    }
}
