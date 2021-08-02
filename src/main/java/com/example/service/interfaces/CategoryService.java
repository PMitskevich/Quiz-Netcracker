package com.example.service.interfaces;

import com.example.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category findCategoryById(UUID id);

    List<Category> findAllCategory();

    Category saveCategory(Category category);

    Category updateCategory(UUID categoryId, Category categoryRequest);

    void deleteCategory(UUID categoryId);
}
