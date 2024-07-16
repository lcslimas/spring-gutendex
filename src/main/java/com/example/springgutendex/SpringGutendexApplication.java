package com.example.springgutendex;

import com.example.springgutendex.principal.Principal;
import com.example.springgutendex.repository.AutorRepository;
import com.example.springgutendex.repository.LivroRepository;
import com.example.springgutendex.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringGutendexApplication implements CommandLineRunner {
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    AutorRepository autorRepository;
    @Autowired
    LivroService livroService;

    public static void main(String[] args) {
        SpringApplication.run(SpringGutendexApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(livroRepository, autorRepository, livroService);
        principal.printOptions();
    }
}
