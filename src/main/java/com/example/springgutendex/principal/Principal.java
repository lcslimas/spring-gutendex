package com.example.springgutendex.principal;

import com.example.springgutendex.model.Autor;
import com.example.springgutendex.model.Livro;
import com.example.springgutendex.model.dto.LivroDTO;
import com.example.springgutendex.repository.AutorRepository;
import com.example.springgutendex.repository.LivroRepository;
import com.example.springgutendex.service.ApiService;
import com.example.springgutendex.service.LivroService;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private ApiService apiService = new ApiService();
    private Scanner scanner = new Scanner(System.in);
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    private LivroService livroService;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository, LivroService livroService) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.livroService = livroService;
    }

    public void printOptions() {
        var option = -1;
        while(option != 0) {
            var menu = """
                Seja bem-vindo a biblioteca
                \s
                1 - Pesquisar livro;
                2 - Listar livros buscados;
                3 - Listar autores buscados;
                4 - Listar autores buscados vivos em determinada data;
                5 - Quantidade de livros por idioma;
                0 - Sair da aplicação;""";
            System.out.println(menu);

            try {
                option = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                System.out.println("Valor Inválido");
                option = -1;
            }

            switch (option) {
                case 1:
                    System.out.println("Digite o nome do livro: ");
                    String consulta = scanner.next();
                    List<LivroDTO> dtos = apiService.consultaApi("?search=" + consulta.trim().replace(" ", "%20"));
                    List<Livro> livros = dtos.stream().map(Livro::new).toList();
                    livros.stream().map(Livro::toString).forEach(System.out::println);
                    livroService.saveAll(livros);
                    break;
                case 2:
                    livroRepository.findAll().stream().map(Livro::toString).forEach(System.out::println);
                    break;
                case 3:
                    autorRepository.findAll().stream().map(Autor::toString).forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Digite a data desejada: ");
                    try {
                        String ano = scanner.next();
                        Integer anoInteger = Integer.parseInt(ano);
                        autorRepository.findByAnoMorteIsNullOrAnoNascimentoIsLessThanAndAnoMorteGreaterThan(anoInteger, anoInteger).forEach(System.out::println);
                    } catch (Exception ex) {
                        System.out.println("Valor invalido");
                    }
                    break;
                case 5:
                    System.out.println("O idioma desejado: (en, pt) ");
                    String idioma = scanner.next();
                    Integer qtdLivroDesseIdioma = livroRepository.countByIdiomasContaining(idioma);
                    System.out.println("Existem: " + qtdLivroDesseIdioma + " livro(s) do idioma: " +idioma);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void saveAutor(LivroDTO dto) {
        if(dto == null) return;
        Autor autor = new Autor(dto);

        autorRepository.save(autor);
    }

}
