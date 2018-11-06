package be.belgiplast.notes.db;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Executor;

import be.belgiplast.notes.business.Note;
import be.belgiplast.notes.model.CacheListener;

public class NoteLocalCache {
    private NoteDao repoDao;
    private Executor ioExecutor;

    public NoteLocalCache() {
    }

    public NoteLocalCache(NoteDao repoDao, Executor ioExecutor) {
        this.repoDao = repoDao;
        this.ioExecutor = ioExecutor;
    }

    public void insert(final Note repos, final CacheListener l){
        ioExecutor.execute(new Runnable(){

            @Override
            public void run() {
                Log.d("NoteLocalCache", String.format("inserting %d notes",1));
                repoDao.insert(repos);
                l.insertFinished();
            }
        });
    }

    public void insert(final List<Note> repos, final CacheListener l){
        ioExecutor.execute(new Runnable(){

            @Override
            public void run() {
                Log.d("NoteLocalCache", String.format("inserting %d notes",repos.size()));
                repoDao.insert(repos);
                l.insertFinished();
            }
        });
    }

    public LiveData<List<Note>> reposById(long id){
        return repoDao.reposById(id);
    }
}
