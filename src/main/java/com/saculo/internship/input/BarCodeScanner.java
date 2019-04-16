package com.saculo.internship.input;

import com.saculo.internship.product.ProductRepositiory;

import java.util.Scanner;

public class BarCodeScanner {

    private Scanner scan = new Scanner(System.in);
    private ProductRepositiory productRepositiory;

    public BarCodeScanner(ProductRepositiory productRepositiory) {
        this.productRepositiory = productRepositiory;
    }

    public String scanBarCode() {
        return scan.nextLine();
    }

    public boolean isProductInDatabase(String barCode) {
        return productRepositiory.exists(barCode);
    }

    public boolean isBarCodeNull(String barCode) {
        return barCode.isEmpty();
    }
}
