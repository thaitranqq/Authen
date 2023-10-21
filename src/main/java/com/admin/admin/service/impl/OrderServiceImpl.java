package com.admin.admin.service.impl;

import com.admin.admin.model.*;
import com.admin.admin.repository.AddressRepository;
import com.admin.admin.repository.OrderDetailRepository;
import com.admin.admin.repository.OrderItemRepository;
import com.admin.admin.repository.ProductRepository;
import com.admin.admin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderDetailRepository orderDetailRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final OrderItemRepository orderItemRepository;
    @Autowired
    private final AddressRepository addressRepository;
    @Override
    public ResponseEntity<?> addOrder(OrderRequest orderRequest) {
        try {
            OrderResponse response = new OrderResponse();
            String id = orderRequest.getAddress().getUser_id();
            String idOrder = RandomStringUtils.random(5,true,true);
            float[] total={0};
            for(DataOrderRequest i : orderRequest.getDataOrderRequests()){
                Optional<Product> priceProduct = productRepository.findById(i.getId());
                priceProduct.stream().forEach(product -> {
                    float price = product.getPrice() * i.getValue();
                    total[0] += price;
                });
            }
            OrderDetail orderDetail = new OrderDetail(idOrder ,
                    orderRequest.getAddress().getAddress_id(),
                    id,
                    orderRequest.getAddress().getAddress(),
                    LocalDateTime.now(),
                    orderRequest.getAddress().getPayment_method(),
                    total[0],
                    1);
            orderDetailRepository.save(orderDetail);
            response.setOrderDetail(orderDetail);
            response.setTotal(total[0]);
            Optional<Address> address = addressRepository.findById(orderRequest.getAddress().getAddress_id());
            address.stream().forEach(address1 -> {
                response.setAddress(address1);
            });
            for(DataOrderRequest i : orderRequest.getDataOrderRequests()){
                Optional<Product> priceProduct = productRepository.findById(i.getId());
                priceProduct.stream().forEach(product -> {
                    orderItemRepository.save(new OrderItem(idOrder,
                            i.getId(),
                            i.getValue(),
                            product.getPrice()
                    ));
                });
            }
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getOrderByDate(LocalDateTime startDate, LocalDateTime endDate) {
        try {
            return ResponseEntity.ok(orderDetailRepository.findByOrderdateBetween(startDate,endDate));
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }

    }

    @Override
    public boolean setStatus(String id) {
        try {
            Optional<OrderDetail> detail = orderDetailRepository.findById(id);
            if (!detail.isPresent()){
                return false;
            }
            detail.stream().forEach(orderDetail -> {
                int check = orderDetail.getOrder_status();
                if(check == 1){
                    check = 0;
                }else {
                    check = 1;
                }
                orderDetail.setOrder_status(check);
                orderDetailRepository.save(orderDetail);
            });
            return true;
        }catch (Exception e ){
            return false;
        }
    }
}
