package com.admin.admin.service.impl;

import com.admin.admin.model.*;
import com.admin.admin.repository.*;
import com.admin.admin.service.MailService;
import com.admin.admin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private final MailService mailService;
    @Autowired
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<?> addOrder(OrderRequest orderRequest) {
        try {
            MailOrderInfor orderInfor = new MailOrderInfor();
            OrderResponse response = new OrderResponse();
            String id = orderRequest.getAddress().getUser_id();
            Optional<Users> users = userRepository.findByEmail(id);
            users.stream().forEach(users1 -> {
                orderInfor.setName(users1.getUsername());
            });
            String idOrder = RandomStringUtils.random(5,true,true);
            float[] total={0};
            for(DataOrderRequest i : orderRequest.getDataOrderRequests()){
                Optional<Product> priceProduct = productRepository.findById(i.getId());
                priceProduct.stream().forEach(product -> {
                    float price = product.getPrice() * i.getValue();
                    total[0] += price;
                });
            }
            orderInfor.setTotal(total[0]);
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
                orderInfor.setAddress(address1.getAddress_shipping());
                orderInfor.setPhone(address1.getPhone());
                response.setAddress(address1);
            });
            List<OrderMailProductRespone> productList = new ArrayList<>();
            for(DataOrderRequest i : orderRequest.getDataOrderRequests()){
                Optional<Product> priceProduct = productRepository.findById(i.getId());
                OrderMailProductRespone mailProductRespone = new OrderMailProductRespone();
                priceProduct.stream().forEach(product -> {
                    mailProductRespone.setName(product.getName());
                    mailProductRespone.setQuality(i.getValue());
                    mailProductRespone.setPrice(i.getValue()*product.getPrice());
                    productList.add(mailProductRespone);
                    orderItemRepository.save(new OrderItem(idOrder,
                            i.getId(),
                            i.getValue(),
                            product.getPrice()
                    ));
                });
            }
            orderInfor.setProductList(productList);
            orderInfor.setEmail(orderRequest.getAddress().getEmail());
            mailService.sendemail(orderInfor);
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
