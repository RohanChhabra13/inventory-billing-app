package com.inventorybillingmanagement.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SalesBillRequest {
    private String billNumber;
    private String customerName;
    private LocalDate billDate;
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<SaleItemRequest> items;
}
