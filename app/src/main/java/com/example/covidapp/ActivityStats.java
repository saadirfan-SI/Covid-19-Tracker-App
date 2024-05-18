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

public class ActivityStats extends AppCompatActivity {

    private TextView tvTotalCases;
    private TextView tvTotalDeaths;
    private TextView tvTotalTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stats);

        tvTotalCases = findViewById(R.id.tvTotalCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTotalTests = findViewById(R.id.tvTotalTests);

        // Fetch data from API
        new FetchData().execute();

        // Apply window insets listener to handle system bars insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // AsyncTask to fetch data from the API
    private class FetchData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://covid-193.p.rapidapi.com/statistics?country=UK")
                    .get()
                    .addHeader("X-RapidAPI-Key", "df703400d7mshfb0f875dcb78ac4p1a1794jsn918b0dc4f1b9")
                    .addHeader("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String responseString) {
            super.onPostExecute(responseString);
            if (responseString != null) {
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    JSONObject response = jsonObject.getJSONArray("response").getJSONObject(0);

                    // Extracting data from the response
                    String totalCases = response.getJSONObject("cases").getString("total");
                    String totalDeaths = response.getJSONObject("deaths").getString("total");
                    String totalTests = response.getJSONObject("tests").getString("total");

                    // Set the text to the TextViews
                    tvTotalCases.setText("Total Cases: " + totalCases);
                    tvTotalDeaths.setText("Total Deaths: " + totalDeaths);
                    tvTotalTests.setText("Total Tests: " + totalTests);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
