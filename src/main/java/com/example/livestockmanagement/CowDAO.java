package com.example.livestockmanagement;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class CowDAO {
    private static DatabaseReference databaseReference;


    public CowDAO() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("cows");
    }

    //Update cow health status in the database
    public static void updateCowHealthStatus(String cattleNumber, Cow.HealthStatus newHealthStatus) {
        DatabaseReference cowRef = databaseReference.child(cattleNumber);
        try {
            cowRef.child("healthStatus").setValue(newHealthStatus.name());
        } catch (Exception e) {
            System.out.println("An error occurred while updating cow health status: " + e.getMessage());
        }

    }

}

