package com.mabc.services.product;

import com.mabc.dto.ProductDTO;
import com.mabc.entities.Category;
import com.mabc.entities.Mark;
import com.mabc.entities.Product;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);
    ProductDTO getProductById(Long id);
    void deleteProduct(Long id);
}