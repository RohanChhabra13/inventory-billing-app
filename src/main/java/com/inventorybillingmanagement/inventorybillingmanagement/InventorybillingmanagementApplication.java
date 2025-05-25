package com.inventorybillingmanagement.inventorybillingmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.inventorybillingmanagement")
@EnableJpaRepositories("com.inventorybillingmanagement.repository")
@EntityScan("com.inventorybillingmanagement.entity")
public class InventorybillingmanagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventorybillingmanagementApplication.class, args);
    }

}