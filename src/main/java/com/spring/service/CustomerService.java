package com.spring.service;

import java.util.List;

import com.spring.domain.Customer;
import com.spring.domain.Product;

public interface CustomerService {
	List<Customer> listAllCustomers();

	Customer getCustomerById(Integer id);

	Customer saveCustomer(Customer customer);

	void deleteCustomer(Integer id);
}
