package com.admin.admin.service;

import com.admin.admin.model.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    boolean setIsPost(Long id);
    ResponseEntity<?> updateProduct(Long productID, Product product);
}
