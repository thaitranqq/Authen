package com.admin.admin.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailOrderInfor {
    private List<OrderMailProductRespone> productList;
    private String name;
    private String address;
    private String phone;
    private float total;
    private String email;
}
