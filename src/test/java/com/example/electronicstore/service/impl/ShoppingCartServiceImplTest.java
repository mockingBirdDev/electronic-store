package com.example.electronicstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.electronicstore.entity.ShoppingCart;
import com.example.electronicstore.entity.ShoppingCart.ItemDetails;
import com.example.electronicstore.entity.enums.Deals;
import com.example.electronicstore.repos.ShoppingCartRepository;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceImplTest {
    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartServiceImpl;

    @Test
    @DisplayName("Test Service Example")
    public void findById_thenReturnShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        ItemDetails details = new ItemDetails(Deals.NORMAL, 10d, 5l);
        details.setSubtotal(50d);
        cart.setId(1l);
        cart.setItems(Collections.singletonMap(1l, details));
        cart.setTotalPrice(50d);
        cart.setVersion(0);

        when(shoppingCartRepository.findById(1l)).thenReturn(Optional.of(cart));

        ShoppingCart foundCart = shoppingCartServiceImpl.findById(1l);

        assertEquals(foundCart, cart);
    }
}
