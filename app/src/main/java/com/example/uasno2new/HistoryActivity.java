package com.example.uasno2new;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasno2new.Model.Adapter.HistoryAdapter;
import com.example.uasno2new.Model.ItemHistory;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ImageView backToDashboard, viewToProfile;

    TextView search;

    DBHelper dbHelper;

    ArrayList<ItemHistory> arrHistory;

    RecyclerView recyclerViewHistory;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "userSignIn";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dbHelper = new DBHelper(this);

        backToDashboard = findViewById(R.id.btn_ToDashboard);
        viewToProfile = findViewById(R.id.btn_viewToProfile);
        search = findViewById(R.id.txt_search);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        Integer IdUser = sharedPreferences.getInt("id", 0);

        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
        arrHistory = dbHelper.get_History(IdUser);

        HistoryAdapter adapter = new HistoryAdapter(arrHistory);
        recyclerViewHistory.setAdapter(adapter);
        recyclerViewHistory.setLayoutManager(new GridLayoutManager(this,1));

        backToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, DashbordActivity.class);
                startActivity(intent);
            }
        });

        viewToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });



    }
}