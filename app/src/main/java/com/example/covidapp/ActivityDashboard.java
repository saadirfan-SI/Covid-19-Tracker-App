package com.example.covidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // News
        // This code block is for the "news" image that, when clicked, takes you to the news
        // activity page
        ImageView imgNews = findViewById(R.id.imageViewNews);
        imgNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "News image clicked");
                Intent goToNews = new Intent(ActivityDashboard.this, ActivityNews.class);
                startActivity(goToNews);
            }
        });

        // This code block is for the "news" text that, when clicked takes you to the news
        // activity page
        TextView txtNews = findViewById(R.id.textViewNews);
        txtNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "News text clicked");
                Intent toNewsPage2 = new Intent(ActivityDashboard.this, ActivityNews.class);
                startActivity(toNewsPage2);
            }
        });

        // Notifications
        // This code block is for the "notifications" image that, when clicked takes you to the
        // notification activity page
        ImageView imgNotification = findViewById(R.id.imageViewNotification);
        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Notification image clicked");
                Intent toNotificationPage = new Intent(ActivityDashboard.this, ActivityNotifications.class);
                startActivity(toNotificationPage);
            }
        });

        // This code block is for the "notifications" text that, when clicked takes you to the
        // notifications activity page
        TextView txtNotification = findViewById(R.id.textViewNotification);
        txtNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Notification text clicked");
                Intent toNotificationPage2 = new Intent(ActivityDashboard.this, ActivityNotifications.class);
                startActivity(toNotificationPage2);
            }
        });

        // Advice
        // This code block is for the "advice" image that, when clicked takes you to the advice
        // activity page
        ImageView imgAdvice = findViewById(R.id.imageView6);
        imgAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Advice image clicked");
                Intent toAdvicePage = new Intent(ActivityDashboard.this, ActivityAdvice.class);
                startActivity(toAdvicePage);
            }
        });

        // This code block is for the "advice" text that, when clicked takes you to the advice
        // activity page
        TextView txtAdvice = findViewById(R.id.textViewCovidAdvice);
        txtAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Advice text clicked");
                Intent toAdvicePage2 = new Intent(ActivityDashboard.this, ActivityAdvice.class);
                startActivity(toAdvicePage2);
            }
        });

        // Maps
        // This code block is for the "maps" image that, when clicked takes you to the maps
        // activity page
        ImageView imgMaps = findViewById(R.id.imageViewMaps);
        imgMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Maps image clicked");
                Intent toMapsPage = new Intent(ActivityDashboard.this, ActivityMaps.class);
                startActivity(toMapsPage);
            }
        });

        // This code block is for the "maps" text that, when clicked takes you to the maps
        // activity page
        TextView txtMaps = findViewById(R.id.textViewMaps);
        txtMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Maps text clicked");
                Intent toMapsPage2 = new Intent(ActivityDashboard.this, ActivityMaps.class);
                startActivity(toMapsPage2);
            }
        });

        // Inbox
        // This code block is for the "inbox" image that, when clicked takes you to the inbox
        // activity page
        ImageView imgInbox = findViewById(R.id.imageViewInbox);
        imgInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Inbox image clicked");
                Intent toInboxPage = new Intent(ActivityDashboard.this, ActivityInbox.class);
                startActivity(toInboxPage);
            }
        });

        // This code block is for the "inbox" text that, when clicked takes you to the inbox
        // activity page
        TextView txtInbox = findViewById(R.id.textViewInbox);
        txtInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Inbox text clicked");
                Intent toInboxPage2 = new Intent(ActivityDashboard.this, ActivityInbox.class);
                startActivity(toInboxPage2);
            }
        });

        // Stats
        // This code block is for the "stats" image that, when clicked takes you to the stats
        // activity page
        ImageView imgStats = findViewById(R.id.imageViewStats);
        imgStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Stats image clicked");
                Intent toStatsPage = new Intent(ActivityDashboard.this, ActivityStats.class);
                startActivity(toStatsPage);
            }
        });

        // This code block is for the "stats" text that, when clicked takes you to the
        // stats activity page
        TextView txtStats = findViewById(R.id.textViewStats);
        txtStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Stats text clicked");
                Intent toStatsPage2 = new Intent(ActivityDashboard.this, ActivityStats.class);
                startActivity(toStatsPage2);
            }
        });

        // Calendar
        // This code block is for the "calendar" image that, when clicked takes you to the
        // calendar activity page
        ImageView imgCalendar = findViewById(R.id.imageViewCalendar);
        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Calendar image clicked");
                Intent toCalendarPage = new Intent(ActivityDashboard.this, ActivityCalendar.class);
                startActivity(toCalendarPage);
            }
        });

        // This code block is for the "calendar" text that, when clicked takes you to the
        // calendar activity page
        TextView txtCalendar = findViewById(R.id.textViewCalendar);
        txtCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Calendar text clicked");
                Intent toCalendarPage2 = new Intent(ActivityDashboard.this, ActivityCalendar.class);
                startActivity(toCalendarPage2);
            }
        });

        // Settings
        // This code block is for the "settings" image that, when clicked takes you to the
        // settings activity page
        ImageView imgSettings = findViewById(R.id.imageViewSettings1);
        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Settings image clicked");
                Intent toSettingsPage = new Intent(ActivityDashboard.this, ActivitySettings.class);
                startActivity(toSettingsPage);
            }
        });

        // This code block is for the "settings" text that, when clicked takes you to the
        // settings activity page
        TextView txtSettings = findViewById(R.id.textViewSettings1);
        txtSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Settings text clicked");
                Intent toSettingsPage2 = new Intent(ActivityDashboard.this, ActivitySettings.class);
                startActivity(toSettingsPage2);
            }
        });

        // Emergency
        // This code block is for the "emergency" image that, when clicked takes you to the
        // emergency activity page
        ImageView imgEmergency = findViewById(R.id.imageViewEmergency);
        imgEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Emergency image clicked");
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:999"));
                // Starting the dialer activity
                startActivity(dialIntent);
            }
        });

        // This code block is for the "emergency" text that, when clicked takes you to your
        // phone's call manager to call 999
        TextView txtEmergency = findViewById(R.id.textViewEmergency);
        txtEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Emergency text clicked");
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:999"));
                // Starting the dialer activity
                startActivity(dialIntent);
            }
        });
    }
}
