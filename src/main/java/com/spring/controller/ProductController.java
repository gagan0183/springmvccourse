package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.domain.Product;
import com.spring.service.ProductService;

@Controller
public class ProductController {

	private ProductService productService;

	@RequestMapping("/products/list")
	public String listProducts(Model model) {
		model.addAttribute("products", productService.listAllProducts());
		return "products";
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping("/product/show/{id}")
	public String getProduct(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "product";
	}

	@RequestMapping("/product/new")
	public String createProduct(Model model) {
		model.addAttribute("product", new Product());
		return "productform";
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String saveProduct(Product product) {
		Product product1 = productService.saveProduct(product);
		return "redirect:/product/" + product1.getId();
	}

	@RequestMapping("/product/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "productform";
	}

	@RequestMapping("/product/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}
}
