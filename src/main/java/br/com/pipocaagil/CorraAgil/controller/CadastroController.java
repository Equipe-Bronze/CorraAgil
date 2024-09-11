//package br.com.pipocaagil.CorraAgil.controller;
//
//import br.com.pipocaagil.CorraAgil.DTO.DadosCadastroDTO;
//import br.com.pipocaagil.CorraAgil.DTO.DadosCadastroList;
//import br.com.pipocaagil.CorraAgil.DTO.DadosToUpdateCadastroDTO;
//import br.com.pipocaagil.CorraAgil.DTO.ResponseBodyStatusCadastroDTO;
//import br.com.pipocaagil.CorraAgil.model.CadastroModel;
//import br.com.pipocaagil.CorraAgil.repositories.CadastroRepository;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@RestController
//@RequestMapping("/api/corragil/v1")
//@RequiredArgsConstructor
//public class CadastroController {
//    private final CadastroRepository repository;
//
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Page<DadosCadastroList>> findAll(@PageableDefault(size = 10) Pageable paginacao) {
//        // Adaptando Page List de todos os cadastro
//        var pageList = repository.findAll(paginacao).map(DadosCadastroList::new);
//
//        // Return HttpStatus 200 com os dados de Page List
//        return ResponseEntity.ok(pageList);
//    }
//
//    @GetMapping(value = "/{id}")
//    @Transactional
//    public ResponseEntity<?> findById(@PathVariable Long id) {
//        // Criando busca por id da variável do objeto de referência da entidade
//        CadastroModel entityById = repository.getReferenceById(id);
//
//        // Return HttpStatus 200
//        return ResponseEntity.ok(new ResponseBodyStatusCadastroDTO(entityById));
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Transactional
//    public ResponseEntity<?> createCadastro(@RequestBody @Valid DadosCadastroDTO createCadastro,
//                                            UriComponentsBuilder uriComponentsBuilder) {
//        // Criando variável de um novo cadastro do object reference(CadastroModel), nova entidade no DB
//        CadastroModel entityModel = repository.save(new CadastroModel(createCadastro));
//
//        // Variável URI encapsulada com Location('endereço ou Header') para FrontEnd
//        var uri = uriComponentsBuilder.path("/api/corragil/v1/{id}").buildAndExpand(entityModel.getId()).toUri();
//
//        // Response HttpStatus 201 com URI e BODY do Objeto novo criado
//        return ResponseEntity.created(uri).body(new ResponseBodyStatusCadastroDTO(entityModel));
//
//    }
//
//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Transactional
//    public ResponseEntity<?> update(@RequestBody @Valid DadosToUpdateCadastroDTO updateCadastro) {
//        // Criando variável da entidade por busca do id deo objeto de referência
//        CadastroModel entityModel = repository.getReferenceById(updateCadastro.id());
//
//        // Chamada do método passando por parâmetro a entidade para atualizar o cadastro de acordo com o id no DB
//        entityModel.updateDadosPacientes(updateCadastro);
//
//        // Return HttpStatus 200 com dados criado no record para o Header
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyStatusCadastroDTO(entityModel));
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        // Criando busca por id da variável do objeto de referência da entidade
//        CadastroModel entityDelete = repository.findById(id).orElseThrow();
//
//        // Delete direto do DB dos dados da entidade
//        repository.delete(entityDelete);
//
//        // Return HttpStatus 204
//        return ResponseEntity.noContent().build();
//    }
//}
