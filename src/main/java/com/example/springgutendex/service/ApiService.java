package com.example.springgutendex.service;

import com.example.springgutendex.model.dto.LivroDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static java.time.Duration.ofMinutes;

public class ApiService {
    public List<LivroDTO> consultaApi(String consulta) {
        String API = "https://gutendex.com/books/" + consulta;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API))
                .timeout(ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(body -> Objects.requireNonNull(this.parseJson(body)).get("results"))
                    .thenApply(this::jsonNodeToLivros).exceptionally(ex -> new ArrayList<>()).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    private List<LivroDTO> jsonNodeToLivros(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(node, new TypeReference<List<LivroDTO>>() {});
    }
    private JsonNode parseJson(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
