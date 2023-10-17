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
}
