package com.example.springgutendex.model.dto;

import com.example.springgutendex.model.Autor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ReferenceType;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
        @JsonAlias("title")
        String titulo,
        @JsonAlias("authors")
        List<Autor> autor,
        @JsonAlias("languages")
        List<String> idiomas,
        @JsonAlias("download_count")
        int numeroDownloads) {

        public LivroDTO Livro(JsonNode json) {
                ObjectMapper mapper = new ObjectMapper();
               return mapper.convertValue(json, LivroDTO.class);
        }
}
