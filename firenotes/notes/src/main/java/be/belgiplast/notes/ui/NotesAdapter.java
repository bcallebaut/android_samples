package be.belgiplast.notes.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import be.belgiplast.common.DataSource;
import be.belgiplast.notes.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private DataSource<Note> datasource;
    private LiveData<List<Note>> liveData;

    public NotesAdapter(){
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note());
        notes.add(new Note());
        notes.add(new Note());

        liveData = new MutableLiveData<>();
        ((MutableLiveData<List<Note>>)liveData).setValue(notes);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return NoteViewHolder.create(parent);
    }

    @Override
    public int getItemCount() {
        return liveData.getValue().size();
    }

    private Note getItem(int index){
        return liveData.getValue().get(index);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int position) {
        Note repoItem = getItem(position);
        if (repoItem != null) {
            ((NoteViewHolder)noteViewHolder).bind(repoItem);
        }
    }

    public void bind(DataSource<Note> datasource){
        this.datasource = datasource;
        liveData = this.datasource.getSource();
        liveData.observeForever(note ->{
            NotesAdapter.this.notifyDataSetChanged();
        });
    }
}
