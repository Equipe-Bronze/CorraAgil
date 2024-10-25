package br.com.pipocaagil.corraagil.Cadastro;

import jakarta.persistence.*;

@Entity
public class CadastroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeCompleto;
    private String email;
    private String senha;

    public CadastroModel(Long id, String nomeCompleto, String email, String senha) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
    }
    public CadastroModel() {}


    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
