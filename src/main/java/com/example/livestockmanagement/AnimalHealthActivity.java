package com.example.livestockmanagement;
/*
* Track Vaccination, medication,
health records, medication,
health records, schedule
appointment with vetenary
*/
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimalHealthActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_health);

        TextView textAnimalHealth = findViewById(R.id.textAnimalHealth);
        TextView textAddTreatmentRecord = findViewById(R.id.textAddTreatmentRecord);
        TextView textAnimalHeath = findViewById(R.id.textAnimalHeath);


    }
}
