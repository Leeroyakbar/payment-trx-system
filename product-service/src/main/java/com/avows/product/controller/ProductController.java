package com.avows.product.controller;

import com.avows.product.dto.ApiResponseDTO;
import com.avows.product.dto.ProductDTO;
import com.avows.product.dto.ProductRequest;
import com.avows.product.enums.ReturnMessage;
import com.avows.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;


    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponseDTO<ProductDTO>> getProduct(@PathVariable UUID productId){
        return ResponseEntity.ok(ApiResponseDTO.ok(productService.getByProductId(productId), ReturnMessage.SUCCESS_FETCH_DATA.getMessage()));
    }

    @GetMapping("/{productId}/stock")
    public ResponseEntity<ApiResponseDTO<Integer>> getStock(@PathVariable UUID productId) {
        return ResponseEntity.ok(ApiResponseDTO.ok(productService.getStock(productId), ReturnMessage.SUCCESS_FETCH_DATA.getMessage()));
    }

    @PutMapping("/reduce")
    public ResponseEntity<ApiResponseDTO<String>> reduceStock(@RequestBody ProductRequest request) {
        productService.reduceStock(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(ApiResponseDTO.ok(ReturnMessage.SUCCESS_UPDATE_DATA.getMessage(), ReturnMessage.STOCK_REDUCED_SUCCESS.getMessage()));
    }
}
