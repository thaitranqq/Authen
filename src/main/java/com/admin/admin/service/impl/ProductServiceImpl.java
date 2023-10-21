package com.admin.admin.service.impl;

import com.admin.admin.model.Product;
import com.admin.admin.repository.ProductRepository;
import com.admin.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public boolean setIsPost(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if(!product.isPresent()){
                return false;
            }
            product.stream().forEach(product1 -> {
                Boolean check = product1.getIs_post();
                check = !check;
                product1.setIs_post(check);
                productRepository.save(product1);
            });
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
