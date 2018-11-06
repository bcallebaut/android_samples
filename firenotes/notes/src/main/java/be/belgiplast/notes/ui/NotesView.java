package be.belgiplast.notes.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import be.belgiplast.common.DataSource;
import be.belgiplast.notes.R;
import be.belgiplast.notes.model.Note;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class NotesView extends ConstraintLayout {
    private RecyclerView rv;
    private NotesAdapter adapter;
    private DataSource<Note> noteDataSource;

    public NotesView(Context context) {
        this(context, null);
    }

    public NotesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NotesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.notesview, this, true);

        rv = findViewById(R.id.notes_recycler);
        DividerItemDecoration decoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(decoration);
        LinearLayoutManager mgr =new LinearLayoutManager(getContext());
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(mgr);
        adapter = new NotesAdapter();
        rv.setAdapter(adapter);
    }

    public void setDatasource(DataSource<Note> ds){
        adapter.bind(noteDataSource = ds);
    }

    public void createNote(){
        Intent _intent = new Intent(getContext(),CreateNoteActivity.class);
        startActivityForResult((Activity)getContext(),_intent,134,null);
    }

    protected void onActivityResult(
            int aRequestCode, int aResultCode, Intent aData
    ) {
        switch (aRequestCode) {
            case 134:
                if (aResultCode == 1){
                    Note n = new Note();
                    n.setName(aData.getStringExtra("title"));
                    n.setDescription(aData.getStringExtra("description"));

                }
                break;
        }
    }

}
