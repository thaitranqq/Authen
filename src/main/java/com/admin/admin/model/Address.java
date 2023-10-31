package com.admin.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;
    private String address_shipping;
    private String name;
    private String email;
    private String phone;

    public Address(String user_id, String address_shipping, String name, String email, String phone) {
        this.userid = user_id;
        this.address_shipping = address_shipping;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
