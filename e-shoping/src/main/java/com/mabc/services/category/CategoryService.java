package com.mabc.services.category;

import com.mabc.dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(Long id);
    void deleteCategory(Long id);
}