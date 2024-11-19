package br.com.pipocaagil.corraagil.Reset;

import br.com.pipocaagil.corraagil.Cadastro.CadastroModel;
import br.com.pipocaagil.corraagil.Cadastro.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ResetService {
    @Autowired
    private CadastroRepository cadastroRepository;
    @Autowired
    private ResetTokenRepository resetTokenRepository;
    @Autowired
    private JavaMailSender mailSender;

    // Método para gerar um token de 4 dígitos
    private String gerarToken() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000)); // Gera um token de 4 dígitos

    }

    public void createResetTokenForCadastro(CadastroModel cadastroModel, String token) {
        ResetToken myToken = new ResetToken(token, cadastroModel);
        resetTokenRepository.save(myToken);
    }

    public void createResetTokenForCadastro(CadastroModel cadastroModel) {
        String token = gerarToken();
        ResetToken myToken = new ResetToken(token, cadastroModel);
        resetTokenRepository.save(myToken);
    }

    public void ResetTokenEmail(String email, String token) {
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(email);
        emailMessage.setSubject("Reset de Senha");
        emailMessage.setText("Seu token de reset de senha é: " + token +
                "\n\nEste token é válido por 20 minutos.");
        mailSender.send(emailMessage);
    }
}
