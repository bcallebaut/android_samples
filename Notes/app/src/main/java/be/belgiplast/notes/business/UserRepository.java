package be.belgiplast.notes.business;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import be.belgiplast.notes.network.Controller;

@Singleton
public class UserRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 3;

    private final Controller webservice;
    private final NoteDao noteDao;
    private final Executor executor;
    private Date lastUpdate = null;

    public UserRepository(Context context) {
        this.webservice = new Controller(this);
        this.noteDao = NoteRoomDatabase.getDatabase(context).noteDao();
        this.webservice.start();
        this.executor = Executors.newCachedThreadPool();
    }

    public void updateNotes(List<Note> notes) {
        lastUpdate = new Date();
        for (Note note : notes) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        noteDao.update(note);
                    } catch (Exception e) {
                        noteDao.insert(note);
                    }
                }
            });
        }
    }

    public LiveData<List<Note>> getNotes() {
        refreshNotes();
        return noteDao.getAllTasks(); // return a LiveData directly from the database.
    }

    // ---

    private void refreshNotes() {
        executor.execute(() -> {
            if (lastUpdate == null) {
                webservice.loadNotes();
            } else {
                webservice.loadNewNotes(lastUpdate);
            }
        });
    }

    // ---

    private Date getMaxRefreshTime(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
