package com.example.covidapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // This code block is for the image, that when clicked takes you back to the login page
        TextView txt2 = findViewById(R.id.textView3L);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToLoginPage = new Intent(ActivitySignUp.this, MainActivity.class);
                startActivity(backToLoginPage);
            }
        });

        // This code block is for the button that takes back to the login page if the account has been created
        ImageView imgSignUpButton = findViewById(R.id.imageViewButton2);
        //@SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imgSignUpButton = findViewById(R.id.imageViewButton2);
        imgSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToLoginPage = new Intent(ActivitySignUp.this, MainActivity.class);
                startActivity(backToLoginPage);
            }
        });

    }
}
