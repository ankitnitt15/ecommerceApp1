package com.ecom.catalogue.controller;

import com.ecom.catalogue.exception.ProductNotFoundException;
import com.ecom.catalogue.model.Product;
import com.ecom.catalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.ok().body(this.service.createProduct(product));
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product){
        product.setProductId(productId);
        return ResponseEntity.ok().body(this.service.updateProduct(product));
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity getProductsByCategory(@PathVariable String category){
        try{
            return ResponseEntity.ok().body(this.service.getProductsByCategory(category));
        }
        catch(ProductNotFoundException e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @GetMapping("/products/name/{name}")
    public ResponseEntity getProductsByName(@PathVariable String name){
        try{
            return ResponseEntity.ok().body(this.service.getProductsByName(name));
        }
        catch(ProductNotFoundException e){
            return ResponseEntity.ok().body(e.getMessage());
        }

    }

    @DeleteMapping("/products/{productId}")
    public HttpStatus deleteProduct(@PathVariable Long productId){
        this.service.deleteProduct(productId);
        return HttpStatus.OK;
    }

}
