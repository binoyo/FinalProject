package com.lambton.project.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lambton.project.entity.Customer;
import com.lambton.project.repository.CustomerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByUsername(username);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(), grantedAuthorities);
	}

}
