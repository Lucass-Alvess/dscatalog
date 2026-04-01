package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.Factory;
import com.devsuperior.dscatalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private Long exintingId;
    private Long notExistingId;
    private Long countTotalProduct;

    @BeforeEach
    void setUp() throws Exception{
        exintingId = 1L;
        notExistingId = 1000L;
        countTotalProduct = 25L;
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {

        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProduct + 1, product.getId());

    }

    @Test
    public void deleteShoulDeleteObjectWheIdExists() {

        repository.deleteById(exintingId);

        Optional<Product> result = repository.findById(exintingId);
        Assertions.assertFalse(result.isPresent());

    }

    @Test
    public void findByIdShouldReturnNonEmptyOpitionalWhenIdExsts() {

        Optional<Product> result = repository.findById(exintingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOpitionalWhenIdDoesNotExist() {

        Optional<Product> result = repository.findById(notExistingId);
        Assertions.assertTrue(result.isEmpty());
    }



}
