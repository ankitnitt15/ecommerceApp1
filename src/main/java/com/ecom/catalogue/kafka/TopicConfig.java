package com.ecom.catalogue.kafka;

import com.ecom.catalogue.utils.AppConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic productmetaTopic(){
        return TopicBuilder.name(AppConstants.PRODUCT_TOPIC).build();
    }

    @Bean
    public NewTopic categorymetaTopic(){
        return TopicBuilder.name(AppConstants.CATEGORY_TOPIC).build();
    }
}
