package com.avows.product.service;

import com.avows.product.dto.ProductDTO;

import java.util.UUID;

public interface ProductService {

    ProductDTO getByProductId(UUID productId);

    Integer getStock(UUID productId);

    void reduceStock(UUID productId, Integer quantity);

}
