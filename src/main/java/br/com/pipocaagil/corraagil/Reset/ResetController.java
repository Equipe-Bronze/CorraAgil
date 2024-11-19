package br.com.pipocaagil.corraagil.Reset;

import br.com.pipocaagil.corraagil.Cadastro.CadastroModel;
import br.com.pipocaagil.corraagil.Cadastro.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/")
public class ResetController {
    @Autowired
    private ResetService resetService;
    @Autowired
    private CadastroRepository cadastroRepository;
    @Autowired
    private ResetTokenRepository resetTokenRepository;

    private String gerarToken() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000)); // Gera um token de 4 dígitos
    }

    @PostMapping("/resetSenha")
    public ResponseEntity resetSenha(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        CadastroModel cadastroModel = cadastroRepository.findByEmail(email);

        if (cadastroModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        // Verifica se já existe um token associado ao usuário
        ResetToken existingToken = resetService.findTokenByCadastro(cadastroModel);

        if (existingToken != null) {
            // Se o token existir e estiver expirado, deleta do banco
            if (!existingToken.isTokenValido()) {
                resetService.deletarToken(existingToken);
            }
        }

        // Gera um novo token
        String newToken = gerarToken(); // Gera um token de 4 dígitos
        resetService.createResetTokenForCadastro(cadastroModel, newToken);
        resetService.ResetTokenEmail(email, newToken);

        return ResponseEntity.ok("Token de reset de senha enviado para o e-mail");
    }

    @PostMapping("/saveSenha")
    public ResponseEntity<String> saveSenha(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String senha = request.get("senha");
        ResetToken resetToken = resetTokenRepository.findByToken(token);
        if (resetToken == null || !resetToken.isTokenValido()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
        }
        CadastroModel user = resetToken.getCadastroModel();
        user.setSenha(senha); // Considere criptografar a senha aqui
        cadastroRepository.save(user);
        resetTokenRepository.delete(resetToken); // Remove o token após o uso
        return ResponseEntity.ok("Senha alterada com sucesso");
    }

}