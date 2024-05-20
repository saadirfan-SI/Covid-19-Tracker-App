package com.example.covidapp;

import static com.example.covidapp.ActivityDashboard.END_QUARANTINE_REQUEST_CODE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class EndQuarantineNotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "END_QUARANTINE_NOTIFICATION_CHANNEL";  // Unique identifier for the notification channel

    @Override
    public void onReceive(Context context, Intent intent) {
        // Message to be displayed in the notification
        String message = "Quarantine Over, please order a test to end quarantine. Thank you!";

        // Get the NotificationManager system service
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // For devices running Android O (API 26) and above, create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,  // The ID of the channel
                    "End Quarantine Notifications",  // The user-visible name of the channel
                    NotificationManager.IMPORTANCE_HIGH  // The importance level for the notifications posted to this channel
            );
            notificationManager.createNotificationChannel(channel);  // Create the notification channel
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification)  // The small icon to be displayed in the notification
                .setContentTitle("End of Quarantine")  // The title of the notification
                .setContentText(message)  // The content text of the notification
                .setPriority(NotificationCompat.PRIORITY_HIGH)  // The priority level for the notification
                .setAutoCancel(true);  // Automatically remove the notification when the user taps it

        // Show the notification
        notificationManager.notify(END_QUARANTINE_REQUEST_CODE, builder.build());
    }
}
