package com.inventorybillingmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardSummary {
    private double todaySales;
    private double monthSales;
    private double threeMonthSales;
    private double yearSales;

    private double todayPurchases;
    private double monthPurchases;
    private double threeMonthPurchases;
    private double yearPurchases;
}
