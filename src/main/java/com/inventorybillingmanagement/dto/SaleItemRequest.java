package com.inventorybillingmanagement.dto;

import lombok.Data;

@Data
public class SaleItemRequest {
    private Long productId;
    private int quantity;
    private Double price;
}
