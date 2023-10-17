package com.ecom.catalogue.kafka;

import com.ecom.catalogue.model.Category;
import com.ecom.catalogue.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProducerCategory {
    private static final Logger logger = LoggerFactory.getLogger(ProducerCategory.class);

    @Autowired
    private KafkaTemplate<String, Category> kafkaTemplate;

    public void sendMessage(Category category){
        logger.info(String.format("Message sent -> %s", category.toString()));

        Message<Category> message = MessageBuilder
                .withPayload(category)
                .setHeader(KafkaHeaders.TOPIC, AppConstants.CATEGORY_TOPIC)
                .build();

        kafkaTemplate.send(message);
    }
}
