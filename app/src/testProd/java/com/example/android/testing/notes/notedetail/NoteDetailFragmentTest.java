package com.example.android.testing.notes.notedetail;

import android.content.Intent;
import android.widget.TextView;

import com.example.android.testing.notes.Injection;
import com.example.android.testing.notes.R;
import com.example.android.testing.notes.data.Note;
import com.example.android.testing.notes.data.NotesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Robolectric tests for {@link NoteDetailFragment}.
 */
@RunWith(RobolectricTestRunner.class)
public class NoteDetailFragmentTest {
    private NoteDetailFragment fragment;
    private NoteDetailActivity activity;

    @Before
    public void setUp() {
        Note newNote = new Note("my note", "my description", null);
        String newId = newNote.getId();

        NotesRepository repository = Injection.provideNotesRepository();
        repository.saveNote(newNote);

        Intent startIntent = new Intent();
        startIntent.putExtra(NoteDetailActivity.EXTRA_NOTE_ID, newId);
        activity = Robolectric.buildActivity(NoteDetailActivity.class, startIntent).setup().get();
        fragment = (NoteDetailFragment) activity.getSupportFragmentManager().getFragments().get(0);
    }

    @Test
    public void testNoteTitleAndDescription_showLoading() {
        TextView mDetailTitle = fragment.getActivity().findViewById(R.id.note_detail_title);
        TextView mDetailDescription = fragment.getActivity().findViewById(R.id.note_detail_description);
        assertThat(mDetailTitle.getText().toString(), is(equalTo("")));
        assertThat(mDetailDescription.getText().toString(), is(equalTo("LOADING")));
    }

    @Test
    public void testNoteTitleAndDescription_showNote() {
        // Complete background task to load the note.
        Robolectric.flushForegroundThreadScheduler();
        TextView mDetailTitle = fragment.getActivity().findViewById(R.id.note_detail_title);
        TextView mDetailDescription = fragment.getActivity().findViewById(R.id.note_detail_description);
        assertThat(mDetailTitle.getText().toString(), is(equalTo("my note")));
        assertThat(mDetailDescription.getText().toString(), is(equalTo("my description")));
    }
}
