package com.example.livestockmanagement;

public class VaccinationRecord {
    private String cowName;
    private String tag;
    private final String vaccineName;
    private final String batchNumber;
    private final String manufacturer;
    private final String dosage;
    private final String administeredBy;
    private final String date;
    private final String notes;

    public VaccinationRecord(String cowId, String vaccineName, String dosage, String date, String batchNumber, String manufacturer, String administeredBy, String notes) {
        this.cowName = cowName;
        this.tag = tag;
        this.vaccineName = vaccineName;
        this.dosage = dosage;
        this.date = date;
        this.batchNumber = batchNumber;
        this.manufacturer = manufacturer;
        this.administeredBy = administeredBy;
        this.notes = notes;
    }
}
