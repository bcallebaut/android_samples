package be.belgiplast.notes.model;

import android.arch.lifecycle.LiveData;

import java.util.List;

import be.belgiplast.notes.business.Note;

public class NoteSearchResult extends AbstractSearchResult<Note>{

    LiveData<List<Note>> data;
    LiveData<String> networkErrors;

    public NoteSearchResult(LiveData<List<Note>> data, LiveData<String> networkErrors) {
        super(data, networkErrors);
    }

    public LiveData<List<Note>> getData() {
        return data;
    }

    public void setData(LiveData<List<Note>> data) {
        this.data = data;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }

    public void setNetworkErrors(LiveData<String> networkErrors) {
        this.networkErrors = networkErrors;
    }
}
