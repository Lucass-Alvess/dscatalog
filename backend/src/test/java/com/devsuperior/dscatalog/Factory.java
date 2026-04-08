package com.devsuperior.dscatalog;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct() {
        Product product = new Product(1L, "Phone", "Good Phone", 800.00, "https://test/teste.png", Instant.parse("2026-01-20T03:00:00Z"));
        product.getCategories().add(crateCategory());
        return product;
    }

    public static ProductDTO createProductDTO() {
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }

    public static Category crateCategory() {
       return new Category(2L, "Electronics");
    }
}
