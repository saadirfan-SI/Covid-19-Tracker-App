package com.example.covidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityNewsAdapter extends RecyclerView.Adapter<ActivityNewsAdapter.NewsViewHolder> {

    // JSONArray to hold the news data
    private JSONArray newsArray;

    // Constructor to initialize the adapter with a JSONArray of news data
    public ActivityNewsAdapter(JSONArray newsArray) {
        // Initialize the newsArray with the provided JSONArray
        this.newsArray = newsArray;
    }

    // Annotation whichs indicates that the method should not return null
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item_news layout to create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);

        // Return a new instance of NewsViewHolder with the inflated view
        return new NewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        try {
            // Get the JSONObject representing a single news item at the given position
            JSONObject newsItem = newsArray.getJSONObject(position);

            // Set the title TextView in the ViewHolder to the title from the news item
            holder.textViewTitle.setText(newsItem.getString("title"));

            // Set the description TextView in the ViewHolder to the excerpt from the news item
            holder.textViewDescription.setText(newsItem.getString("excerpt"));
        } catch (JSONException e) {
            // Print the stack trace if there is a JSONException
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the newsArray
        return newsArray.length();
    }

    // ViewHolder class for the RecyclerView
    static class NewsViewHolder extends RecyclerView.ViewHolder {
        // TextView for the title of the news item
        TextView textViewTitle;

        // TextView for the description of the news item
        TextView textViewDescription;

        // Constructor for the ViewHolder
        NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the TextView for the title by finding it from the itemView
            textViewTitle = itemView.findViewById(R.id.textViewTitle);

            // Initialize the TextView for the description by finding it from the itemView
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
