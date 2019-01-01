package com.lambton.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lambton.project.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByUsername(String username);
}
