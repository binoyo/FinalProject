package com.lambton.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lambton.project.entity.Customer;
import com.lambton.project.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
 
	@Autowired
    private CustomerRepository customerRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Customer customer) {
    	customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    @Override
    public Customer findByUsername(String username) {
    	for(Customer cust: customerRepository.findAll()) {
    		if(cust.getUsername().matches(username)) {
    		
    			return cust;
    		}
    	}
        return null;
    }
}
