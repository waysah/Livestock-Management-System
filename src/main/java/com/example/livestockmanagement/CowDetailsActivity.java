package com.example.livestockmanagement;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CowDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);

        TextView nameTextView = findViewById(R.id.cow_name);
        TextView tagTextView = findViewById(R.id.tag);
        TextView ageTextView = findViewById(R.id.age);
        TextView genderTextView = findViewById(R.id.gender);
        TextView breedTextView = findViewById(R.id.breed);
        TextView statusTextView = findViewById(R.id.status);
        TextView purposeTextView = findViewById(R.id.purpose);
        TextView weightTextView = findViewById(R.id.weight);
        //TextView milkProductionTextView = findViewById(R.id.milk_production);

        Cow cow = (Cow) getIntent().getSerializableExtra("cow");

        if (cow != null) {
            nameTextView.setText("Name: " + cow.getName());
            tagTextView.setText("Tag: " + cow.getTagNumber());
            ageTextView.setText("Age: " + cow.getAge() + " months");
            genderTextView.setText("Gender: " + cow.getGender());
            breedTextView.setText("Breed: " + cow.getBreed());
            purposeTextView.setText("Purpose: " + cow.getPurpose());
            weightTextView.setText("Weight: " + cow.getWeight() + " kg");
            //milkProductionTextView.setText("Milk Production: " + cow.getMilkProductionPerDay() + " litres/day");
        }
    }
}
