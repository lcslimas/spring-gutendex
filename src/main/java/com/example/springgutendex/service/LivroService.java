package com.example.springgutendex.service;

import com.example.springgutendex.model.Livro;
import com.example.springgutendex.repository.AutorRepository;
import com.example.springgutendex.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public List<Livro> saveAll(List<Livro> livros) {
        livros = livros.stream().peek(livro -> {
            autorRepository.save(livro.getAutor());
        }).toList();

        return livroRepository.saveAll(livros);
    }
}
