package com.inventorybillingmanagement.service;

import com.inventorybillingmanagement.dto.SaleItemRequest;
import com.inventorybillingmanagement.dto.SalesBillRequest;
import com.inventorybillingmanagement.entity.*;
import com.inventorybillingmanagement.repository.ProductRepository;
import com.inventorybillingmanagement.repository.SalesBillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesBillServiceImpl implements SalesBillService {

    private final SalesBillRepository salesBillRepository;
    private final ProductRepository productRepository;

    @Override
    public SalesBill createBill(SalesBillRequest request) {
        List<SaleItem> items = new ArrayList<>();
        double totalAmount = 0;

        for (SaleItemRequest itemReq : request.getItems()) {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            if (product.getQuantityInStock() < itemReq.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock for product: " + product.getName());
            }

            product.setQuantityInStock(product.getQuantityInStock() - itemReq.getQuantity());
            productRepository.save(product);

            SaleItem item = SaleItem.builder()
                    .product(product)
                    .quantity(itemReq.getQuantity())
                    .price(itemReq.getPrice())
                    .build();

            items.add(item);
            totalAmount += itemReq.getQuantity() * itemReq.getPrice();
        }

        SalesBill bill = SalesBill.builder()
                .customerName(request.getCustomerName())
                .saleDate(LocalDate.now())
                .items(items)
                .totalAmount(totalAmount)
                .build();

        for (SaleItem item : items) {
            item.setBill(bill);
        }

        return salesBillRepository.save(bill);
    }

    @Override
    public List<SalesBill> getAllBills() {
        return salesBillRepository.findAll();
    }

    @Override
    public List<SalesBill> searchByCustomerName(String name) {
        return salesBillRepository.findByCustomerNameContainingIgnoreCase(name);
    }
}
