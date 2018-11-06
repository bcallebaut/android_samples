package be.belgiplast.notes.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.Date;
import java.util.List;

import be.belgiplast.notes.api.ErrorListener;
import be.belgiplast.notes.api.NoteService;
import be.belgiplast.notes.api.SuccessListener;
import be.belgiplast.notes.api.Util;
import be.belgiplast.notes.business.Note;
import be.belgiplast.notes.db.NoteLocalCache;
import be.belgiplast.notes.model.CacheListener;
import be.belgiplast.notes.model.NoteSearchResult;

public class NoteRepository {
    private static final int NETWORK_PAGE_SIZE = 50;

    private NoteService service;
    private NoteLocalCache cache;


    private int lastRequestedPage = 1;
    private MutableLiveData<String> networkErrors = new MutableLiveData<>();
    private boolean isRequestInProgress = false;

    public NoteRepository(NoteService service, NoteLocalCache cache) {
        this.service = service;
        this.cache = cache;
    }

    public NoteSearchResult search(long query){
        Log.d("NoteRepository","New Query : " + query);
        lastRequestedPage = 1;
        requestAndSaveData(query);
        LiveData<List<Note>> data = cache.reposById(query);
        return new NoteSearchResult(data, networkErrors);
    }

    public void createNote(String name,String description){
        final Note note = new Note();
        note.setName(name);
        note.setDescription(description);
        note.setTimestamp(new Date());
        note.setModification(note.getTimestamp());
        note.setId(note.hashCode());
        cache.insert(note, new CacheListener() {
            @Override
            public void insertFinished() {
                service.createNote(name,description);
            }
        });
    }

    public void requestMore(long query){
        requestAndSaveData(query);
    }

    private void requestAndSaveData(long query){
        if (isRequestInProgress) return;

        isRequestInProgress = true;
        Util.searchRepos(service, query, lastRequestedPage, NETWORK_PAGE_SIZE,
                new SuccessListener() {
                    @Override
                    public void onSuccess(List<Note> repos) {
                        cache.insert(repos, new CacheListener() {
                            @Override
                            public void insertFinished() {
                                lastRequestedPage++;
                                isRequestInProgress = false;
                            }
                        });
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onError(String error) {
                        networkErrors.postValue(error);
                        isRequestInProgress = false;
                    }
                }

        );
    }
}
