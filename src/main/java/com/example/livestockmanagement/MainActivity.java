package com.example.livestockmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livestockmanagement.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CowAdapter.OnCowClickListener {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private CowAdapter cowAdapter;
    private List<Cow> cowList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigationView = binding.navigationView;
        drawer = binding.drawerLayout;
        recyclerView = binding.recyclerView;
        toolbar = binding.appBarMain.toolbar;
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cowList = new ArrayList<>();
        cowAdapter = new CowAdapter(cowList, this);
        recyclerView.setAdapter(cowAdapter);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_animal_health, R.id.nav_milk_inventory, R.id.nav_analytics, R.id.nav_breeding,
                R.id.nav_feeding, R.id.nav_tasks).setOpenableLayout(drawer).build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        db = FirebaseFirestore.getInstance();
        fetchCowsFromFirestore();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //Get cow list from firebase database and update recycler view
    private void fetchCowsFromFirestore() {
        CollectionReference cowsRef = db.collection("cows");
        cowsRef.addSnapshotListener((value, error) -> {
            if (error != null) {
                // Handle the error
                Toast.makeText(MainActivity.this, "Error while getting cow list", Toast.LENGTH_SHORT).show();
                return;
            }
            cowList.clear();
            for (DocumentSnapshot document : value.getDocuments()) {
                String name = document.getString("name");
                String breed = document.getString("breed");
                String gender = document.getString("gender");
                String purpose = document.getString("purpose");
                String tag = document.getString("tag");
                Cow cow = new Cow(name, breed, gender, purpose, tag);
                cowList.add(cow);
            }
            cowAdapter.notifyDataSetChanged();
        });
    }

    //Expand to a detailed view of a cow
    @Override
    public void onCowClick(int position) {
        Cow selectedCow = cowList.get(position);
        Intent intent = new Intent(this, CowDetailsActivity.class);
        final Intent cow = intent.putExtra("cow", selectedCow);
        startActivity(intent);
    }
}
