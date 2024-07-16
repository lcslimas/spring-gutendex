package com.example.springgutendex.repository;

import com.example.springgutendex.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Integer countByIdiomasContaining(String nome);
}
