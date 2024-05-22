package com.example.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySignUp extends AppCompatActivity {

    EditText etUser, etEmail, etPass;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etUser = findViewById(R.id.editTextTextPersonName);
        etEmail = findViewById(R.id.editTextTextPersonEmail);
        etPass = findViewById(R.id.editTextTextPasswordName);
        dbHelper = new DatabaseHelper(this); // Initialize dbHelper here

        // This code block is for the image, that when clicked takes you back to the login page
        ImageView imgSignUpButton = findViewById(R.id.imageViewButton2);

        // Set onClickListener for the text
        imgSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate back to the MainActivity (login page)
                Intent backToLoginPage = new Intent(ActivitySignUp.this, MainActivity.class);
                // Start the MainActivity
                startActivity(backToLoginPage);

                String user, email, password;
                user = etUser.getText().toString();
                email = etEmail.getText().toString();
                password = etPass.getText().toString();

                // Check if any of the fields are empty
                if (user.equals("") || email.equals("") || password.equals("")){
                    Toast.makeText(ActivitySignUp.this, "Please Fill In The Fields", Toast.LENGTH_LONG).show();
                } else {

                    if (dbHelper.checkUsername(user)){
                        Toast.makeText(ActivitySignUp.this, "User Already Exists", Toast.LENGTH_LONG).show();
                        return;
                    }

                    // Insert data into the database
                    boolean registeredSuccess = dbHelper.insertData(user, email, password);
                    if (registeredSuccess) {
                        Toast.makeText(ActivitySignUp.this, "User Registered Successfully!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ActivitySignUp.this, "User Registration Failed! Please Try Again", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        // This code block is for the button that takes back to the login page if the account has been created
        TextView txt2 = findViewById(R.id.textView3L);

        // Set onClickListener for the button
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate back to the MainActivity (login page)
                Intent backToLoginPage = new Intent(ActivitySignUp.this, MainActivity.class);
                // Start the MainActivity
                startActivity(backToLoginPage);
            }
        });
    }
}
