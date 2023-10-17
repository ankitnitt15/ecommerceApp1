package com.ecom.catalogue.kafka;

import com.ecom.catalogue.model.Category;
import com.ecom.catalogue.service.CategoryService;
import com.ecom.catalogue.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerCategory {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerCategory.class);

    @Autowired
    private CategoryService categoryService;

    @KafkaListener(topics = AppConstants.CATEGORY_TOPIC,
            groupId = AppConstants.CAT_GROUP_ID)
    public void consume(Category category) {
        logger.info(String.format("Message received -> %s", category.toString()));
        this.categoryService.createCategory(category);
    }
}
