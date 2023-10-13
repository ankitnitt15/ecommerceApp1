package com.ecom.catalogue.controller;

import com.ecom.catalogue.model.ProductES;
import com.ecom.catalogue.repository.ElasticSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ElasticSearchController {

    @Autowired
    private ElasticSearchRepository repository;

    @PostMapping("/createDocument")
    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody ProductES product) throws IOException {
        String response = repository.createOrUpdateDocument(product);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getDocument")
    public ResponseEntity<Object> getDocument(@RequestParam String id) throws IOException {
        ProductES product = repository.getDocumentbyId(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchDocument(@RequestParam String text) throws IOException {
        List<ProductES> products = repository.findProducts(text);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/deleteDocument")
    public ResponseEntity<Object> deleteDocument(@RequestParam String id) throws IOException {
        String response = repository.deleteDocumentById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
