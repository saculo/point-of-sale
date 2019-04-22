package com.saculo.internship.product;

import com.saculo.internship.device.input.InputDevice;
import com.saculo.internship.device.output.OutputDevice;
import com.saculo.internship.product.model.Product;
import com.saculo.internship.product.model.Receipt;

import java.util.*;

public class ProductManager {

    private ProductRepository productRepositiory;
    private InputDevice barCodeScanner;
    private List<OutputDevice> outputDevices;
    private Receipt receipt;
    private Long receiptId;

    public ProductManager(ProductRepository productRepositiory, InputDevice barCodeScanner,
                          List<OutputDevice> outputDevices) {
        this.productRepositiory = productRepositiory;
        this.barCodeScanner = barCodeScanner;
        this.outputDevices = outputDevices;
        receiptId = 1L;
    }

    public boolean checkBarCodeIsNull(String barCode) {
        return barCode.isEmpty();
    }

    public Receipt startTransaction() {
        receipt = new Receipt(receiptId);
        return receipt;
    }

    public String scanCode(String code) {
        return scanProduct(barCodeScanner.read(code), receipt);
    }

    String scanProduct(String barCode, Receipt receipt) {
        if(checkBarCodeIsNull(barCode))
            return outputDevices.get(0).print("Invalid bar-code");
        else if(barCode.equals("exit")) {
            return displayReceipt(receipt);
        }
        else {
            return productRepositiory.get(barCode)
                    .map(receipt::addProductToReceipt)
                    .map(this::displayProductDetails)
                    .orElseGet(() -> outputDevices.get(0).print("Product not found"));
        }
    }

    String displayReceipt(Receipt receipt) {
        Double totalPrice = receipt.sumPrices();
        outputDevices.get(1).print(totalPrice.toString());
        outputDevices.get(1).print(displayAllProducts(receipt));
        receiptId++;
        return outputDevices.get(0).print(totalPrice.toString());
    }

    String displayProductDetails(Product product) {
        return outputDevices.get(0).print(product.toString());
    }

    String displayAllProducts(Receipt receipt) {
        List<Product> productsInReceipt = receipt.getProducts();
        StringBuilder stringBuilder = new StringBuilder();
        String allProducts = "";
        for(Product product : productsInReceipt)
            stringBuilder.append(displayProductDetails(product));
        allProducts = stringBuilder.toString();
        return allProducts;
    }
}
