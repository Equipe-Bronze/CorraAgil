package br.com.pipocaagil.CorraAgil.Cadastro;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class CadastroController {

    private CadastroService cadastroService;

    @GetMapping("/todos")
    public List<CadastroModel> getAllCadastroModel(){
        return cadastroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<CadastroModel> buscar (@PathVariable Long id){
        return cadastroService.buscar(id);
    }
    @PostMapping("/cadastro")
    public CadastroModel createCadastroModel(@RequestBody CadastroModel cadastroModel){
        return cadastroService.salvar(cadastroModel);
    }
    @PutMapping("/{id}")
    public CadastroModel atualizar (@PathVariable Long id, @RequestBody CadastroModel cadastroModel){
        return cadastroService.atualizar(id,cadastroModel);
    }
    @DeleteMapping("/{id}")
    public void deletarCadastroModel(@PathVariable Long id){
        cadastroService.deletar(id);
    }
    @PostMapping("/login")
    public String login (@RequestBody CadastroModel cadastroModel){
        Optional<CadastroModel> usuarioAutenticado = cadastroService.autenticar(cadastroModel.getEmail(),cadastroModel.getSenha());
        if (usuarioAutenticado.isPresent()){
            return "Login bem-sucedido!";
        }else {
            return "Falha no login. Verifique suas credenciais.";
        }
    }
}
