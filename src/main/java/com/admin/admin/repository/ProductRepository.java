package com.admin.admin.repository;

import com.admin.admin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findTop5ByNameContainingIgnoreCase(String name);
    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderByPriceDesc();
}
