package com.mabc.services.category;

import com.mabc.dto.CategoryDTO;
import com.mabc.entities.Category;
import com.mabc.repositories.CategoryRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        this.validaDatos(categoryDTO);

        Category category;
        if (categoryDTO.getId() == null) {
            category = new Category();
        } else {
            category = this.repository.findById(categoryDTO.getId()).orElse(new Category());
        }

        category.setName(categoryDTO.getName());
        category.setActive(categoryDTO.getActive());

        Category savedCategory = this.repository.save(category);
        categoryDTO.setId(savedCategory.getId());

        return categoryDTO;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = this.repository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }
        return new CategoryDTO(category.getId(), category.getName(), category.getActive());
    }

    @Override
    public void deleteCategory(Long id) {
        if(!this.repository.existsById(id)){
            throw new IllegalArgumentException("Category not exist");
        }
        this.repository.deleteById(id);
    }

    private Boolean validaDatos(CategoryDTO categoryDTO) throws IllegalArgumentException {
        Boolean isValid = true;       
        isValid = categoryDTO.getName() != null && !categoryDTO.getName().isEmpty();
        isValid = categoryDTO.getActive() != null;

        if (!isValid) {
            throw new IllegalArgumentException("Invalid category data");
        }
        return true;
    }
}