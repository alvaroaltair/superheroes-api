package com.superheroes.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@RestController
public class HeroController {
    public static final String POSTS_API_URL = "https://akabab.github.io/superhero-api/api/all.json";
    private final WebClient client;

    public HeroController(WebClient.Builder builder) {
        this.client = builder.baseUrl(POSTS_API_URL).build();
    }

    @GetMapping("/")
    public List<Map<String, Object>> all() throws JsonProcessingException {
        String data = client.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonNode root = new ObjectMapper().readTree(data);
        int nrOfHeroes = root.size();

        List<Map<String, Object>> response = new ArrayList<>();

        for (int i = 0; i < nrOfHeroes; i++) {
            response.add(Map.of("id", root.get(i).get("id"),
                    "name", root.get(i).get("name")));
        }

        return response;
    }

    @GetMapping("/powerstat/{powerstat}")
    public List<Map<String, Object>> stat(@PathVariable String powerstat) throws JsonProcessingException {
        String data = client.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonNode root = new ObjectMapper().readTree(data);
        int nrOfHeroes = root.size();

        // check if entered powerstat is valid
        List<String> existentPowerstats = new ArrayList<>();
        Iterator<String> powerstats = root.get(0).get("powerstats").fieldNames();
        powerstats.forEachRemaining(existentPowerstats::add);

        if (!existentPowerstats.contains(powerstat)) {
            return List.of(Map.of("error", "no such powerstat"));
        }

        List<Map<String, Object>> response = new ArrayList<>();

        for (int i = 0; i < nrOfHeroes; i++) {
            response.add(Map.of("id", root.get(i).get("id"),
                    "name", root.get(i).get("name"),
                    powerstat, root.get(i).get("powerstats").get(powerstat)));
        }

        return response.stream()
                .sorted(Comparator.comparing(m -> ((JsonNode) m.get(powerstat)).asInt(), Comparator.reverseOrder()))
                .limit(5)
                .toList();
    }
}