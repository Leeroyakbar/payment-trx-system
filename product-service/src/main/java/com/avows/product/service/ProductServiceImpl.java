package com.avows.product.service;


import com.avows.product.dto.ProductDTO;
import com.avows.product.entity.Product;
import com.avows.product.enums.ReturnMessage;
import com.avows.product.exception.NotFoundException;
import com.avows.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDTO getByProductId(UUID productId) {
        return productRepository.findById(productId)
                .map(product -> new ProductDTO(
                        product.getProductId(),
                        product.getProductName(),
                        product.getStock(),
                        product.getPrice()
                ))
                .orElseThrow(() -> new NotFoundException(ReturnMessage.PRODUCT_NOT_FOUND.getMessage()));
    }

    @Override
    public Integer getStock(UUID productId) {
        return Optional.ofNullable(productRepository.getStockByProductId(productId))
                .orElseThrow(() -> new NotFoundException(ReturnMessage.PRODUCT_NOT_FOUND.getMessage()));
    }

    @Transactional
    @Override
    public void reduceStock(UUID id, Integer qty) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ReturnMessage.PRODUCT_NOT_FOUND.getMessage()));

        if (product.getStock() < qty) {
            throw new RuntimeException(ReturnMessage.INSUFFICIENT_STOCK.getMessage());
        }

        product.setStock(product.getStock() - qty);
        productRepository.save(product);
    }
}
