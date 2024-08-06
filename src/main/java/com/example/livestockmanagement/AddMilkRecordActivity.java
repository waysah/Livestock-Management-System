package com.example.livestockmanagement;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddMilkRecordActivity extends AppCompatActivity {

    private EditText dateEditText;
    private EditText morningEditText;
    private EditText afternoonEditText;
    private EditText eveningEditText;
    private EditText totalEditText;
    private Button saveButton;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_milk_record);

        dateEditText = findViewById(R.id.dateEditText);
        morningEditText = findViewById(R.id.morningEditText);
        afternoonEditText = findViewById(R.id.afternoonEditText);
        eveningEditText = findViewById(R.id.eveningEditText);
        totalEditText = findViewById(R.id.totalEditText);
        saveButton = findViewById(R.id.saveButton);

        db = FirebaseFirestore.getInstance();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMilkRecord();
            }
        });
    }

    private void saveMilkRecord() {
        String date = dateEditText.getText().toString().trim();
        String morning = morningEditText.getText().toString().trim();
        String afternoon = afternoonEditText.getText().toString().trim();
        String evening = eveningEditText.getText().toString().trim();
        String total = totalEditText.getText().toString().trim();

        if (TextUtils.isEmpty(date) || TextUtils.isEmpty(morning) || TextUtils.isEmpty(afternoon)
                || TextUtils.isEmpty(evening) || TextUtils.isEmpty(total)) {
            Toast.makeText(AddMilkRecordActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        double morningMilk = Double.parseDouble(morning);
        double afternoonMilk = Double.parseDouble(afternoon);
        double eveningMilk = Double.parseDouble(evening);
        double totalMilk = Double.parseDouble(total);

        Map<String, Object> milkRecord = new HashMap<>();
        milkRecord.put("date", date);
        milkRecord.put("morning", morningMilk);
        milkRecord.put("afternoon", afternoonMilk);
        milkRecord.put("evening", eveningMilk);
        milkRecord.put("total", totalMilk);

        db.collection("milk_records").add(milkRecord)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddMilkRecordActivity.this, "Milk record saved", Toast.LENGTH_SHORT).show();
                            clearFields();
                        } else {
                            Toast.makeText(AddMilkRecordActivity.this, "Failed to save record", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void clearFields() {
        dateEditText.setText("");
        morningEditText.setText("");
        afternoonEditText.setText("");
        eveningEditText.setText("");
        totalEditText.setText("");
    }
}