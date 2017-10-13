package com.example.android.testing.notes.statistics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Robolectric tests for {@link StatisticsActivity}.
 */
@RunWith(RobolectricTestRunner.class)
public class StatisticsActivityTest {

    private StatisticsActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(StatisticsActivity.class);
    }

    @Test
    public void actionBar_hasStatisticsTitle() {
        assertThat(activity.getSupportActionBar().getTitle().toString(), is(equalTo("Statistics")));
    }
}
