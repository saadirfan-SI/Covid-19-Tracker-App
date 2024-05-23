package com.example.covidapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class ActivityDashboardTest {

    @Test
    public void quarantineDays_isCorrect() {
        assertEquals(14, ActivityDashboard.QUARANTINE_DAYS);
    }
}
