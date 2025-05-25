package com.inventorybillingmanagement.service;

import com.inventorybillingmanagement.dto.SalesBillRequest;
import com.inventorybillingmanagement.entity.SalesBill;

import java.util.List;

public interface SalesBillService {
    SalesBill createBill(SalesBillRequest request);
    List<SalesBill> getAllBills();
    List<SalesBill> searchByCustomerName(String name);
}
