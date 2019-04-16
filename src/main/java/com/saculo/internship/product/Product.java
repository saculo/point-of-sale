package com.saculo.internship.product;

public class Product {

    private String name;
    private String barCode;
    private Double price;

    public Product(String name, String barCode, Double price) {
        this.name = name;
        this.barCode = barCode;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBarCode() {
        return barCode;
    }

    public Double getPrice() {
        return price;
    }
}
