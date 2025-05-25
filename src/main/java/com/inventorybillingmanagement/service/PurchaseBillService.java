package com.inventorybillingmanagement.service;

import com.inventorybillingmanagement.dto.PurchaseBillRequest;
import com.inventorybillingmanagement.entity.PurchaseBill;

import java.util.List;

public interface PurchaseBillService {
    PurchaseBill createBill(PurchaseBillRequest request);
    List<PurchaseBill> getAllBills();
}
