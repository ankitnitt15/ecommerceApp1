package com.ecom.catalogue.controller;

import com.ecom.catalogue.kafka.ProducerProduct;
import com.ecom.catalogue.kafka.ProducerCategory;
import com.ecom.catalogue.model.Category;
import com.ecom.catalogue.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaProducerController {

    @Autowired
    private ProducerProduct producerProduct;

    @Autowired
    private ProducerCategory producerCategory;

    @PostMapping("/publish/product")
    public ResponseEntity<String> publishProduct(@RequestBody Product product){
        producerProduct.sendMessage(product);
        return ResponseEntity.ok("Message sent to kafka topic");
    }

    @PostMapping("/publish/category")
    public ResponseEntity<String> publishCategory(@RequestBody Category category){
        producerCategory.sendMessage(category);
        return ResponseEntity.ok("Message sent to kafka topic");
    }
}
