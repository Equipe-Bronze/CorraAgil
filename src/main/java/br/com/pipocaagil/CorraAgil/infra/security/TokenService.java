package br.com.pipocaagil.CorraAgil.infra.security;

import br.com.pipocaagil.CorraAgil.model.CadastroModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(CadastroModel userGenerateToken) {
        try {
            // Algoritmo de criptografia para utilizar
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Criação do Token
            String token = JWT.create()
                    .withIssuer("CorraAgil")
                    .withSubject(userGenerateToken.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao autenticar!");
        }
    }

    public String validateToken(String validateToken) {
        try {
            // Algoritmo de criptografia para utilizar
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Realiza a verificação do token "generateToken()" e valida
            return JWT.require(algorithm)
                    .withIssuer("CorraAgil")
                    .build()
                    .verify(validateToken)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
