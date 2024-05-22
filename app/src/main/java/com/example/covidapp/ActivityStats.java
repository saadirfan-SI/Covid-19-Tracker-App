package com.example.covidapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// This class handles the Covid Stats that gets data using an API

public class ActivityStats extends AppCompatActivity {

    // TextView to display the total number of cases
    private TextView tvTotalCases;

    // TextView to display the total number of deaths
    private TextView tvTotalDeaths;

    // TextView to display the total number of tests
    private TextView tvTotalTests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enables edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the content view to the activity_stats layout
        setContentView(R.layout.activity_stats);

        // Initialize TextViews for displaying total cases, deaths, and tests
        tvTotalCases = findViewById(R.id.tvTotalCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTotalTests = findViewById(R.id.tvTotalTests);

        // Fetch data from the API asynchronously
        // Aparantely the execute function is depreciated but the code somehow works so im not touching it :)
        new FetchData().execute();

        // Apply window insets listener to adjust padding for system bars (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Get insets for system bars
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Set padding for the view based on system bars insets
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    // AsyncTask to fetch data from the API
    private class FetchData extends AsyncTask<Void, Void, String> {

        // This method runs in the background on a separate thread
        @Override
        protected String doInBackground(Void... voids) {
            // Create an instance of OkHttpClient to send the request
            OkHttpClient client = new OkHttpClient();

            // Build the HTTP request to fetch data from the API
            Request request = new Request.Builder()
                    .url("https://covid-193.p.rapidapi.com/statistics?country=UK") // API endpoint
                    .get() // HTTP GET method
                    .addHeader("X-RapidAPI-Key", "df703400d7mshfb0f875dcb78ac4p1a1794jsn918b0dc4f1b9") // API key header
                    .addHeader("X-RapidAPI-Host", "covid-193.p.rapidapi.com") // API host header
                    .build();

            try {
                // Execute the request and get the response
                Response response = client.newCall(request).execute();
                // Return the response body as a string
                return response.body().string();
            } catch (IOException e) {
                // Print stack trace if there's an exception
                e.printStackTrace();
            }
            // Return null if the request fails
            return null;
        }

        @Override
        protected void onPostExecute(String responseString) { // The Post Execute function is depreciated but it works so im not touching it :)
            super.onPostExecute(responseString);
            // Check if the responseString is not null
            if (responseString != null) {
                try {
                    // Parse the response string into a JSONObject
                    JSONObject jsonObject = new JSONObject(responseString);
                    // Extract the first object from the "response" array
                    JSONObject response = jsonObject.getJSONArray("response").getJSONObject(0);

                    // Extracting data from the response
                    String totalCases = response.getJSONObject("cases").getString("total");
                    String totalDeaths = response.getJSONObject("deaths").getString("total");
                    String totalTests = response.getJSONObject("tests").getString("total");

                    // Set the extracted data to the respective TextViews
                    tvTotalCases.setText("Total Cases: " + totalCases);
                    tvTotalDeaths.setText("Total Deaths: " + totalDeaths);
                    tvTotalTests.setText("Total Tests: " + totalTests);

                } catch (JSONException e) {
                    // Print stack trace if there's a JSON exception
                    e.printStackTrace();
                }
            }
        }
    }
}