package br.com.pipocaagil.CorraAgil.Cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @GetMapping("/todos")
    public List<CadastroModel> getAllCadastroModel() {
        return cadastroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroModel> buscar(@PathVariable Long id) {
        return cadastroService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CadastroModel> createCadastroModel(@RequestBody CadastroModel cadastroModel) {
        CadastroModel savedCadastro = cadastroService.salvar(cadastroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCadastro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadastroModel> atualizar(@PathVariable Long id, @RequestBody CadastroModel cadastroModel) {
        try {
            CadastroModel updatedCadastro = cadastroService.atualizar(id, cadastroModel);
            return ResponseEntity.ok(updatedCadastro);
        } catch (CadastroNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCadastroModel(@PathVariable Long id) {
        cadastroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CadastroModel cadastroModel) {
        Optional<CadastroModel> usuarioAutenticado = cadastroService.autenticar(cadastroModel.getEmail(), cadastroModel.getSenha());
        if (usuarioAutenticado.isPresent()) {
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha no login. Verifique suas credenciais.");
        }
    }
}
