package br.com.pipocaagil.corraagil.Reset;

import br.com.pipocaagil.corraagil.Cadastro.CadastroModel;
import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;
@Entity
public class ResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne(targetEntity = CadastroModel.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "cadastro_id")
    private CadastroModel cadastroModel;
    private Date expiryDate;

    public ResetToken() {}

    public ResetToken(String token, CadastroModel cadastroModel) {
        this.token = token;
        this.cadastroModel = cadastroModel;
        this.expiryDate = calculateExpiryDate(2 * 10); // Exemplo de 20 minutos de validade
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return calendar.getTime();
    }
    public boolean isTokenValido() {
        return new Date().before(expiryDate); // Verifica se a data atual é antes da data de expiração
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CadastroModel getCadastroModel() {
        return cadastroModel;
    }

    public void setCadastroModel(CadastroModel cadastroModel) {
        this.cadastroModel = cadastroModel;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
