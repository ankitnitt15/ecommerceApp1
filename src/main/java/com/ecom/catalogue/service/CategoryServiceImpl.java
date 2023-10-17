package com.ecom.catalogue.service;

import com.ecom.catalogue.exception.CategoryNotFoundException;
import com.ecom.catalogue.model.Category;
import com.ecom.catalogue.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@EnableCaching
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        Category cat = categoryRepository.save(category);
        return cat;
    }

    @Override
    @Cacheable("categories")
    public Category getCategory(String categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isPresent()){
            return category.get();
        }
        else{
            throw new CategoryNotFoundException("No category found with id="+categoryId);
        }
    }

    @Override
    @Cacheable("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
