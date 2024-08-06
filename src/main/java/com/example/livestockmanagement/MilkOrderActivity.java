package com.example.livestockmanagement;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MilkOrderActivity extends AppCompatActivity {
    private TextView textViewCustomerName;
    private TextView textViewLitres;
    private TextView textViewAmountPayable;
    private CheckBox checkBoxPaid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_order);

        textViewCustomerName = findViewById(R.id.textViewCustomerName);
        textViewLitres = findViewById(R.id.textViewLitres);
        textViewAmountPayable = findViewById(R.id.textViewAmountPayable);
        checkBoxPaid = findViewById(R.id.checkBoxPaid);


    }
}
