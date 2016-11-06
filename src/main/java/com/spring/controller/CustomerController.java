package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.domain.Customer;
import com.spring.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/customers")
	public String getCustomers(Model model) {
		model.addAttribute("customers", customerService.listAllCustomers());
		return "customers";
	}

	@RequestMapping("/customer/{id}")
	public String getCustomer(@PathVariable Integer id, Model model) {
		Customer customer = customerService.getCustomerById(id);
		model.addAttribute("customer", customer);
		return "customer";
	}

	@RequestMapping("/customer/new")
	public String getCustomerform(Model model) {
		model.addAttribute("customer", new Customer());
		return "customerform";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public String postCustomer(Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customers";
	}

	@RequestMapping("/customer/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id) {
		customerService.deleteCustomer(id);
		return "redirect:/customers";
	}

	@RequestMapping("/customer/edit/{id}")
	public String editCustomer(@PathVariable Integer id, Model model) {
		Customer customer = customerService.getCustomerById(id);
		model.addAttribute("customer", customer);
		return "customerform";
	}
}
