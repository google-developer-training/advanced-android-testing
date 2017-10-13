package com.example.android.testing.notes.notedetail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Robolectric tests for {@link NoteDetailActivity}.
 */
@RunWith(RobolectricTestRunner.class)
public class NoteDetailActivityTest {

    private NoteDetailActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(NoteDetailActivity.class);
    }

    @Test
    public void actionBar_hasNotesTitle() {
        assertThat(activity.getSupportActionBar().getTitle().toString(), is(equalTo("Notes")));
    }
}
