package com.admin.admin.service;

import org.springframework.http.ResponseEntity;

public interface DashboardService {
    int getProductSold();
    float getTotalBudget();
    ResponseEntity<?> getChartCategory();
    ResponseEntity<?> getSalesWeek(int year, int month);
}
