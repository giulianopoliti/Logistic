package com.example.logistic.service;

import java.util.Map;

public  class UserUtils {
    public static void validateUserData (Map<String, Object> userData) {
        if (userData.get("name") == null) {
            throw new RuntimeException("Name is required");
        }
        if (userData.get("lastName") == null) {
            throw new RuntimeException("Last name is required");
        }
        if (userData.get("dateOfBirth") == null) {
            throw new RuntimeException("Date of birth is required");
        }
        if (userData.get("email") == null) {
            throw new RuntimeException("Email is required");
        }
        if (userData.get("phone") == null) {
            throw new RuntimeException("Phone is required");
        }
        if (userData.get("username") == null) {
            throw new RuntimeException("Username is required");
        }
        if (userData.get("cuil") == null) {
            throw new RuntimeException("CUIL is required");
        }
        if (userData.get("address") == null) {
            throw new RuntimeException("Address is required");
        }

    }
}
