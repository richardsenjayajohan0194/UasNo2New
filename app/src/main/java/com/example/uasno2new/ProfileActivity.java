package com.example.uasno2new;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileActivity extends AppCompatActivity {

    TextView profile_username, profile_email;

    Button UpdateProfile, DeleteAccount, Exit, Back;

    DBHelper dbHelper;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "userSignIn";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_username = findViewById(R.id.profile_username);
        profile_email = findViewById(R.id.profile_email);
        UpdateProfile= findViewById(R.id.btn_UpdateProfile);
        DeleteAccount = findViewById(R.id.btn_DeleteAccount);
        Exit = findViewById(R.id.btn_Exit);
        Back = findViewById(R.id.btn_BackToDashboard);

        dbHelper = new DBHelper(this);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String username = sharedPreferences.getString("username","");
        String email = sharedPreferences.getString("email","");

        profile_username.setText(username);
        profile_email.setText(email);

        UpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, UpdatePassword.class);
                startActivity(intent);
            }
        });

        DeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Integer id = sharedPreferences.getInt("id", 0);
                 boolean result = dbHelper.DeleteAccount(id);
                 SharedPreferences.Editor editor = sharedPreferences.edit();
                 editor.putInt("id", 0);
                 editor.putString("username", "");
                 editor.putString("email", "");
                 editor.apply(); // Menggunakan apply() untuk menyimpan perubahan secara asinkron

                if (result) {
                    Toast.makeText(ProfileActivity.this, "Successfully deleted", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed deleted ", Toast.LENGTH_LONG).show();
                }
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id", 0);
                editor.putString("username", "");
                editor.putString("email", "");
                editor.apply(); // Menggunakan apply() untuk menyimpan perubahan secara asinkron
                Intent intent = new Intent(ProfileActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, DashbordActivity.class);
                startActivity(intent);
            }
        });


    }
}