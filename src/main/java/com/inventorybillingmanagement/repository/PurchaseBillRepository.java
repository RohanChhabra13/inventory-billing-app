package com.inventorybillingmanagement.repository;

import com.inventorybillingmanagement.entity.PurchaseBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseBillRepository extends JpaRepository<PurchaseBill, Long> {
    List<PurchaseBill> findByPurchaseDate(LocalDate date);
    List<PurchaseBill> findByPurchaseDateBetween(LocalDate start, LocalDate end);
    boolean existsByBillNumber(String billNumber);
}
