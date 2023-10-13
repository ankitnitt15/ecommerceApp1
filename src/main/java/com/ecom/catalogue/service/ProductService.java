package com.ecom.catalogue.service;

import com.ecom.catalogue.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product updateProduct(Product product);

    Product processProductFeed(Product product);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByName(String name);

    void deleteProduct(Long productId);
}
