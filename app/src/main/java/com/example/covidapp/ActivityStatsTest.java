package com.example.covidapp;

import org.json.JSONException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ActivityStatsTest {

    @Test
    public void testFetchData() throws JSONException {
        ActivityStats activityStats = new ActivityStats();
        activityStats.fetchData();
        // Simulate a delay to allow the AsyncTask to complete (not ideal, but sufficient for demonstration)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotNull(activityStats.tvTotalCases);
        assertNotNull(activityStats.tvTotalDeaths);
        assertNotNull(activityStats.tvTotalTests);
        // Validate that the TextViews are updated with non-empty strings
        assertNotEmpty(activityStats.tvTotalCases.getText().toString());
        assertNotEmpty(activityStats.tvTotalDeaths.getText().toString());
        assertNotEmpty(activityStats.tvTotalTests.getText().toString());
    }

    private void assertNotEmpty(String value) {
        assertNotNull(value);
        assertEquals(false, value.isEmpty());
    }
}
