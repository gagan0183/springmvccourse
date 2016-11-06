package com.spring.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spring.domain.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private Map<Integer, Customer> customers;

	public CustomerServiceImpl() {
		loadCustomers();
	}

	@Override
	public List<Customer> listAllCustomers() {
		return new ArrayList<>(customers.values());
	}

	@Override
	public Customer getCustomerById(Integer id) {
		return customers.get(id);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		if (customer != null) {
			if (customer.getId() == null) {
				customer.setId(getNext());
			}
			customers.put(customer.getId(), customer);
			return customer;
		} else {
			throw new RuntimeException("ar");
		}
	}

	private int getNext() {
		return Collections.max(customers.keySet()) + 1;
	}

	@Override
	public void deleteCustomer(Integer id) {
		customers.remove(id);
	}

	private void loadCustomers() {
		customers = new HashMap<>();

		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setFirstName("first name 1");
		customer1.setLastName("Last name 1");
		customer1.setEmail("email 1");
		customer1.setPhoneNumber("111111111");
		customer1.setAddressLine1("Address line 1");
		customer1.setAddressLine2("Address line 2");
		customer1.setCity("city 1");
		customer1.setState("state 1");
		customer1.setZipcode("zipcode 1");

		customers.put(1, customer1);

		Customer customer2 = new Customer();
		customer2.setId(2);
		customer2.setFirstName("first name 2");
		customer2.setLastName("Last name 2");
		customer2.setEmail("email 2");
		customer2.setPhoneNumber("2222");
		customer2.setAddressLine1("Address line 1");
		customer2.setAddressLine2("Address line 2");
		customer2.setCity("city 2");
		customer2.setState("state 2");
		customer2.setZipcode("zipcode 2");

		customers.put(2, customer2);

		Customer customer3 = new Customer();
		customer3.setId(3);
		customer3.setFirstName("first name 3");
		customer3.setLastName("Last name 3");
		customer3.setEmail("email 3");
		customer3.setPhoneNumber("333");
		customer3.setAddressLine1("Address line 1");
		customer3.setAddressLine2("Address line 2");
		customer3.setCity("city 3");
		customer3.setState("state 3");
		customer3.setZipcode("zipcode 3");

		customers.put(3, customer3);

		Customer customer4 = new Customer();
		customer4.setId(4);
		customer4.setFirstName("first name 4");
		customer4.setLastName("Last name 4");
		customer4.setEmail("email 4");
		customer4.setPhoneNumber("441");
		customer4.setAddressLine1("Address line 1");
		customer4.setAddressLine2("Address line 2");
		customer4.setCity("city 4");
		customer4.setState("state 4");
		customer4.setZipcode("zipcode 4");

		customers.put(4, customer4);
	}
}
