package com.example.uasno2new;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.uasno2new.Model.Adapter.ItemAdapter;
import com.example.uasno2new.Model.ItemList;

import java.util.ArrayList;

public class DashbordActivity extends AppCompatActivity {

    ImageView view_history, view_profile;

    RecyclerView  recyclerViewItem;

    DBHelper dbHelper;

    ArrayList<ItemList> listItem;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        dbHelper = new DBHelper(this);
        sharedPreferences = getSharedPreferences("itemDatabase", MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("isInserted", false)){
            listItem = new ArrayList<ItemList>();
            listItem.add(new ItemList(1, R.drawable.product1, "T-Shirt PERU Size S", 10000));
            listItem.add(new ItemList(2, R.drawable.product2, "T-Shirt Colaboration Marvel Studio", 250000));
            listItem.add(new ItemList(4, R.drawable.product3, "T-Shirt Tron Size XXL", 90000));
            listItem.add(new ItemList(5, R.drawable.product4, "T-SHIRT Sequin Man Size L", 100000));
            for(int i = 0 ; i < listItem.size() ; i++){
                dbHelper.insertItem(listItem.get(i));
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isInserted", true);
            editor.apply();
        }


        view_history = findViewById(R.id.view_history);
        view_profile = findViewById(R.id.view_profile);
        recyclerViewItem = findViewById(R.id.recyclerViewItem);
        listItem = dbHelper.getItems();

        ItemAdapter adapter = new ItemAdapter(listItem);
        recyclerViewItem.setAdapter(adapter);
        recyclerViewItem.setLayoutManager(new GridLayoutManager(this, 2));

        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashbordActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

     }
    public void displayToast(String toast){
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    };

}