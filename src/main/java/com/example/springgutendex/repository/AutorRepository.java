package com.example.springgutendex.repository;

import com.example.springgutendex.model.Autor;
import com.example.springgutendex.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long>{
//    @Query("SELECT a from Autor a where a.anoMorte is null or (:ano between a.anoNascimento and a.anoMorte)" )
    List<Autor> findByAnoMorteIsNullOrAnoNascimentoIsLessThanAndAnoMorteGreaterThan(Integer ano, Integer morte);
}
