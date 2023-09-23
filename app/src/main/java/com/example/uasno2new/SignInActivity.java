package com.example.uasno2new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText txt_username, txt_password;

    Button btn_SignIn;

    TextView SignUp;

    DBHelper dbHelper;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "userSignIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txt_username = findViewById(R.id.txt_username);
        txt_password= findViewById(R.id.txt_password);
        btn_SignIn = findViewById(R.id.btn_SignIn);
        SignUp = findViewById(R.id.SignUp);

        dbHelper = new DBHelper(this);


        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();

                User user = dbHelper.checkUser(username, password);
                if (user != null){
                    // Save user sign in
                    sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("id", user.get_id());
                    editor.putString("username", user.get_username());
                    editor.putString("email", user.get_email());
                    editor.apply();

                    //To page Dashboard
                    Intent intent = new Intent(SignInActivity.this, DashbordActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignInActivity.this, "Sign In Failed", Toast.LENGTH_LONG).show();
                }

            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }

}