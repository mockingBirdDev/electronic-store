package com.example.electronicstore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.electronicstore.controller.error.AlreadyExistException;
import com.example.electronicstore.controller.error.NotFoundException;
import com.example.electronicstore.entity.ShoppingCart;
import com.example.electronicstore.entity.ShoppingCart.ItemDetails;
import com.example.electronicstore.entity.enums.Deals;
import com.example.electronicstore.repos.ShoppingCartRepository;
import com.example.electronicstore.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    private final ShoppingCartRepository repository;

    ShoppingCartServiceImpl(ShoppingCartRepository repository) {
        this.repository = repository;
    }

    public List<ShoppingCart> findAll() {
        return repository.findAll();
    }

    public ShoppingCart findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id, "ShoppingCart"));
    }

    public ShoppingCart addNewCart(ShoppingCart newCart) {
        Map<Long, ItemDetails> itemsList = newCart.getItems();
        for(Map.Entry<Long, ItemDetails> items : itemsList.entrySet()) {
            double realAmount = Deals.getActualMultiplier(items.getValue().getDeals(), items.getValue().getAmount());
            double subtotal = realAmount * items.getValue().getPrice();
            items.getValue().setSubtotal(subtotal);
            newCart.addTotal(subtotal);
        }
        newCart.setItems(itemsList);
        return repository.save(newCart);
    }

    public ShoppingCart updateShoppingCart(ShoppingCart newCart, Long id) {
        Map<Long, ItemDetails> itemsList = newCart.getItems();
        for(Map.Entry<Long, ItemDetails> items : itemsList.entrySet()) {
            double realAmount = Deals.getActualMultiplier(items.getValue().getDeals(), items.getValue().getAmount());
            double subtotal = realAmount * items.getValue().getPrice();
            items.getValue().setSubtotal(subtotal);
            newCart.addTotal(subtotal);
        }
        newCart.setItems(itemsList);
        return repository.findById(id)
        .map(cart -> {
          cart.setItems(newCart.getItems());
          cart.setTotalPrice(newCart.getTotalPrice());
          return repository.save(cart);
        })
        .orElseGet(() -> {
          newCart.setId(id);
          return repository.save(newCart);
        });
    }

    public void deleteShoppingCart(Long id) {
        repository.deleteById(id);
    }

    public Map<Long, ItemDetails> findAllItemsFromCart(Long id) {
        ShoppingCart cart = repository.findById(id).orElseThrow(() -> new NotFoundException(id, "ShoppingCart"));
        return cart != null ? cart.getItems() : null;
    }

    public ItemDetails findSingleItemsFromCart(Long id, Long productId) {
        ShoppingCart cart = repository.findById(id).orElseThrow(() -> new NotFoundException(id, "ShoppingCart"));
        if (cart != null) {
            ItemDetails requiredItem = cart.getItems().get(productId);
            if(requiredItem == null) {
                throw new NotFoundException(productId, "Shopping Cart Items");
            }
            return requiredItem;
        }
        else {
            throw new NotFoundException(id, "ShoppingCart");
        }
    }

    public ShoppingCart addSingleItemsToCart(Long id, Long productId, ItemDetails itemDetails) {
         ShoppingCart cart = repository.findById(id).orElseThrow(() -> new NotFoundException(id, "ShoppingCart"));
        if (cart != null) {
            if(cart.getItems().containsKey(productId)) {
                throw new AlreadyExistException(productId, "Shopping Cart Items");
            }
            double realAmount = Deals.getActualMultiplier(itemDetails.getDeals(), itemDetails.getAmount());
            double subtotal = realAmount * itemDetails.getPrice();
            itemDetails.setSubtotal(subtotal);
            cart.addTotal(subtotal);
            Map<Long, ItemDetails> originalItems = cart.getItems();
            originalItems.putAll(Map.of(productId, itemDetails));
            cart.setItems(originalItems);
            repository.save(cart);
            return cart;
        }
        else {
            throw new NotFoundException(id, "ShoppingCart");
        }
    }

    public ShoppingCart modifySingleItemsInCart(Long id, Long productId, ItemDetails itemDetails) {
        ShoppingCart cart = repository.findById(id).orElseThrow(() -> new NotFoundException(id, "ShoppingCart"));
        if (cart != null) {
            double realAmount = Deals.getActualMultiplier(itemDetails.getDeals(), itemDetails.getAmount());
            double subtotal = realAmount * itemDetails.getPrice();
            itemDetails.setSubtotal(subtotal);
            cart.addTotal(subtotal);
            Map<Long, ItemDetails> originalItems = cart.getItems();
            if(!cart.getItems().containsKey(productId)) {
                originalItems.putAll(Map.of(productId, itemDetails));
                cart.setItems(originalItems);
            }
            else {
                cart.subtractTotal(originalItems.get(productId).getSubtotal());
                originalItems.remove(productId);
                originalItems.putAll(Map.of(productId, itemDetails));
                cart.setItems(originalItems);
            }

            repository.save(cart);
            return cart;
        }
        else {
            throw new NotFoundException(id, "ShoppingCart");
        }
    }

    public ShoppingCart deleteSingleItemsInCart(Long id, Long productId) {
        ShoppingCart cart = repository.findById(id).orElseThrow(() -> new NotFoundException(id, "ShoppingCart"));
         if (cart != null) {
            Map<Long, ItemDetails> originalItems = cart.getItems();
            if(!originalItems.containsKey(productId)) {
                throw new NotFoundException(productId, "Shopping Cart Items");
            }
            cart.subtractTotal(originalItems.get(productId).getSubtotal());
            originalItems.remove(productId);
            cart.setItems(originalItems);
            repository.save(cart);
            return cart;
         }
         else {
            throw new NotFoundException(id, "ShoppingCart");
         }
    }
}
