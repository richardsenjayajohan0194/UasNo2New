package com.example.uasno2new;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetail extends AppCompatActivity {

    ImageView back, view_profile;

    Button buy;

    ImageView Image;

    TextView NameAndSize, Price;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

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

        int imageId = getIntent().getIntExtra("ImageSource", 0);
        Image.setImageResource(imageId);
        NameAndSize.setText(getIntent().getExtras().getString("NameAndSize"));

        // Mengambil data dari Intent
        String priceString = getIntent().getExtras().getString("Price");

        // Mengubah string harga menjadi angka
        int price = Integer.parseInt(priceString);

        // Mengubah angka menjadi format rupiah
        String hargaRupiah = "Rp" + String.format("%,d", price).replace(",", ".") + ",00";

        // Menampilkan harga dalam format rupiah
        Price.setText(hargaRupiah);

    }
}