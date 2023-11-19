package com.example.payment;

public class KaspiPay implements Pay{
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using KaspiPay.");
    }
}