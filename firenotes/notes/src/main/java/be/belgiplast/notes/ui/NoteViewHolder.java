package be.belgiplast.notes.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import be.belgiplast.notes.R;
import be.belgiplast.notes.model.Note;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private NoteItemView note;

    public NoteViewHolder(final View view) {
        super(view);
        note = (NoteItemView)view;
        note.getNote().observeForever(note -> {});
    }

    public void bind(Note repo) {
        note.bind(repo);
    }

    public static NoteViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view_item, parent, false);
        view.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
        return new NoteViewHolder(view);
    }

}
