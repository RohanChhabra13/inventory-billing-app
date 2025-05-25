package com.inventorybillingmanagement.service;

import com.inventorybillingmanagement.dto.PurchaseBillRequest;
import com.inventorybillingmanagement.dto.PurchaseItemRequest;
import com.inventorybillingmanagement.entity.Product;
import com.inventorybillingmanagement.entity.PurchaseBill;
import com.inventorybillingmanagement.entity.PurchaseItem;
import com.inventorybillingmanagement.repository.ProductRepository;
import com.inventorybillingmanagement.repository.PurchaseBillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseBillServiceImpl implements PurchaseBillService {

    private final PurchaseBillRepository purchaseBillRepository;
    private final ProductRepository productRepository;

    @Override
    public PurchaseBill createBill(PurchaseBillRequest request) {
        if (request.getBillNumber() == null || request.getBillNumber().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bill number is required");
        }
        if (purchaseBillRepository.existsByBillNumber(request.getBillNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Bill number already exists");
        }

        List<PurchaseItem> items = new ArrayList<>();
        double totalAmount = 0;

        for (PurchaseItemRequest itemReq : request.getItems()) {
            Product product;

            if (itemReq.getProductId() != null) {
                product = productRepository.findById(itemReq.getProductId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
            } else {
                product = productRepository.findByName(itemReq.getName()).orElse(null);

                if (product == null) {
                    if (itemReq.getName() == null || itemReq.getUnit() == null ||
                            itemReq.getGstPercent() == null || itemReq.getPrice() == null) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New product details are incomplete.");
                    }

                    product = Product.builder()
                            .name(itemReq.getName())
                            .description(itemReq.getDescription())
                            .unit(itemReq.getUnit())
                            .price(itemReq.getPrice())
                            .gstPercent(itemReq.getGstPercent())
                            .quantityInStock(0)
                            .build();
                }
            }

            product.setQuantityInStock(product.getQuantityInStock() + itemReq.getQuantity());
            product = productRepository.save(product);

            PurchaseItem item = PurchaseItem.builder()
                    .product(product)
                    .quantity(itemReq.getQuantity())
                    .price(itemReq.getPrice())
                    .build();

            items.add(item);
            totalAmount += itemReq.getQuantity() * itemReq.getPrice();
        }

        PurchaseBill bill = PurchaseBill.builder()
                .billNumber(request.getBillNumber())
                .purchaseDate(LocalDate.now())
                .supplierName(request.getSupplierName())
                .items(items)
                .totalAmount(totalAmount)
                .build();

        for (PurchaseItem item : items) {
            item.setBill(bill);
        }

        return purchaseBillRepository.save(bill);
    }

    @Override
    public List<PurchaseBill> getAllBills() {
        return purchaseBillRepository.findAll();
    }
}
