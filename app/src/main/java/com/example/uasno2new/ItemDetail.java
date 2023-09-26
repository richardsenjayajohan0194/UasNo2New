package com.example.uasno2new;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uasno2new.Model.ItemHistory;
import com.example.uasno2new.Model.ItemList;

import java.time.LocalDate;
import java.time.LocalTime;

public class ItemDetail extends AppCompatActivity {

    ImageView back, view_profile;

    Button buy;

    ImageView Image;

    TextView NameAndSize, Price;

    DBHelper dbHelper;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "userSignIn";


    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        dbHelper = new DBHelper(this);

        back = findViewById(R.id.btn_ToDashboard);
        view_profile = findViewById(R.id.view_profile);
        Image = findViewById(R.id.imageItem);
        NameAndSize = findViewById(R.id.txtItemName);
        Price = findViewById(R.id.txtItemPrice);
        buy = findViewById(R.id.btn_buy);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetail.this, DashbordActivity.class);
                startActivity(intent);
            }
        });

        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetail.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        ItemList item = (ItemList) getIntent().getParcelableExtra("item");

        Image.setImageResource(item.get_imageSource());
        NameAndSize.setText(item.get_nameItemAndSize());
        Price.setText("Rp" + String.format("%,d", item.get_price()).replace(",", ".") + ",00");

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetail.this, BuyActivity.class);
                sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                Integer IdUser = sharedPreferences.getInt("id", 0);
                dbHelper.insertHistory(new ItemHistory(0, IdUser, item, LocalDate.now(), LocalTime.now()));
                startActivity(intent);
            }
        });

    }
}