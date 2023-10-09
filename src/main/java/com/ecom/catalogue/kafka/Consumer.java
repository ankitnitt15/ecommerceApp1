package com.ecom.catalogue.kafka;

import com.ecom.catalogue.model.Product;
import com.ecom.catalogue.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private ProductService productService;

    @KafkaListener(topics = AppConstants.TOPIC_NAME,
                    groupId = AppConstants.GROUP_ID)
    public void consume(Product product){
        logger.info(String.format("Message received -> %s", product.toString()));
        this.productService.createProduct(product);
    }
}
