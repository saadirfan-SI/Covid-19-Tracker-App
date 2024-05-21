package com.example.covidapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ActivityNews extends AppCompatActivity {

    private RecyclerView recyclerViewNews;

    // Suppress lint warning for missing inflated ID, as it is inflated dynamically
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call the superclass onCreate method
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display for this activity
        EdgeToEdge.enable(this);

        // Set the activity layout from the XML resource file
        setContentView(R.layout.activity_news);

        // Initialize the RecyclerView for displaying news items
        recyclerViewNews = findViewById(R.id.recyclerViewNews);

        // Set the layout manager for the RecyclerView
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(this));

        // Apply window insets to the main view to account for system bars (e.g., status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Get the system bars insets
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // Set padding to the main view to avoid content being covered by system bars
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Return the insets with padding applied
            return insets;
        });

        // Fetch news data from the API using an AsyncTask
        new FetchNewsTask().execute(); // depreciated function but it works somehow
    }


    public class FetchNewsTask extends AsyncTask<Void, Void, String> {

        // Background task to perform network operations in a separate thread.
        // God knows how the depreciated function below works, i saw a tutorial where the author used
        // it and so i thought hey why not use it but turns out its depreciated but again it works.
        // I promise you mark this code was inpsired by the tutorial and so i used the function
        // but again only god knows how it works and again I'm not touching it :))
        @Override
        protected String doInBackground(Void... voids) {
            try {
                // Create an OkHttpClient instance to send the HTTP request
                OkHttpClient client = new OkHttpClient();

                // Create a request to fetch news data from the API
                Request request = new Request.Builder()
                        .url("https://coronavirus-smartable.p.rapidapi.com/news/v1/global/") // URL of the API endpoint
                        .get() // HTTP GET request
                        .addHeader("X-RapidAPI-Key", "df703400d7mshfb0f875dcb78ac4p1a1794jsn918b0dc4f1b9") // Add API key header
                        .addHeader("X-RapidAPI-Host", "coronavirus-smartable.p.rapidapi.com") // Add API host header
                        .build();

                // Execute the HTTP request synchronously and get the response
                Response response = client.newCall(request).execute();

                // Check if the response is successful and the response body is not null
                if (response.isSuccessful() && response.body() != null) {
                    // Return the response body as a string (JSON data)
                    return response.body().string();
                }
            } catch (IOException e) {
                // Print the stack trace if there is an IOException
                e.printStackTrace();
            }

            // Return null if there was an error or the response was unsuccessful
            return null;
        }


        // Method called after doInBackground() method execution is finished in AsyncTask
        // Again the function works even though its depreciated, i dont know how
        @Override
        protected void onPostExecute(String jsonData) {
            // Call the superclass method
            super.onPostExecute(jsonData);

            // Check if JSON data is not null
            if (jsonData != null) {
                // Parse the JSON data and update the UI
                updateUIWithNewsData(jsonData);
            }
        }

        // Method to update the UI with news data received from JSON response
        private void updateUIWithNewsData(String jsonData) {
            try {
                // Convert the JSON string to a JSONObject
                JSONObject jsonObject = new JSONObject(jsonData);

                // Get the JSONArray containing news items from the JSONObject
                JSONArray newsArray = jsonObject.getJSONArray("news");

                // Create a new instance of ActivityNewsAdapter with the newsArray
                ActivityNewsAdapter adapter = new ActivityNewsAdapter(newsArray);

                // Set the adapter to the recyclerViewNews to display the news items
                recyclerViewNews.setAdapter(adapter);
            } catch (JSONException e) {
                // Print the stack trace if there is a JSONException
                e.printStackTrace();
            }
        }
    }
}
