package com.ecom.catalogue.controller;

import com.ecom.catalogue.exception.CategoryNotFoundException;
import com.ecom.catalogue.exception.ProductNotFoundException;
import com.ecom.catalogue.model.Product;
import com.ecom.catalogue.repository.CategoryRepository;
import com.ecom.catalogue.repository.ElasticSearchRepository;
import com.ecom.catalogue.service.CategoryService;
import com.ecom.catalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ElasticSearchRepository elasticSearchRepository;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws IOException {
        return ResponseEntity.ok().body(this.service.createProduct(product));
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) throws IOException {
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

    @GetMapping("/search/product/{text}")
    public ResponseEntity getProductsByName(@PathVariable String text) throws IOException{
        try{
            return ResponseEntity.ok().body(this.elasticSearchRepository.findProducts(text));
        }
        catch(ProductNotFoundException e){
            try{
                return ResponseEntity.ok().body(this.service.getProductsByName(text));
            }
            catch(ProductNotFoundException ex){
                return ResponseEntity.ok().body(ex.getMessage());
            }
        }
    }

    @DeleteMapping("/products/{productId}")
    public HttpStatus deleteProduct(@PathVariable Long productId){
        this.service.deleteProduct(productId);
        return HttpStatus.OK;
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity getProductsById(@PathVariable String id) throws IOException{
        try{
            return ResponseEntity.ok().body(this.elasticSearchRepository.getDocumentbyId(id));
        }
        catch(ProductNotFoundException e){
            try{
                return ResponseEntity.ok().body(this.service.getProductsByCategory(id));
            }
            catch(ProductNotFoundException ex){
                return ResponseEntity.ok().body(ex.getMessage());
            }

        }
    }

    @GetMapping("/search/text/{text}")
    public ResponseEntity getProductsByText(@PathVariable String name){
        try{
            return ResponseEntity.ok().body(this.service.getProductsByName(name));
        }
        catch(ProductNotFoundException e){
            return ResponseEntity.ok().body(e.getMessage());
        }

    }

    @GetMapping("/search/category/{id}")
    public ResponseEntity getCategoryById(@PathVariable String id){
        try{
            return ResponseEntity.ok().body(this.categoryService.getCategory(id));
        }
        catch(CategoryNotFoundException e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @GetMapping("/search/categories")
    public ResponseEntity getCategories(){
        try{
            return ResponseEntity.ok().body(this.categoryService.getAllCategories());
        }
        catch(CategoryNotFoundException e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

}
