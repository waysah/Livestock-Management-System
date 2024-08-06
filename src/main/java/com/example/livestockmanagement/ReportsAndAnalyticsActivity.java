package com.example.livestockmanagement;
/*
* Generate reports oo livestock
productivity, Monitor sales
performance, track sales volume
* */

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.graphics.Canvas;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportsAndAnalyticsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MilkRecordAdapter milkRecordAdapter;
    private List<MilkRecord> milkRecordList;
    private FirebaseDatabase db;
    //TODO Print file button
    private Button printButton;
    private Button generatePDFBtn;
    private Button addMilkRecordButton;
    int pageHeight = 1120;
    int pageWidth = 792;

    private static final int PERMISSION_REQUEST_CODE = 200;

    private DatabaseReference myRef;
    List<String[]> tableData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_and_analytics);

        recyclerView = findViewById(R.id.recyclerView);
        generatePDFBtn = findViewById(R.id.printButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        milkRecordList = new ArrayList<>();
        milkRecordAdapter = new MilkRecordAdapter(milkRecordList);
        recyclerView.setAdapter(milkRecordAdapter);
        addMilkRecordButton = findViewById(R.id.addMilkRecordButton);

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("milk_records");

        //check for permissions
        //TODO Return to read about method
        if (checkPermission()) {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        fetchDataFromFirebase();
        generatePDFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    generatePDF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void fetchDataFromFirebase() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                milkRecordList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MilkRecord milkRecord = snapshot.getValue(MilkRecord.class);
                    milkRecordList.add(milkRecord);
                }
                milkRecordAdapter = new MilkRecordAdapter(milkRecordList);
                recyclerView.setAdapter(milkRecordAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //TODO HANDLE POSSIBLE ERRORS
            }
        });
    }

    private void generatePDF() {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint title = new Paint();

        //Add info on pdf, pdf-width, pdf-height, no, of pages

        PdfDocument.PageInfo milkRecordPDFInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
        //Create a start page
        PdfDocument.Page page = pdfDocument.startPage(milkRecordPDFInfo);
        Canvas canvas = (Canvas) page.getCanvas();
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        //Set text size
        title.setTextSize(15);
        title.setColor(ContextCompat.getColor(this, R.color.black));

        //Draw table headers
        drawTable(canvas, title);

        pdfDocument.finishPage(page);

        File file = new File(Environment.getExternalStorageDirectory(), "MilkRecord.pdf");
        try {
            //Write PDF file to that location
            pdfDocument.writeTo(new FileOutputStream(file));

            //Inform whether pdf is generated successfully
            Toast.makeText(ReportsAndAnalyticsActivity.this, "PDF generated successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            //TODO HANDLE ERROR
            e.printStackTrace();
        }
        //Close file after storing it
        pdfDocument.close();

    }

    private void drawTable(Canvas canvas, Paint paint) {
        int startX = 50;
        int startY = 150;
        int cellWidth = 200;
        int cellHeight = 50;

        String[] headers = {"Date", " Morning", "Afternoon", "Evening", "Total"};
        List<String[]> allData = new ArrayList<>();
        allData.add(headers);
        for (MilkRecord milkRecord : milkRecordList) {
            allData.add(new String[]{milkRecord.getDate(), String.valueOf(milkRecord.getMorning()), String.valueOf(milkRecord.getAfternoon()), String.valueOf(milkRecord.getEvening()), String.valueOf(milkRecord.getTotal())});
        }
        for (int i = 0; i < allData.size(); i++) {
            for (int j = 0; j < allData.get(i).length; j++) {
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawRect(startX + j * cellWidth, startY + i * cellHeight,
                        startX + (j + 1) * cellWidth, startY + (i + 1) * cellHeight, (androidx.compose.ui.graphics.Paint) paint);
                //Draw cell text
                paint.setStyle(Paint.Style.FILL);
                paint.setTextAlign(Paint.Align.LEFT);
                //TODO Clarify on canvas.drawText
                canvas.drawText(allData.get(i)[j], startX + j * cellWidth + 10, startY + i * cellHeight + cellHeight / 2, paint);

            }
        }

    }

    private boolean checkPermission() {

        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        //Request for permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                boolean writeStorage = grantResults[0] == PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied..", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}

