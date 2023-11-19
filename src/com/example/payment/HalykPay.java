package com.example.payment;

public class HalykPay implements Pay{
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using KaspiPay.");
    }
}
