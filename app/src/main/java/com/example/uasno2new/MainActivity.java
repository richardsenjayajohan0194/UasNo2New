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

public class MainActivity extends AppCompatActivity {

    EditText txt_username, txt_email, txt_password, txt_cofirmpass;

    TextView SignUp;

    Button btnSignUp;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_username = findViewById(R.id.txt_username);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        txt_cofirmpass = findViewById(R.id.txt_cofirmpass);
        SignUp = findViewById(R.id.SignUp);
        btnSignUp = findViewById(R.id.btn_SignUp);


        dbHelper = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txt_username.getText().toString();
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();
                String confirmpass = txt_cofirmpass.getText().toString();

                if(username.equals("") || email.equals("") || password.equals("") || confirmpass.equals("")){
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {

                    if(password.equals(confirmpass)){
                        //Proceed with sign up or registration
                        if (dbHelper.checkUsername(username)){
                            Toast.makeText(MainActivity.this, "User Allredy Exists", Toast.LENGTH_LONG).show();
                        }
                        boolean signupSuccess = dbHelper.insetData(username, email, password);
                        Toast.makeText(MainActivity.this, "signup" + signupSuccess, Toast.LENGTH_LONG).show();
                        if(signupSuccess){
                            User user = new User(null, username, email, password);
                            Toast.makeText(MainActivity.this, "User Sign Up Successfuly", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "User Sign Up Failed", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                    }
                }





            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);

            }
        });

    }

}