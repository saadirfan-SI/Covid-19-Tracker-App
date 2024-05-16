package com.example.covidapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        // This code block is for the button that takes you to the main dashboard screen
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imgButtonToDashboard = findViewById(R.id.imageView2Button);
        imgButtonToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignUpPage = new Intent(MainActivity.this,ActivityDashboard.class);
                startActivity(goToSignUpPage);
            }
        });

        // This code block is for the text that, when clicked, takes you to the create account page
        TextView txt = findViewById(R.id.textView2);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignUpPage = new Intent(MainActivity.this,ActivitySignUp.class);
                startActivity(goToSignUpPage);
            }
        });



    }
}
