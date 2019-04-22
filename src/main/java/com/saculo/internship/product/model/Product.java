package com.saculo.internship.product.model;

public class Product {

    private String barCode;
    private String name;
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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
