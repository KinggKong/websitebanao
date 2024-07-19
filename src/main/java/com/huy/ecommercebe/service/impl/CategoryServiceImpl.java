package com.huy.ecommercebe.service.impl;

import com.huy.ecommercebe.model.Category;
import com.huy.ecommercebe.repository.CategoryRepostitoy;
import com.huy.ecommercebe.service.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CategoryServiceImpl implements ICategoryService {
    CategoryRepostitoy categoryRepostitoy;

    @Override
    public Category createCategory() {
        return null;
    }

    @Override
    public Category updateCategory() {
        return null;
    }

    @Override
    public void deleteCategory() {

    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryRepostitoy.findById(categoryId).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }
}
