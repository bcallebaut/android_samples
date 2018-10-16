package be.belgiplast.notes.model;

import android.arch.lifecycle.LiveData;

import java.util.List;

import be.belgiplast.notes.business.Note;

public class AbstractSearchResult<T> {
    LiveData<List<T>> data;
    LiveData<String> networkErrors;

    public AbstractSearchResult(LiveData<List<T>> data, LiveData<String> networkErrors) {
        this.data = data;
        this.networkErrors = networkErrors;
    }

    public LiveData<List<T>> getData() {
        return data;
    }

    public void setData(LiveData<List<T>> data) {
        this.data = data;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }

    public void setNetworkErrors(LiveData<String> networkErrors) {
        this.networkErrors = networkErrors;
    }
}
