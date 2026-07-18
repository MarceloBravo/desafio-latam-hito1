package com.mabc.services.product;

import com.mabc.dto.ProductDTO;
import com.mabc.dto.MarkDTO;
import com.mabc.dto.CategoryDTO;
import com.mabc.entities.Category;
import com.mabc.entities.Mark;
import com.mabc.entities.Product;

import com.mabc.repositories.ProductRepository;
import com.mabc.repositories.CategoryRepository;
import com.mabc.repositories.MarkRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MarkRepository markRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, MarkRepository markRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.markRepository = markRepository;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Optional<Mark> mark = markRepository.findById(productDTO.getMark().getId());
        Boolean isCategoryExists = this.isCategoryExists(productDTO.getCategories());

        if(!mark.isPresent()){
            throw new IllegalArgumentException("Mark not exist");
        }
        
        if(!isCategoryExists){
            throw new IllegalArgumentException("Category not exist");
        }

        Product product = this.productRepository.findById(productDTO.getId()).orElse(new Product());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPriceCost(productDTO.getPriceCost());
        product.setPriceSale(productDTO.getPriceSale());
        List<Category> categories = categoryRepository.findAllById(
            productDTO.getCategories().stream().map(CategoryDTO::getId).toList()
        );
        product.setCategories(categories);
        product.setMark(mark.get());

        Product savedProduct = productRepository.save(product);
        return new ProductDTO(
            savedProduct.getId(),
            new MarkDTO(savedProduct.getMark().getId(), savedProduct.getMark().getName(), savedProduct.getMark().getActive()),
            new CategoryDTO(savedProduct.getCategories().get(0).getId(), savedProduct.getCategories().get(0).getName(), savedProduct.getCategories().get(0).getActive()),
            savedProduct.getName(),
            savedProduct.getDescription(),
            savedProduct.getStock(),
            savedProduct.getWeight(),
            savedProduct.getPriceCost(),
            savedProduct.getPriceSale()
        );
    }

    private Boolean isCategoryExists(List<CategoryDTO> categoryDTOs) throws IllegalArgumentException {
        if (categoryDTOs == null || categoryDTOs.isEmpty()) {
            return false;
        }

        List<Category> categories = categoryRepository.findAll();
        return categories.stream().anyMatch(category -> categoryDTOs.stream().anyMatch(categoryDTO -> category.getId().equals(categoryDTO.getId())));

    }

    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return new ProductDTO(
                product.get().getId(),
                new MarkDTO(product.get().getMark().getId(), product.get().getMark().getName(), product.get().getMark().getActive()),
                new CategoryDTO(product.get().getCategories().get(0).getId(), product.get().getCategories().get(0).getName(), product.get().getCategories().get(0).getActive()),
                product.get().getName(),
                product.get().getDescription(),
                product.get().getStock(),
                product.get().getWeight(),
                product.get().getPriceCost(),
                product.get().getPriceSale()
            );
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        if(!this.productRepository.existsById(id)){
            throw new IllegalArgumentException("Product not exist");
        }
        productRepository.deleteById(id);
    }   
}