package com.example.livestockmanagement;

public class TreatmentRecord {
    private String cowName;
    private String tag;
    private String treatment;
    private String disease;
    private final String dosage;
    private final String administeredBy;
    private final String date;
    private final String notes;

    public TreatmentRecord(String cowId, String treatmentName, String dosage, String date, String administeredBy, String reason, String notes) {
        this.cowName = cowName;
        this.treatment= treatment;
        this.dosage = dosage;
        this.date = date;
        this.administeredBy = administeredBy;
        this.disease = disease;
        this.notes = notes;
    }

}
