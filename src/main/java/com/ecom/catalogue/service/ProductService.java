package com.ecom.catalogue.service;

import com.ecom.catalogue.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product) throws IOException;

    Product updateProduct(Product product) throws IOException;

    Product processProductFeed(Product product) throws IOException;

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByName(String name);

    void deleteProduct(Long productId);
}
