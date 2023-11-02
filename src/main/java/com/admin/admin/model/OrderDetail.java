package com.admin.admin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    private String id;
    private Long address_id;
    private String userid;
    private String shipping_address;
    private LocalDateTime orderdate;
    private String payment_method;
    private float order_total;
    private int order_status;

    public OrderDetail(String id, Long address_id, String user_id, String shipping_address, LocalDateTime order_date, String payment_method, float order_total, int order_status) {
        this.id = id;
        this.address_id = address_id;
        this.userid = user_id;
        this.shipping_address = shipping_address;
        this.orderdate = order_date;
        this.payment_method = payment_method;
        this.order_total = order_total;
        this.order_status = order_status;
    }

}
