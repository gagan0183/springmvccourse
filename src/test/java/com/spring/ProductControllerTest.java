package com.spring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring.controller.ProductController;
import com.spring.domain.Product;
import com.spring.service.ProductService;

public class ProductControllerTest {
	@Mock
	private ProductService productService;
	@InjectMocks
	private ProductController productController;

	private MockMvc mvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void testList() throws Exception {
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());

		Mockito.when(productService.listAllProducts()).thenReturn((List<Product>) products);

		mvc.perform(MockMvcRequestBuilders.get("/products/list")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("products"))
				.andExpect(MockMvcResultMatchers.model().attribute("products", Matchers.hasSize(2)));
	}

	@Test
	public void testShow() throws Exception {
		Integer id = 1;

		Mockito.when(productService.getProductById(id)).thenReturn(new Product());

		mvc.perform(MockMvcRequestBuilders.get("/product/show/1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("product"))
				.andExpect(MockMvcResultMatchers.model().attribute("product", Matchers.instanceOf(Product.class)));
	}

	@Test
	public void testEditGet() throws Exception {
		Integer id = 1;

		Mockito.when(productService.getProductById(id)).thenReturn(new Product());

		mvc.perform(MockMvcRequestBuilders.get("/product/edit/1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("productform"))
				.andExpect(MockMvcResultMatchers.model().attribute("product", Matchers.instanceOf(Product.class)));
	}

	@Test
	public void testNewProduct() throws Exception {
		Mockito.verifyZeroInteractions(productService);

		mvc.perform(MockMvcRequestBuilders.get("/product/new")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("productform"))
				.andExpect(MockMvcResultMatchers.model().attribute("product", Matchers.instanceOf(Product.class)));
	}

	@Test
	public void testSave() throws Exception {
		Integer id = 1;
		String description = "Test description";
		BigDecimal price = new BigDecimal("9.00");
		String imageUrl = "example.com";

		Product returnProduct = new Product();
		returnProduct.setId(id);
		returnProduct.setDescription(description);
		returnProduct.setPrice(price);
		returnProduct.setImageUrl(imageUrl);

		Mockito.when(productService.saveProduct(org.mockito.Matchers.<Product> any())).thenReturn(returnProduct);

		mvc.perform(MockMvcRequestBuilders.post("/product").param("id", "1").param("description", description)
				.param("price", "9.00").param("imageUrl", imageUrl))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/product/1"));

		ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
		Mockito.verify(productService).saveProduct(argumentCaptor.capture());
		Assert.assertEquals(id, argumentCaptor.getValue().getId());
		Assert.assertEquals(description, argumentCaptor.getValue().getDescription());
		Assert.assertEquals(price, argumentCaptor.getValue().getPrice());
		Assert.assertEquals(imageUrl, argumentCaptor.getValue().getImageUrl());
	}

	@Test
	public void testdelete() throws Exception {
		Integer id = 1;

		mvc.perform(MockMvcRequestBuilders.get("/product/delete/1"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/products"));

		Mockito.verify(productService, Mockito.times(1)).deleteProduct(id);
	}
}
