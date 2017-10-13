package com.example.android.testing.notes.addnote;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/** Robolectric tests for {@link AddNoteActivity}. */
@RunWith(RobolectricTestRunner.class)
public class
AddNoteActivityTest {

    private AddNoteActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(AddNoteActivity.class);
    }

    @Test
    public void actionBar_hasNewNoteTitle() {
        assertThat(activity.getSupportActionBar().getTitle().toString(), is(equalTo("New Note")));
    }
}
