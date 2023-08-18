package com.example.electronicstore.entity;

import java.util.HashMap;
import java.util.Map;


import com.example.electronicstore.entity.enums.Deals;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private Map<Long, ItemDetails> items = new HashMap<Long, ItemDetails>();

    private Double totalPrice;

    @Version
    private int version;


    public ShoppingCart () {
        this.totalPrice = 0d;
    }

    public ShoppingCart(Map<Long, ItemDetails> items) {
        this.items = items;
        this.totalPrice = 0d;
    }


    public Long getId() {
        return id;
    }

    public Map<Long, ItemDetails> getItems() {
        return items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItems(Map<Long, ItemDetails> items) {
        this.items = items;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addTotal(Double subtotal) {
        this.totalPrice += subtotal;
    }

    public void subtractTotal(Double subtotal) {
        this.totalPrice -= subtotal;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Embeddable
    public static class ItemDetails {

        Deals deals;
        Double price;
        Long amount;
        Double subtotal;

        ItemDetails() {
            this.subtotal = 0d;
        }

        public ItemDetails(Deals deals, Double price, Long amount) {
            this.deals = deals;
            this.price = price;
            this.amount = amount;
            this.subtotal = 0d;
        }

        public Deals getDeals() {
            return deals;
        }

        public void setDeals(Deals deals) {
            this.deals = deals;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

        public Double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(Double subtotal) {
            this.subtotal = subtotal;
        }
    }

}

