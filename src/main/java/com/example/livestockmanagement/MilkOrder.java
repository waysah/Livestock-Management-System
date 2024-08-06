package com.example.livestockmanagement;

public class MilkOrder {
    private final String customerName;
    private final double litres;
    private final double amountPayable;
    private final boolean paid;

    public MilkOrder(String customerName, double litres, double amountPayable, boolean paid) {
        this.customerName = customerName;
        this.litres = litres;
        this.amountPayable = amountPayable;
        this.paid = paid;
    }
    public String getCustomerName() {
        return customerName;
    }

    public double getLitres() {
        return litres;
    }

    public double getAmountPayable() {
        return amountPayable;
    }

    public boolean isPaid() {
        return paid;
    }
}
