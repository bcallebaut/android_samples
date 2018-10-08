package be.belgiplast.notes.business;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface NoteDao{
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
}
