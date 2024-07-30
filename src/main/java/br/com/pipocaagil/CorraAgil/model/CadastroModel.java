package br.com.pipocaagil.CorraAgil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "corragil")
public class CadastroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nomecompleto")
    private String nomecompleto;

    @Column(unique = true)
    @Email
    private String email;

    @Column(name = "senha")
    @Length(min = 8)
    private String senha;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CadastroModel that = (CadastroModel) o;
        return id.equals(that.id) && nomecompleto.equals(that.nomecompleto) && email.equals(that.email) && senha.equals(that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomecompleto, email, senha);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomecompleto() {
        return nomecompleto;
    }

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
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
