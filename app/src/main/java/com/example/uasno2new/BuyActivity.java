package com.example.uasno2new;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class BuyActivity extends AppCompatActivity {

    Button Buy;

    ImageView Succes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Succes = findViewById(R.id.animationSuccess);
        Buy = findViewById(R.id.btn_buy);

        //Animation Success
        Glide.with(BuyActivity.this).load(R.drawable.animation).into(Succes);

        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyActivity.this, DashbordActivity.class);
                startActivity(intent);
            }
        });



    }
}