package com.inventorybillingmanagement.service;

import com.inventorybillingmanagement.dto.DashboardSummary;
import com.inventorybillingmanagement.entity.PurchaseBill;
import com.inventorybillingmanagement.entity.SalesBill;
import com.inventorybillingmanagement.repository.PurchaseBillRepository;
import com.inventorybillingmanagement.repository.SalesBillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final SalesBillRepository salesRepo;
    private final PurchaseBillRepository purchaseRepo;

    @Override
    public DashboardSummary getSummary() {
        LocalDate today = LocalDate.now();
        LocalDate monthStart = today.withDayOfMonth(1);
        LocalDate threeMonthStart = today.minusMonths(3).withDayOfMonth(1);
        LocalDate yearStart = today.withDayOfYear(1);

        double todaySales = sum(salesRepo.findBySaleDate(today));
        double monthSales = sum(salesRepo.findBySaleDateBetween(monthStart, today));
        double threeMonthSales = sum(salesRepo.findBySaleDateBetween(threeMonthStart, today));
        double yearSales = sum(salesRepo.findBySaleDateBetween(yearStart, today));

        double todayPurchases = sum(purchaseRepo.findByPurchaseDate(today));
        double monthPurchases = sum(purchaseRepo.findByPurchaseDateBetween(monthStart, today));
        double threeMonthPurchases = sum(purchaseRepo.findByPurchaseDateBetween(threeMonthStart, today));
        double yearPurchases = sum(purchaseRepo.findByPurchaseDateBetween(yearStart, today));

        return new DashboardSummary(
                todaySales, monthSales, threeMonthSales, yearSales,
                todayPurchases, monthPurchases, threeMonthPurchases, yearPurchases
        );
    }

    private double sum(List<? extends Object> bills) {
        return bills.stream()
                .mapToDouble(b -> {
                    if (b instanceof SalesBill sb) return sb.getTotalAmount();
                    if (b instanceof PurchaseBill pb) return pb.getTotalAmount();
                    return 0.0;
                })
                .sum();
    }
}
