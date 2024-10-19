package br.com.pipocaagil.CorraAgil.Reset;

import br.com.pipocaagil.CorraAgil.Cadastro.CadastroModel;
import br.com.pipocaagil.CorraAgil.Cadastro.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class ResetController {
    @Autowired
    private ResetService resetService;
    @Autowired
    private CadastroRepository cadastroRepository;
    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @PostMapping("/resetSenha")
    public ResponseEntity<String> resetSenha(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        CadastroModel cadastroModel = cadastroRepository.findByEmail(email);
        if (cadastroModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        String token = UUID.randomUUID().toString();
        resetService.createResetTokenForCadastro(cadastroModel, token);
        resetService.ResetTokenEmail(email, token);
        return ResponseEntity.ok("Link de reset de senha enviado para o e-mail");
    }

    @PostMapping("/saveSenha")
    public ResponseEntity<String> saveSenha(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String senha = request.get("senha");
        ResetToken resetToken = resetTokenRepository.findByToken(token);
        if (resetToken == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token inválido");
        }
        CadastroModel user = resetToken.getCadastroModel();
        user.setSenha(senha); // Considere criptografar a senha aqui
        cadastroRepository.save(user);
        return ResponseEntity.ok("Senha alterada com sucesso");
    }
}