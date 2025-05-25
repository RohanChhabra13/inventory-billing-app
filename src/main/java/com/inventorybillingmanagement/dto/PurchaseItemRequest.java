package com.inventorybillingmanagement.dto;

import lombok.Data;

@Data
public class PurchaseItemRequest {
    private Long productId;
    private int quantity;
    private Double price;

    // If productId is null → we expect these fields to be filled
    private String name;
    private String description;
    private String unit;
    private Double gstPercent;
}
