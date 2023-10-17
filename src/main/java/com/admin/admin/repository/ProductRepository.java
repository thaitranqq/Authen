package com.admin.admin.repository;

import com.admin.admin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findTop5ByNameContainingIgnoreCase(String name);
    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderByPriceDesc();

}
