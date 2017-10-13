package com.example.android.testing.notes.addnote;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.android.testing.notes.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/** Robolectric tests for {@link AddNoteFragment}. */
@RunWith(RobolectricTestRunner.class)
public class AddNoteFragmentTest {

    private AddNoteFragment fragment;

    @Before
    public void setUp() {
        // AddNoteActivity.onCreate calls AddNoteFragment.newInstance().
        fragment = (AddNoteFragment) Robolectric.setupActivity(AddNoteActivity.class)
                .getSupportFragmentManager().getFragments().get(0);
    }

    @Test
    public void addEmptyNote_showCannotBeEmptyToast() {
        View fab = fragment.getActivity().findViewById(R.id.fab_add_notes);
        fab.performClick();

        TextView snackbarText = fragment.getActivity().getWindow().findViewById(R.id.snackbar_text);
        assertThat(snackbarText.getText().toString(), is("Notes cannot be empty"));
    }

    @Test
    public void addNoteWithTitleAndDescription_showNoteInList() {
        View fab = fragment.getActivity().findViewById(R.id.fab_add_notes);

        TextView title = (TextView) fragment.getActivity().findViewById(R.id.add_note_title);
        TextView description = (TextView) fragment.getActivity().findViewById(R.id.add_note_description);

        title.setText("My note");
        description.setText("My note description");

        fab.performClick();

        assertThat(Shadows.shadowOf(fragment.getActivity()).getResultCode(), is(equalTo(
                Activity.RESULT_OK)));
    }
}
