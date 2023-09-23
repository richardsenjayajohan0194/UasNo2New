package com.example.uasno2new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePassword extends AppCompatActivity {

    EditText txt_username, old_password, new_password;

    Button update_password, back;

    DBHelper dbHelper;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "userSignIn";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        txt_username = findViewById(R.id.txt_username);
        old_password = findViewById(R.id.txt_old_password);
        new_password = findViewById(R.id.txt_new_password);
        update_password = findViewById(R.id.btn_UpdatePassword);
        back = findViewById(R.id.btn_Back);

        dbHelper = new DBHelper(this);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        //username input field only
        txt_username.setText(username);
        txt_username.setFocusable(false);

        update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer id = sharedPreferences.getInt("id", 0);
                String old_pass = old_password.getText().toString();
                String new_pass = new_password.getText().toString();

                boolean result = dbHelper.UpdatePassword(id, old_pass, new_pass);
                if(result){
                    Toast.makeText(UpdatePassword.this, "Successfully updated password", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UpdatePassword.this, ProfileActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(UpdatePassword.this, "Failed updated password", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UpdatePassword.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatePassword.this, ProfileActivity.class);
                startActivity(intent);
            }
        });







    }
}