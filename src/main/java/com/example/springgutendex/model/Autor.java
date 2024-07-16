package com.example.springgutendex.model;

import com.example.springgutendex.model.dto.LivroDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Optional;

@Entity
@Table
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonAlias("name")
    private String nome;
    @JsonAlias("birth_year")
    private Integer anoNascimento;
    @JsonAlias("death_year")
    private Integer anoMorte;

    public Autor(LivroDTO dto) {
        Optional<Autor> autor = dto.autor().stream().findFirst();
        if(autor.isPresent()) {
            this.nome = autor.get().getNome();
            this.anoNascimento = autor.get().getAnoNascimento();
            this.anoMorte = autor.get().getAnoMorte();
        }
    }
    public Autor() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String toString() {
        return "{id: " + id + ", nome: " + nome + ", anoNascimento: " + anoNascimento + ", anoMorte: " + anoMorte + "}";
    }
}
