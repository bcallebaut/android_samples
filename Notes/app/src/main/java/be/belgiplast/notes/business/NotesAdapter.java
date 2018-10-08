package be.belgiplast.notes.business;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import be.belgiplast.notes.R;

public class NotesAdapter extends RecyclerView.Adapter<NoteHolder> {
    private UserRepository repo;

    public NotesAdapter(UserRepository repo) {
        this.repo = repo;
        repo.getNotes().observeForever(new Observer<List<Note>>(){

            @Override
            public void onChanged(@Nullable List<Note> notes) {
                NotesAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);
        NoteHolder holder = new NoteHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return repo.getNotes().getValue().size();
    }
}
