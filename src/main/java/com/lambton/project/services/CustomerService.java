package com.lambton.project.services;

import com.lambton.project.entity.Customer;

public interface CustomerService {
    void save(Customer customer);

    Customer findByUsername(String username);
}
