package com.ecom.catalogue.service;

import com.ecom.catalogue.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategory(String categoryId);
    List<Category> getAllCategories();
}
