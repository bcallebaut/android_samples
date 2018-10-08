package be.belgiplast.plugins.tasks;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.arch.persistence.room.*;

import java.util.List;

import be.belgiplast.plugins.CRUDInterface;

@Dao
public interface TaskDao extends CRUDInterface<MutableTaskImpl> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MutableTaskImpl task);
    @Query("DELETE FROM task")
    void deleteAll();

    @Update
    void update(MutableTaskImpl task);

    @Delete
    void delete(MutableTaskImpl task);

    @Query("SELECT * from task ORDER BY id ASC")
    LiveData<List<MutableTaskImpl>> getAllTasks();

    @Query("SELECT * from task WHERE task.progress = 100 ORDER BY id ASC")
    LiveData<List<MutableTaskImpl>> getFinishedTasks();

    @Query("SELECT * from task WHERE task.progress < 100 ORDER BY id ASC")
    LiveData<List<MutableTaskImpl>> getUnfinishedTasks();

    @Query("SELECT * from task WHERE task.progress = 0 ORDER BY id ASC")
    LiveData<List<MutableTaskImpl>> getNewTasks();

    @Query("SELECT * from task WHERE task.id>=:start AND task.id <=:end ORDER BY id ASC")
    LiveData<List<MutableTaskImpl>> getTaskRange(int start, int end);
}
