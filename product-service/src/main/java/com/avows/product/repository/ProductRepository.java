package com.avows.product.repository;

import com.avows.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT p.stock FROM MST_PRODUCTS p WHERE p.PRODUCT_ID = ?1 ", nativeQuery = true)
    Integer getStockByProductId(UUID productId);
}
