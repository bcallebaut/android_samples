package be.belgiplast.pagedlist.model;

import android.arch.lifecycle.LiveData;

import java.util.List;

public class RepoSearchResult {
    LiveData<List<Repo>> data;
    LiveData<String> networkErrors;

    public RepoSearchResult() {
    }

    public RepoSearchResult(LiveData<List<Repo>> data, LiveData<String> networkErrors) {
        this.data = data;
        this.networkErrors = networkErrors;
    }

    public LiveData<List<Repo>> getData() {
        return data;
    }

    public void setData(LiveData<List<Repo>> data) {
        this.data = data;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }

    public void setNetworkErrors(LiveData<String> networkErrors) {
        this.networkErrors = networkErrors;
    }
}
