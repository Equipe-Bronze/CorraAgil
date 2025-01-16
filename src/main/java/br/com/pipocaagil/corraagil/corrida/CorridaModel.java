package br.com.pipocaagil.corraagil.corrida;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class CorridaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Duration duracao;
    private boolean pausada;

    public CorridaModel(Long id, LocalDateTime inicio, LocalDateTime fim, Duration duracao, boolean pausada) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.duracao = duracao;
        this.pausada = pausada;
    }

    public CorridaModel() {

    }

    @Override
    public String toString() {
        return "CorridaModel{" +
                "id=" + id +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", duracao=" + duracao +
                ", pausada=" + pausada +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public boolean isPausada() {
        return pausada;
    }

    public void setPausada(boolean pausada) {
        this.pausada = pausada;
    }
}
