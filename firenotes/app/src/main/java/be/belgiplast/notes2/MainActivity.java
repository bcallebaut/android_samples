package be.belgiplast.notes2;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import be.belgiplast.firebase.FirebaseApplication;
import be.belgiplast.firebase_notes.NotesDataSource;
import be.belgiplast.notes.model.Note;
import be.belgiplast.notes.ui.CreateNoteActivity;
import be.belgiplast.notes.ui.NotesView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(be.belgiplast.notes2.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(be.belgiplast.notes2.R.id.toolbar);
        setSupportActionBar(toolbar);

        model = ViewModelProviders.of(this).get(MainViewModel.class);
        model.setApplication((FirebaseApplication)getApplication());
        NotesView nv = findViewById(be.belgiplast.notes2.R.id.notes_view);
        nv.setDatasource(model.getNotesDataSource());

        FloatingActionButton fab = (FloatingActionButton) findViewById(be.belgiplast.notes2.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNoteActivity.fireCreateActivity(MainActivity.this);

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(be.belgiplast.notes2.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == be.belgiplast.notes2.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //((FirebaseApplication)getApplication()).onStart();
    }

    @Override
    protected void onStop() {
        ((FirebaseApplication)getApplication()).onStop();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Note n = CreateNoteActivity.processOnActivityResult(requestCode,resultCode,data);
        if (n != null){
            model.addNote(n);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
