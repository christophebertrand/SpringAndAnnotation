package com.example.microservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexService {

    private final RestTemplate restTemplate;

    private final String base = "http://localhost:8090";
    public IndexService(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    public void add(User u) {
        String url = base + "/add";
        HttpEntity<User> entity = new HttpEntity<>(u);
        restTemplate.postForEntity(url, entity, HttpEntity.class);
    }

    public List<Long> findLike(String s) {
        String url = base + String.format("/user/like?name=%s", s);
        System.out.println(url);
        ResponseEntity<List<Long>> response = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Long>>() {
                });
        System.out.println(response);
        System.out.println(response.getBody().toString());
        return response.getBody();
//        return response.getBody().stream().map(b -> b.id).collect(Collectors.toList());
    }

}

