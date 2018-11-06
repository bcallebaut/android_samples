package be.belgiplast.notes.ui;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import be.belgiplast.notes.R;
import be.belgiplast.notes.business.Note;

public class AddNoteActivity extends AppCompatActivity {

    private Intent result;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        result = new Intent();
        setContentView(R.layout.activity_add_note);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(myToolbar);

        ((NoteItemView)findViewById(R.id.note_view)).getNote().observeForever(new Observer<Note>(){

            @Override
            public void onChanged(@Nullable Note note) {
                AddNoteActivity.this.note = note;
                result.putExtra("name",note.getName());
                result.putExtra("description",note.getDescription());
                result.putExtra("timestamp",note.getTimestamp());
                result.putExtra("modification",note.getModification());
                result.putExtra("id",note.getId());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addnote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.note_save:
                note = ((NoteItemView)findViewById(R.id.note_view)).getNote().getValue();
                result.putExtra("name",note.getName());
                result.putExtra("description",note.getDescription());
                result.putExtra("timestamp",note.getTimestamp());
                result.putExtra("modification",note.getModification());
                result.putExtra("id",note.getId());
                setResult(0,result);
                finish();
                return true;

            case R.id.note_cancel:
                setResult(0,result);
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
