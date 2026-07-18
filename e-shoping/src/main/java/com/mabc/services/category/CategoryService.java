package com.mabc.services.category;

import com.mabc.dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO registerCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(Long id);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}