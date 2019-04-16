package com.saculo.internship.receipt;

import com.saculo.internship.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private List<Product> products = new ArrayList<>();
    private Double allProductsPrices = (double) 0;

    public void addProduct(Product product) {
        products.add(product);
    }

    public Double sumAllProductsPrices() {
        for(Product product : products)
            allProductsPrices += product.getPrice();

        return allProductsPrices;
    }

    public List<Product> getProducts() {
        return products;
    }
}
