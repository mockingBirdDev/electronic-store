package com.example.electronicstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.electronicstore.entity.ShoppingCart;
import com.example.electronicstore.entity.ShoppingCart.ItemDetails;
import com.example.electronicstore.service.impl.ShoppingCartServiceImpl;

@RestController
public class ShoppingCartController {

    @Autowired
    ShoppingCartServiceImpl shoppingCartServiceImpl;

    @GetMapping("/shopping-cart")
    List<ShoppingCart> all() {
        return shoppingCartServiceImpl.findAll();
    }

    @GetMapping("/shopping-cart/{id}")
    ShoppingCart one(@PathVariable Long id) {
        return shoppingCartServiceImpl.findById(id);
    }

    @PostMapping("/shopping-cart")
    ShoppingCart newCart(@RequestBody ShoppingCart newCart) {
        return shoppingCartServiceImpl.addNewCart(newCart);
    }

    @PutMapping("/shopping-cart/{id}")
    ShoppingCart updateCart(@RequestBody ShoppingCart newCart, @PathVariable Long id) {
        return shoppingCartServiceImpl.updateShoppingCart(newCart, id);
    }

    @DeleteMapping("/shopping-cart/{id}")
    void deleteCart(@PathVariable Long id) {
        shoppingCartServiceImpl.deleteShoppingCart(id);
    }


    @GetMapping("/shopping-cart/{id}/items")
    Map<Long, ItemDetails> allItemsFromCart(@PathVariable Long id) {
        return shoppingCartServiceImpl.findAllItemsFromCart(id);
    }

    //To find Single Item in specific shopping cart
    @GetMapping("/shopping-cart/{id}/items/{productId}")
    ItemDetails specificItemFromCart(@PathVariable Long id, @PathVariable Long productId) {
       return shoppingCartServiceImpl.findSingleItemsFromCart(id, productId);
    }

    //Add new Item to cart
    @PostMapping("/shopping-cart/{id}/items/{productId}")
    ShoppingCart addSingleItemToCart(@PathVariable Long id, @PathVariable Long productId, @RequestBody ItemDetails itemDetails) {
        return shoppingCartServiceImpl.addSingleItemsToCart(id, productId, itemDetails);
    }

    //Modify Single Item in cart
    @PutMapping("/shopping-cart/{id}/items/{productId}")
    ShoppingCart modifySingleItemInCart(@PathVariable Long id, @PathVariable Long productId, @RequestBody ItemDetails itemDetails) {
        return shoppingCartServiceImpl.modifySingleItemsInCart(id, productId, itemDetails);
    }

    @DeleteMapping("/shopping-cart/{id}/items/{productId}")
    ShoppingCart deleteSingleItemInCart(@PathVariable Long id, @PathVariable Long productId) {
        return shoppingCartServiceImpl.deleteSingleItemsInCart(id, productId);
    }
}
