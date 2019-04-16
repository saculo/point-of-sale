package com.saculo.internship;

import com.saculo.internship.receipt.ReceiptManager;

public class PointOfSaleRunner {
    public static void main(String[] args) {
        ReceiptManager receiptManager = new ReceiptManager();
        while(true) {
            receiptManager.startTransaction();
        }
    }
}
