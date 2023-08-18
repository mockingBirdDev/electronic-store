package com.example.electronicstore.entity;


import com.example.electronicstore.entity.enums.Deals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    private Deals deals;

    private Double price;

    @Version
    private int version;

    public Product() {}

    public Product (String name, Deals deals, Double price) {
        this.name = name;
        this.deals = deals;
        this.price = price;
    }

    public int getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Deals getDeals() {
        return deals;
    }

    public Double getPrice() {
        return price;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeals(Deals deals) {
        this.deals = deals;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
