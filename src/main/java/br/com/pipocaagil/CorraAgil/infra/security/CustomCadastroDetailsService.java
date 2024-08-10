package br.com.pipocaagil.CorraAgil.infra.security;

import br.com.pipocaagil.CorraAgil.model.CadastroModel;
import br.com.pipocaagil.CorraAgil.repositories.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomCadastroDetailsService implements UserDetailsService {
    @Autowired
    private CadastroRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar o usuário
        CadastroModel userEntity = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getSenha(), new ArrayList<>());
    }
}
