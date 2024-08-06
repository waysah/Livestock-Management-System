package com.example.livestockmanagement;

public class MilkRecord {
    private String date;
    private double morning;
    private double afternoon;
    private double evening;
    private double total;

    public MilkRecord() {
    }

    public MilkRecord(String date, double morning, double afternoon, double evening, double total) {
        this.date = date;
        this.morning = morning;
        this.afternoon = afternoon;
        this.evening = evening;
        calculateTotal();

    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public double getMorning() {
        return morning;
    }
    public void setMorning(double morning) {
        this.morning = morning;
        calculateTotal();
    }

    public double getAfternoon() {
        return afternoon;
    }
    public void setAfternoon(double afternoon) {
        this.afternoon = afternoon;
        calculateTotal();
    }
    public double getEvening() {
        return evening;
    }

    public void setEvening(){
        this.evening = evening;
        calculateTotal();
    }
    public double getTotal() {
        return total;
    }
    private void calculateTotal() {
        this.total = this.morning + this.afternoon + this.evening;
    }

}
