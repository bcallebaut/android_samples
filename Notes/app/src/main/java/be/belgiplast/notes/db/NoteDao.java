package be.belgiplast.notes.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import be.belgiplast.notes.business.Note;

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Note> posts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note task);
    @Query("DELETE FROM note")
    void deleteAll();

    @Update
    void update(Note task);

    @Delete
    void delete(Note task);

    @Query("SELECT * from note ORDER BY timestamp ASC")
    LiveData<List<Note>> getAllTasks();

    @Query("SELECT * from note WHERE timestamp>=:start AND timestamp <=:end ORDER BY timestamp ASC")
    LiveData<List<Note>> getTaskRange(java.util.Date start, java.util.Date end);

    @Query("SELECT * from note WHERE modification>=:start ORDER BY timestamp ASC")
    LiveData<List<Note>> getModifiedNotes(java.util.Date start);

    // Do a similar query as the search API:
    // Look for repos that contain the query string in the name or in the description
    // and order those results descending, by the number of stars and then by name
    @Query("SELECT * FROM note WHERE (id LIKE :queryString) ORDER BY timestamp DESC, name ASC")
    LiveData<List<Note>> reposById(long queryString);
}
