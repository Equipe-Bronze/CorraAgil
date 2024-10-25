package br.com.pipocaagil.corraagil.Reset;

import br.com.pipocaagil.corraagil.Cadastro.CadastroModel;
import br.com.pipocaagil.corraagil.Cadastro.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ResetService {
    @Autowired
    private CadastroRepository cadastroRepository;
    @Autowired
    private ResetTokenRepository resetTokenRepository;
    @Autowired
    private JavaMailSender mailSender;


    public void createResetTokenForCadastro(CadastroModel cadastroModel, String token){
        ResetToken myToken = new ResetToken(token,cadastroModel);
        resetTokenRepository.save(myToken);

    }

    public void ResetTokenEmail(String email, String token){
        String url = "http://localhost:8080/resetPassword?token=" + token;
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(email);
        emailMessage.setSubject("Reset de Senha");
        emailMessage.setText("Para resetar sua senha, clique no link abaixo:\n" + url);
        mailSender.send(emailMessage);
    }
}
