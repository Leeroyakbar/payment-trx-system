package com.avows.client.client;

import com.avows.client.dto.ApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class BaseClient {
    protected final RestTemplate restTemplate;

    protected BaseClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected <T> T get(String url, ParameterizedTypeReference<ApiResponseDTO<T>> typeReference) {
        log.info("GET Request to: {}", url);
        try {
            ResponseEntity<ApiResponseDTO<T>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, typeReference);
            return handleResponse(response);
        } catch (Exception e) {
            log.error("GET Error: {}", e.getMessage());
            throw new RuntimeException("External Connection Error: " + e.getMessage());
        }
    }

    protected <T> void put(String url, Object body) {
        log.info("PUT Request to: {}", url);
        try {
            HttpEntity<Object> entity = new HttpEntity<>(body);
            restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
        } catch (Exception e) {
            log.error("PUT Error: {}", e.getMessage());
            throw new RuntimeException("external connection error: " + e.getMessage());
        }
    }

    private <T> T handleResponse(ResponseEntity<ApiResponseDTO<T>> response) {
        if (response.getBody() != null && response.getBody().getSuccess()) {
            return response.getBody().getData();
        }
        throw new RuntimeException("API Error: " +
                (response.getBody() != null ? response.getBody().getMessages() : "No Response Body"));
    }
}
