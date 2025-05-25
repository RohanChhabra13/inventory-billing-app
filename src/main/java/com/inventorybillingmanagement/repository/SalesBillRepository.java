package com.inventorybillingmanagement.repository;

import com.inventorybillingmanagement.entity.SalesBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SalesBillRepository extends JpaRepository<SalesBill, Long> {
    List<SalesBill> findBySaleDate(LocalDate date);
    List<SalesBill> findBySaleDateBetween(LocalDate start, LocalDate end);
    List<SalesBill> findByCustomerNameContainingIgnoreCase(String name);

}
