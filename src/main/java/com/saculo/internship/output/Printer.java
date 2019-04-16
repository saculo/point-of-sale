package com.saculo.internship.output;

import com.saculo.internship.product.Product;
import com.saculo.internship.receipt.Receipt;

import java.util.List;

public class Printer implements OutputDevice {

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void printNameAndPriceOfProduct(Product product) {
        print(product.getName() + " " + product.getPrice());
    }

    public void printAllProductsOnReceipt(Receipt receipt) {
        List<Product> productsOnReceipt = receipt.getProducts();
        for(Product product : productsOnReceipt)
            printNameAndPriceOfProduct(product);
    }

    public void printTotalSumOfReceipt(Receipt receipt) {
        Double totalSum = receipt.sumAllProductsPrices();
        print("Total sum: " + totalSum);
    }

    public void printReceipt(Receipt receipt) {
        printAllProductsOnReceipt(receipt);
        printTotalSumOfReceipt(receipt);
    }
}
