package com.ecom.catalogue.repository;

import com.ecom.catalogue.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByProductName(String productName);
}
