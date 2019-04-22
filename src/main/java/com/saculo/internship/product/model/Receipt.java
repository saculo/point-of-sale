package com.saculo.internship.product.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private Long id;
    private List<Product> products = new ArrayList<>();

    public Receipt(Long id) {
        this.id = id;
    }

    public Product addProductToReceipt(Product product) {
        products.add(product);
        return product;
    }

    public Double sumPrices() {
        return products.stream()
                       .map(Product::getPrice)
                       .reduce((x,y) -> x + y)
                       .orElse(0.0);
    }

    public List<Product> getProducts() {
        return products;
    }
}
