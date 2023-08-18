package com.example.electronicstore.entity.enums;

public enum Deals {
    NORMAL(0, "normal price"),
    DISCOUNT_TYPE_0(1, "buy one get one free"),
    DISCOUNT_TYPE_1(2, "buy one, 50% off second")
    ;
    private int id;
    private String description;

    Deals(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription(){
        return this.description;
    }

    public static Double getActualMultiplier(Deals deals, Long amount) {
        switch(deals){
            case DISCOUNT_TYPE_0:
                return Double.valueOf((amount / 2) + (amount % 2));
            case DISCOUNT_TYPE_1:
                return Double.valueOf(amount / 2) * 1.5 + (amount % 2);
            default:
                return Double.valueOf(amount);

        }
    }
}
