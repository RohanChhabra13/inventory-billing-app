package com.inventorybillingmanagement.repository;

import com.inventorybillingmanagement.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
}
