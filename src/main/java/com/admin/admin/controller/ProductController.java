package com.admin.admin.controller;


import com.admin.admin.model.Product;
import com.admin.admin.repository.ProductRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/seacrhByName")
    public ResponseEntity<?> getProductByName(@RequestParam String name){
        List<Product> products = productRepository.findTop5ByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/getAllOrderByPriceAsc")
    public ResponseEntity<?> getAllOrderByPrice(){
        List<Product> products = productRepository.findByOrderByPriceAsc();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/getAllOrderByPriceDesc")
    public ResponseEntity<?> getAllOrderByPriceDesc(){
        List<Product> products = productRepository.findByOrderByPriceDesc();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/getAllOrderByNameAsc")
    public ResponseEntity<?> getAllOrderByNameAsc(){
        List<Product> products = productRepository.findByOrderByPriceAsc();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/getAllOrderByNameDesc")
    public ResponseEntity<?> getAllOrderByNameDesc(){
        List<Product> products = productRepository.findByOrderByNameDesc();
        return ResponseEntity.ok(products);
    }
}
