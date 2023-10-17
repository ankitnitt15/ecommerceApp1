package com.ecom.catalogue.repository;

import com.ecom.catalogue.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
