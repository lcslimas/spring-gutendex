# Aplicação biblioteca

### Aplicação em CLI de uma biblioteca usando a API Gutendex com persistência e busca em um banco postgreSQL 

O sistema usa Spring Boot, faz consultas na API Gutendex e persiste os resultados em um banco PostgreSQL.
Possui algumas funcionalidades como:
- Pesquisar livro;
- Listar livros buscados;
- Listar autores buscados;
- Listar autores buscados vivos em determinada data;
- Quantidade de livros por idioma;

Foi utilizado JPARepository com derivedQueries 
