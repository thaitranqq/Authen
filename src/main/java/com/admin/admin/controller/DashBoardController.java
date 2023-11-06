package com.admin.admin.controller;

import com.admin.admin.model.CategoryChartReponse;
import com.admin.admin.model.OrderDetail;
import com.admin.admin.model.OrderItem;
import com.admin.admin.model.Product;
import com.admin.admin.repository.OrderDetailRepository;
import com.admin.admin.repository.OrderItemRepository;
import com.admin.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashBoardController {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    @GetMapping("/productsold")
    public int getProductSold(){
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDate = LocalDateTime.now();
        int ans = 0;
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderdateBetween(startOfMonth,endOfDate);
        for (OrderDetail orderDetail:orderDetails
             ) {
            List<OrderItem> orderItemList = orderItemRepository.findByOrderdetailid(orderDetail.getId());
            for (OrderItem item : orderItemList
            ){
                ans += item.getQuantity();
            }
        }
        return ans;
    }

    @GetMapping("/budget")
    public float getTotalBudget(){
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDate = LocalDateTime.now();
        float ans = 0;
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderdateBetween(startOfMonth,endOfDate);
        for (OrderDetail orderDetail:orderDetails
        ) {
            List<OrderItem> orderItemList = orderItemRepository.findByOrderdetailid(orderDetail.getId());
            for (OrderItem item : orderItemList
            ){
                ans += (item.getPrice() * item.getQuantity());
            }
        }
        return ans;
    }

    @GetMapping("/getChartCategory")
    public ResponseEntity<?> getChartCategory(){
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDate = LocalDateTime.now();
        AtomicInteger wood = new AtomicInteger();
        AtomicInteger metal = new AtomicInteger();
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderdateBetween(startOfMonth,endOfDate);
        for (OrderDetail orderDetail:orderDetails
        ) {
            List<OrderItem> orderItemList = orderItemRepository.findByOrderdetailid(orderDetail.getId());
            for (OrderItem item : orderItemList
            ){
                Optional<Product> product = productRepository.findById(item.getProduct_item_id());
                product.stream().forEach(product1 -> {
                    if (product1.getCategory_id() == 1){
                        wood.addAndGet(item.getQuantity());
                    }else {
                        metal.addAndGet(item.getQuantity());
                    }
                });
            }
        }
        CategoryChartReponse categoryChartReponse = new CategoryChartReponse(wood,metal);
        return ResponseEntity.ok(categoryChartReponse);
    }
}
