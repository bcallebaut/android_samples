package be.belgiplast.notes.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends ViewModel {
    LiveData<List<Note>> notes;

    public LiveData<List<Note>> getRepos() {
        return notes;
    }
}
