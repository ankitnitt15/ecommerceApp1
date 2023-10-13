package com.ecom.catalogue.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElasticSearchRepository {

    @Autowired
    private ElasticsearchClient elasticsearchClient;


}
