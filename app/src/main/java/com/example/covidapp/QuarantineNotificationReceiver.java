package com.example.covidapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;

// This class handles the daily notifications for when you are in quarantine

public class QuarantineNotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "QUARANTINE_NOTIFICATION_CHANNEL";  // Unique identifier for the notification channel

    @Override
    public void onReceive(Context context, Intent intent) {
        // Retrieve the number of days left in quarantine from the intent's extras
        int daysLeft = intent.getIntExtra("days_left", 0);

        // Message to be displayed in the notification
        String message = "You have " + daysLeft + " days left in quarantine.";

        // Get the NotificationManager system service
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // For devices running Android O (API 26) and above, create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,  // The ID of the channel
                    "Quarantine Notifications",  // The user-visible name of the channel
                    NotificationManager.IMPORTANCE_HIGH  // The importance level for the notifications posted to this channel which is HIGH
            );
            notificationManager.createNotificationChannel(channel);  // Create the notification channel
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification)  // The small icon to be displayed in the notification which is not working properly
                .setContentTitle("Quarantine Reminder")  // The title of the notification
                .setContentText(message)  // The content text of the notification
                .setPriority(NotificationCompat.PRIORITY_HIGH)  // The priority level for the notification
                .setAutoCancel(true);  // Automatically remove the notification when the user taps it

        // Show the notification
        notificationManager.notify(daysLeft, builder.build());
    }
}
