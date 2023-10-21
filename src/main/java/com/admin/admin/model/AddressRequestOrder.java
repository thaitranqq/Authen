package com.admin.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String user_id;
    private String email;
    private Long address_id;
    private String payment_method;
    private String phone;
    private String address;
}
