package com.inventorybillingmanagement.controller;

import com.inventorybillingmanagement.dto.SalesBillRequest;
import com.inventorybillingmanagement.entity.SalesBill;
import com.inventorybillingmanagement.service.SalesBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-bills")
@RequiredArgsConstructor
public class SalesBillController {

    private final SalesBillService salesBillService;

    @PostMapping
    public ResponseEntity<SalesBill> createBill(@RequestBody SalesBillRequest request) {
        return ResponseEntity.ok(salesBillService.createBill(request));
    }

    @GetMapping
    public ResponseEntity<List<SalesBill>> getAll() {
        return ResponseEntity.ok(salesBillService.getAllBills());
    }

    @GetMapping("/sales-bills/search")
    public ResponseEntity<List<SalesBill>> searchSalesByCustomerName(@RequestParam String name) {
        List<SalesBill> bills = salesBillService.searchByCustomerName(name);
        return ResponseEntity.ok(bills);
    }
}
