package com.inventorybillingmanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseBillRequest {
    private String supplierName;
    private List<PurchaseItemRequest> items;
    private String billNumber;
}
