package com.example.springgutendex.model;

import com.example.springgutendex.model.dto.LivroDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @OneToOne
    private Autor autor;
    private String idiomas;
    private int numeroDownloads;

    public Livro(LivroDTO dto) {
        this.titulo = limitSentence(dto.titulo());
        this.autor = new Autor(dto);
        this.idiomas = String.join(", ", dto.idiomas());
        this.numeroDownloads = dto.numeroDownloads();
    }

    private String limitSentence(String sentence) {
        if (sentence.length() > 255) {
            return sentence.substring(0, 255);
        }
        return sentence;
    }

    public Livro() {}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(int numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Long getId() {
        return id;
    }
    public String toString() {
        return "{id: " + id + ", " +
                "titulo: " + titulo + ", " +
                "autor: " + autor + ", " +
                "idiomas: [" + String.join(", ", idiomas) + "], " +
                "numeroDownloads: " + numeroDownloads + "}";
    }
}
