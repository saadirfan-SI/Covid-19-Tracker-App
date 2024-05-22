package com.example.covidapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;


public class ActivityDashboard extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION = 100;
    private static final int REQUEST_LOCATION_SERVICES = 100;

    public static final int QUARANTINE_DAYS = 14;
    public static final int DAILY_REMINDER_REQUEST_CODE = 100;
    public static final int END_QUARANTINE_REQUEST_CODE = 101;

    private boolean isQuarantineStarted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (savedInstanceState != null) {
            isQuarantineStarted = savedInstanceState.getBoolean("isQuarantineStarted", false);
        }

// News
        // This code block is for the "news" image which, when clicked, takes you to the news
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

        // This code block is for the "news" text which, when clicked, takes you to the news
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

// Notification
        // This code block is for the "notifications" image which, when clicked, takes you to the
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

        // This code block is for the "notifications" text which, when clicked, takes you to the
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

// Search bar
        // This code block is for the search bar and the search icon
        // Initialize the search EditText by finding its view using its ID
        EditText searchEditText = findViewById(R.id.editTextTextSearch);

        // Set OnFocusChangeListener on the EditText to handle focus change events
        searchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Check if the EditText has gained focus and its text is "Search"
                if (hasFocus && searchEditText.getText().toString().equals("Search")) {
                    // Clear the text if it is "Search"
                    searchEditText.setText("");
                }
                // Check if the EditText has lost focus and its text is empty
                else if (!hasFocus && searchEditText.getText().toString().isEmpty()) {
                    // Set the text to "Search" if it is empty
                    searchEditText.setText("Search");
                }
            }
        });


        // A TextWatcher to handle placeholder text when the text changes or if text is pasted into
        // the search bar
        searchEditText.addTextChangedListener(new TextWatcher() {
            // This method is called to notify you that somewhere within s, the text is about to be replaced with new text with a different length.
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            // This method is called to notify you that somewhere within s, the text has been replaced with new text with a different length.
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            // This method is called to notify you that somewhere within s, the text has been changed.
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Set OnTouchListener on the EditText to handle touch events
        searchEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Check if the touch event is an "UP" action (i.e., the user lifted their finger)
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Check if the touch event occurred within the drawable area of the EditText
                    if (event.getRawX() >= (searchEditText.getRight() - searchEditText.getCompoundDrawables()[2].getBounds().width())) {
                        // Retrieve the text from the EditText
                        String query = searchEditText.getText().toString();
                        // Check if the text is not equal to "Search" and is not empty
                        if (!query.equals("Search") && !query.isEmpty()) {
                            // Create an Intent to open a web browser with a Google search URL based on the query
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + query));
                            // Start the activity (open the web browser with the search query)
                            startActivity(intent);
                        }
                        // Return true to indicate that the touch event has been consumed
                        return true;
                    }
                }
                // Return false to indicate that the touch event has not been consumed
                return false;
            }
        });


// Advice
        // This code block is for the "advice" image which, when clicked, takes you to the advice
        // section of the NHS website
        ImageView imgAdvice = findViewById(R.id.imageView6);
        imgAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Advice image clicked");

                // Create an Intent to open the NHS COVID-19 advice link
                Uri uri = Uri.parse("https://www.nhs.uk/conditions/covid-19/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                // Set Chrome package name to ensure Chrome is used
                intent.setPackage("com.android.chrome");

                try {
                    // Start the activity to open the link
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Chrome is not installed so fallback to the default browser
                    intent.setPackage(null);
                    startActivity(intent);
                }
            }
        });

        // This code block is for the "advice" text which, when clicked, takes you to the advice
        // section of the NHS website
        TextView txtAdvice = findViewById(R.id.textViewCovidAdvice);
        txtAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Advice text clicked");

                // Create an Intent to open the NHS COVID-19 advice link
                Uri uri = Uri.parse("https://www.nhs.uk/conditions/covid-19/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                // Set Chrome package name to ensure Chrome is used
                intent.setPackage("com.android.chrome");

                try {
                    // Start the activity to open the link
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Chrome is not installed, fallback to default browser
                    intent.setPackage(null);
                    startActivity(intent);
                }
            }
        });

// Maps
        // This code block is for the "maps" image that, when clicked, shows nearby health facilities
        ImageView imgMaps = findViewById(R.id.imageViewMaps);
        imgMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Maps image clicked");
                requestLocationPermissionAndOpenMap();
            }
        });

        TextView txtMaps = findViewById(R.id.textViewMaps);
        txtMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Maps text clicked");
                requestLocationPermissionAndOpenMap();
            }
        });


// Covid test
        // This code block is for the "Covid test" image which, when clicked, takes you to the Amazon link to order a lateral flow test kit
                ImageView imgCovidTest = findViewById(R.id.imageViewCovidTest);
                imgCovidTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("ActivityDashboard", "Covid test image clicked");
                        Intent toCovidTestPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.co.uk/lateral-flow-test-kit/s?k=lateral+flow+test+kit"));
                        startActivity(toCovidTestPage);
                    }
                });

        // This code block is for the "Covid test" text which, when clicked, takes you to the Amazon link to order a lateral flow test kit
                TextView txtCovidTest = findViewById(R.id.textViewCovidTest);
                txtCovidTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("ActivityDashboard", "Covid test text clicked");
                        Intent toCovidTestPage2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.co.uk/lateral-flow-test-kit/s?k=lateral+flow+test+kit"));
                        startActivity(toCovidTestPage2);
                    }
                });

// Stats
        // This code block is for the "stats" image which, when clicked, takes you to the stats
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

        // This code block is for the "stats" text which, when clicked, takes you to the
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
        // This code block is for the "calendar" text image, when clicked, takes you to your phone's
        // in built calendar app
        ImageView imgCalendar = findViewById(R.id.imageViewCalendar);
        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Calendar image clicked");

                // Intent to open the phone's built-in calendar app
                Intent calendarIntent = new Intent(Intent.ACTION_MAIN);
                calendarIntent.addCategory(Intent.CATEGORY_APP_CALENDAR);

                try {
                    startActivity(calendarIntent);
                } catch (ActivityNotFoundException e) {
                    Log.e("ActivityDashboard", "No calendar app found", e);
                    Toast.makeText(ActivityDashboard.this, "No calendar app found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // This code block is for the "calendar" text which, when clicked, takes you to your phone's
        // in built calendar app
        TextView txtCalendar = findViewById(R.id.textViewCalendar);
        txtCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Calendar text clicked");
                Intent toCalendarPage2 = new Intent(ActivityDashboard.this, ActivityCalendar.class);
                startActivity(toCalendarPage2);
            }
        });

        // Start Quarantine
        Button buttonStartQuarantine = findViewById(R.id.buttonQuarantine);
        buttonStartQuarantine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ActivityDashboard", "Start Quarantine button clicked");
                startQuarantinePeriod();
                scheduleDailyReminders();
                scheduleEndOfQuarantineReminder();
                isQuarantineStarted = true;
            }
        });

//Settings
        // This code block is for the "settings" image which, when clicked, takes you to the
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

        // This code block is for the "settings" text which, when clicked, takes you to the
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
        // This code block is for the "emergency" image which, when clicked, takes you to the
        // phone's call manager to call 999
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

        // This code block is for the "emergency" text which, when clicked, takes you to your
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

// Handles quarantine period management and schedules reminders

    private void startQuarantinePeriod() {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_YEAR, QUARANTINE_DAYS); // Add 14 days to start date

        // Create an intent to insert an event
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, "Quarantine Period")
                .putExtra(CalendarContract.Events.DESCRIPTION, "14-day quarantine period")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Home")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("ActivityDashboard", "No calendar app found", e);
            Toast.makeText(ActivityDashboard.this, "No calendar app found", Toast.LENGTH_SHORT).show();
        }
        // Notify that quarantine has started
        Toast.makeText(this, "Quarantine has started. Notifications scheduled.", Toast.LENGTH_SHORT).show();
    }

    private void scheduleDailyReminders() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 9); // Set the time to 9 AM
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        for (int i = 0; i < QUARANTINE_DAYS; i++) {
            Intent intent = new Intent(this, QuarantineNotificationReceiver.class);
            intent.putExtra("days_left", QUARANTINE_DAYS - i);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, DAILY_REMINDER_REQUEST_CODE + i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

    // Method to schedule the reminder for the end of quarantine
    private void scheduleEndOfQuarantineReminder() {
        // Get the system's AlarmManager service
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Get the current time and create a Calendar instance
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        // Add the duration of quarantine to the current time
        calendar.add(Calendar.DAY_OF_YEAR, QUARANTINE_DAYS);

        // Set the time for the reminder to 9 AM
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Create an Intent to broadcast the reminder
        Intent intent = new Intent(this, EndQuarantineNotificationReceiver.class);

        // Create a PendingIntent for the broadcast
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, END_QUARANTINE_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Schedule the alarm to trigger at the specified time
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the 'isQuarantineStarted' variable in the bundle
        outState.putBoolean("isQuarantineStarted", isQuarantineStarted);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the state of the 'isQuarantineStarted' variable from the saved instance state
        isQuarantineStarted = savedInstanceState.getBoolean("isQuarantineStarted", false);
    }


// For Maps to handle permissions and launching Google Maps for nearby health facilities
    // Method to request location permission and open map
    
    private void requestLocationPermissionAndOpenMap() {
        // Check if the location permission is not granted
        if (ContextCompat.checkSelfPermission(ActivityDashboard.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, ask for permission
            ActivityCompat.requestPermissions(ActivityDashboard.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            // Permission is already granted, check if location services are enabled
            checkLocationServicesAndOpenMap();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Check if the request code matches the location permission request code
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            // Check if the permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, check if location services are enabled
                checkLocationServicesAndOpenMap();
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "Location permission denied. Map functionality disabled.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to check if location services are enabled and open map
    private void checkLocationServicesAndOpenMap() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            // Location services are not enabled, prompt the user to enable them
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, REQUEST_LOCATION_SERVICES);
        } else {
            // Location services are enabled, open map
            openMap();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check if the request code matches the location services request code
        if (requestCode == REQUEST_LOCATION_SERVICES) {
            // Check if location services are enabled now
            checkLocationServicesAndOpenMap();
        }
    }

    // Method to open Google Maps with a search query for nearby health facilities
    private void openMap() {
        // Intent to open Google Maps with a search query for nearby health facilities
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=health+facilities+near+me");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        // Check if there is an app that can handle the intent
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // Fallback to open in a web browser
            Uri webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=health+facilities+near+me");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
            startActivity(webIntent);
        }
    }
}

