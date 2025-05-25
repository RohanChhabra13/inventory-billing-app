package com.inventorybillingmanagement.controller;

import com.inventorybillingmanagement.dto.PurchaseBillRequest;
import com.inventorybillingmanagement.entity.PurchaseBill;
import com.inventorybillingmanagement.service.PurchaseBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-bills")
@RequiredArgsConstructor
public class PurchaseBillController {

    private final PurchaseBillService purchaseBillService;

    @PostMapping
    public ResponseEntity<PurchaseBill> createBill(@RequestBody PurchaseBillRequest request) {
        return ResponseEntity.ok(purchaseBillService.createBill(request));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseBill>> getAll() {
        return ResponseEntity.ok(purchaseBillService.getAllBills());
    }
}
