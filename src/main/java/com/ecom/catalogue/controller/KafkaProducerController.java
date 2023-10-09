package com.ecom.catalogue.controller;

import com.ecom.catalogue.kafka.Producer;
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
    private Producer producer;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody Product product){
        producer.sendMessage(product);
        return ResponseEntity.ok("Message sent to kafka topic");
    }
}
