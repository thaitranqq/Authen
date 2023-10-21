package com.admin.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_item")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long category_id;
    private String name;
    private String description;
    private long amount;
    private float price;
    private String product_image;
    private Boolean is_customer;
    private Boolean is_post;

    public Product(Long category_id, String name, String description, long amount, float price, String product_image, Boolean is_customer, Boolean is_post) {
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.product_image = product_image;
        this.is_customer = is_customer;
        this.is_post = is_post;
    }
}
