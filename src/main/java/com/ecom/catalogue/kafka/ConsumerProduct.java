package com.ecom.catalogue.kafka;

import com.ecom.catalogue.model.Product;
import com.ecom.catalogue.service.ProductService;
import com.ecom.catalogue.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerProduct {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerProduct.class);

    @Autowired
    private ProductService productService;

    @KafkaListener(topics = AppConstants.PRODUCT_TOPIC,
                    groupId = AppConstants.PROD_GROUP_ID)
    public void consume(Product product) throws IOException {
        logger.info(String.format("Message received -> %s", product.toString()));
        this.productService.createProduct(product);
    }
}
