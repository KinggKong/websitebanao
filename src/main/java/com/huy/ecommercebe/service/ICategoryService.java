package com.huy.ecommercebe.service;

import com.huy.ecommercebe.model.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory();

    Category updateCategory ();

    void deleteCategory();

    Category findCategoryById(Long categoryId);

    List<Category> getAllCategories();
}
