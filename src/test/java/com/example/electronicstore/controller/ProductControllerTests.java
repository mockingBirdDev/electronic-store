package com.example.electronicstore.controller;

import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.electronicstore.entity.Product;
import com.example.electronicstore.entity.enums.Deals;
import com.example.electronicstore.repos.ProductRepository;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {
  @Autowired
  private MockMvc mvc;

  @MockBean
  ProductRepository productRepository;

  @InjectMocks
  ProductController productController;

  @DisplayName("Test Controller Example")
  @Test
  public void getAllProductsAPI() throws Exception
  {
    Product product = new Product();
    product.setId(1l);
    product.setName("product");
    product.setDeals(Deals.NORMAL);
    product.setPrice(20d);

    when(productRepository.findAll()).thenReturn(Collections.singletonList(
      product
    ));

    mvc.perform(MockMvcRequestBuilders
    .get("/products")
    .accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNotEmpty())
    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1l));
      
  }
}
