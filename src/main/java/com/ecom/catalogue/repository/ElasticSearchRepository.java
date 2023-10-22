package com.ecom.catalogue.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.ecom.catalogue.exception.ProductNotFoundException;
import com.ecom.catalogue.model.Product;
import com.ecom.catalogue.model.ProductES;
import com.ecom.catalogue.service.InventoryGrpcClient;
import com.ecom.catalogue.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ElasticSearchRepository {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private InventoryGrpcClient inventoryGrpcClient;

    public String createOrUpdateDocument(ProductES product) throws IOException {

        IndexResponse response = elasticsearchClient.index(i -> i.index(AppConstants.ES_INDEX)
                .id(product.getProductId())
                .document(product));

        if(response.result().name().equals("Created")){
            return new StringBuilder("Document has been successfully created.").toString();
        }else if(response.result().name().equals("Updated")){
            return new StringBuilder("Document has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }

    public ProductES getDocumentbyId(String id) throws IOException {

        GetResponse<ProductES> response = elasticsearchClient.get(i -> i.index(AppConstants.ES_INDEX)
                .id(id),
                ProductES.class);

        if(response.found()){
            return response.source();
        }
        else{
            throw new ProductNotFoundException("Document with id "+id+" not found in Elasticsearch index");
        }
    }

    public String deleteDocumentById(String id) throws IOException {
        DeleteRequest request = DeleteRequest.of(d -> d.index(AppConstants.ES_INDEX)
                .id(id));

        DeleteResponse response = elasticsearchClient.delete(request);

        if(response != null && !response.result().name().equals("NotFound")){
            return new StringBuilder("Document with id "+id+" deleted").toString();
        }
        else{
            throw new ProductNotFoundException("Document with id \"+id+\" not found in Elasticsearch index");
        }
    }

    public List<ProductES> findProducts(String text) throws IOException {
        List<ProductES> products = new ArrayList<>();
        Query byName = MatchQuery.of(m->m.field("productName")
                .query(text))
                ._toQuery();

        Query byCategory = MatchQuery.of(m->m.field("category")
                        .query(text))
                ._toQuery();

        Query byBrand = MatchQuery.of(m->m.field("brand")
                        .query(text))
                ._toQuery();

        SearchResponse<ProductES> response = elasticsearchClient.search(s-> s.index(AppConstants.ES_INDEX)
                .query(q->q
                        .bool(b->b
                                .should(byName)
                                .should(byCategory)
                                .should(byBrand))),
                ProductES.class);

        if(response.hits().total().value() > 0){
            List<Integer> prods = new ArrayList<>();
            List<Hit<ProductES>> list = response.hits().hits();
            Map<Integer, Integer> stock = new HashMap<>();
            for(Hit<ProductES> p : list){
                prods.add(Integer.parseInt(p.source().getProductId()));
                stock = inventoryGrpcClient.getStock(prods);
                products.add(p.source());
            }
            for(ProductES p : products){
                p.setStock(stock.get(Integer.parseInt(p.getProductId())));
            }
        }
        else{
            throw new ProductNotFoundException("No products found matching with "+text);
        }
        return products;
    }




}
