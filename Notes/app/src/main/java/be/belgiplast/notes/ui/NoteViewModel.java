package be.belgiplast.notes.ui;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import be.belgiplast.notes.business.Note;
import be.belgiplast.notes.data.NoteRepository;
import be.belgiplast.notes.model.NoteSearchResult;

public class NoteViewModel extends ViewModel {
    private NoteRepository repository;
    private static final int VISIBLE_THRESHOLD = 5;

    private MutableLiveData queryLiveData = new MutableLiveData<String>();
    private LiveData<NoteSearchResult> repoResult;

    LiveData<List<Note>> notes;
    LiveData<String> networkErrors;

    public NoteViewModel(NoteRepository repository) {
        this.repository = repository;
        repoResult = Transformations.map(queryLiveData, new Function<Long,NoteSearchResult>() {
            @Override
            public NoteSearchResult apply(Long s) {
                return repository.search(s);
            }
        });

        notes = Transformations.switchMap(repoResult, new Function<NoteSearchResult, LiveData<List<Note>>>() {
            @Override
            public LiveData<List<Note>> apply(NoteSearchResult input) {
                return input.getData();
            }
        });
        networkErrors = Transformations.switchMap(repoResult, new Function<NoteSearchResult, LiveData<String>>() {
            @Override
            public LiveData<String> apply(NoteSearchResult input) {
                return input.getNetworkErrors();
            }
        });
    }

    public void searchRepo(long queryString){
        queryLiveData.postValue(queryString);
    }

    public void listScrolled(int visibleItemCount, int lastVisibleItemPosition, int totalItemCount) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            long immutableQuery = getLastQueryValue();
            if (immutableQuery >=0 ) {
                repository.requestMore(immutableQuery);
            }
        }
    }

    public long getLastQueryValue(){
        return (Long)queryLiveData.getValue();
    }

    public LiveData<List<Note>> getRepos() {
        return notes;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }
}
