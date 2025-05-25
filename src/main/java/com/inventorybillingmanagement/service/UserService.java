package com.inventorybillingmanagement.service;

import com.inventorybillingmanagement.dto.SignupRequest;
import com.inventorybillingmanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User signup(SignupRequest request);
    List<User> getAllUsers();
    Optional<User> getUserByUsername(String username);
    User login(String username, String rawPassword);
}
