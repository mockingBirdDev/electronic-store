package com.example.electronicstore.service;

import java.util.List;
import java.util.Map;

import com.example.electronicstore.entity.ShoppingCart;
import com.example.electronicstore.entity.ShoppingCart.ItemDetails;

public interface ShoppingCartService {
    public List<ShoppingCart> findAll();

    public ShoppingCart findById(Long id);

    public ShoppingCart addNewCart(ShoppingCart newCart);

    public ShoppingCart updateShoppingCart(ShoppingCart newCart, Long id);

    public void deleteShoppingCart(Long id);

    public Map<Long, ItemDetails> findAllItemsFromCart(Long id);

    public ItemDetails findSingleItemsFromCart(Long id, Long productId);

    public ShoppingCart addSingleItemsToCart(Long id, Long productId, ItemDetails itemDetails);

    public ShoppingCart deleteSingleItemsInCart(Long id, Long productId);
}
