package com.huy.ecommercebe.repository;

import com.huy.ecommercebe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepostitoy extends JpaRepository<Category,Long> {
    Category findCategoryByName(String categoryName);

    Category findByNameAndParentCatgory_Name(String categoryName, String parentCategoryName);
}
