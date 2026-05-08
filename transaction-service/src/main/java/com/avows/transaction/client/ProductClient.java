package com.avows.transaction.client;

import com.avows.client.client.BaseClient;
import com.avows.client.dto.ApiResponseDTO;
import com.avows.transaction.dto.ProductDTO;
import com.avows.transaction.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class ProductClient extends BaseClient {

    @Value("${product.service.url}")
    private String baseUrl;

    protected ProductClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public ProductDTO getProductById(UUID productId) {
        String url = baseUrl + "/" + productId;

        ParameterizedTypeReference<ApiResponseDTO<ProductDTO>> typeRef =
                new ParameterizedTypeReference<>() {};

        return get(url, typeRef);
    }

    public void reduceStock(UUID productId, Integer quantity) {
        String url = baseUrl + "/reduce";

        ProductRequest requestBody = new ProductRequest(productId, quantity);

        put(url, requestBody);
    }

}
