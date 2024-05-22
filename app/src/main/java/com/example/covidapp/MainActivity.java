package com.example.covidapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// This is the Login page class which handles the login functionality.

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etEmail = findViewById(R.id.editTextTextPersonName);
        etPassword = findViewById(R.id.editTextTextPasswordName);

// This code block is for the button that takes you to the main dashboard screen

        // This code line is for the emergency button if the login/create account system fails to work
        // This code block is for the button that takes you to the main dashboard screen
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView imgButtonToDashboard = findViewById(R.id.imageView3Button);

// Set onClickListener for the button
        imgButtonToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the ActivityDashboard
                Intent goToDashboard = new Intent(MainActivity.this, ActivityDashboard.class);
                // Start the ActivityDashboard
                startActivity(goToDashboard);
            }
        });

        // Set onClickListener for the button
        ImageView imgSignUpButton = findViewById(R.id.imageView2Button);

        // Set onClickListener for the text
        imgSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoggedId = dbHelper.checkUser(etEmail.getText().toString(), etPassword.getText().toString());

                if (isLoggedId) {
                    Intent toDashboard = new Intent(MainActivity.this, ActivityDashboard.class);
                    startActivity(toDashboard);
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed! Please Try again.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // This code block is for the text that, when clicked, takes you to the create account page
        TextView txt = findViewById(R.id.textView2);

        // Set onClickListener for the text
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the ActivitySignUp
                Intent goToSignUpPage = new Intent(MainActivity.this, ActivitySignUp.class);
                // Start the ActivitySignUp
                startActivity(goToSignUpPage);
            }
        });
    }
}
