package com.example.android.testing.notes.notes;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.example.android.testing.notes.R;
import com.example.android.testing.notes.addnote.AddNoteActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startVisibleFragment;


/**
 * Robolectric tests for {@link NotesActivity}.
 */
@RunWith(RobolectricTestRunner.class)
public class NotesFragmentTest {
    private NotesFragment fragment;

    @Before
    public void setUp() {
        fragment = NotesFragment.newInstance();
        startVisibleFragment(fragment, NotesActivity.class, R.id.contentFrame);
    }

    @Test
    public void addNoteFabClick_startAddNodeActivityIntent() {
        FloatingActionButton fab = fragment.getActivity().findViewById(R.id.fab_add_notes);
        fab.performClick();
        Intent expectedIntent = new Intent(fragment.getContext(), AddNoteActivity.class);
        ShadowActivity.IntentForResult result = shadowOf(fragment.getActivity())
                .getNextStartedActivityForResult();
        assertThat(result.intent.getComponent(), equalTo(expectedIntent.getComponent()));
    }

    @Test
    public void addNoteAndAccept_showToast() {
        FloatingActionButton fab = fragment.getActivity().findViewById(R.id.fab_add_notes);
        fab.performClick();
        shadowOf(fragment.getActivity()).receiveResult(
                new Intent(fragment.getContext(), AddNoteActivity.class),
                Activity.RESULT_OK,
                new Intent());
        TextView snackbarText = fragment.getActivity().getWindow().findViewById(R.id.snackbar_text);
        assertThat(snackbarText, is(notNullValue()));
        assertThat(snackbarText.getText().toString(), is("Note saved"));
    }

    @Test
    public void addNoteAndCancel_showNoToast() {
        FloatingActionButton fab = fragment.getActivity().findViewById(R.id.fab_add_notes);
        fab.performClick();
        shadowOf(fragment.getActivity()).receiveResult(
                new Intent(fragment.getContext(), AddNoteActivity.class),
                Activity.RESULT_CANCELED,
                new Intent());
        View snackbarText = fragment.getView().findViewById(R.id.snackbar_text);
        assertThat(snackbarText, is(nullValue()));
    }
}
