package com.mabc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mabc.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // This interface is intended to handle data persistence for products.
}