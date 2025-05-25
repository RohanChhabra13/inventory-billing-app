package com.inventorybillingmanagement.service;

import com.inventorybillingmanagement.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product updatedProduct);
    void deleteProduct(Long id);
    Product getProduct(Long id);
    List<Product> getAllProducts();
}
