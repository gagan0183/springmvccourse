package com.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring.controller.IndexController;

public class IndexControllerTest {
	private MockMvc mvc;

	private IndexController indexController;

	@Before
	public void setup() {
		indexController = new IndexController();
		mvc = MockMvcBuilders.standaloneSetup(indexController).build();
	}

	@Test
	public void testIndex() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("index"));
	}
}
