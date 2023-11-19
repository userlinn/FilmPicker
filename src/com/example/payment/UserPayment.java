package com.example.payment;


public class UserPayment {
    private Pay payStrategy;

    public void setPaymentStrategy(Pay payStrategy) {
        this.payStrategy = payStrategy;
    }

    public void makePayment(int amount) {
        if (payStrategy != null) {
            payStrategy.pay(amount);
        } else {
            System.out.println("No payment.");
        }
    }
}