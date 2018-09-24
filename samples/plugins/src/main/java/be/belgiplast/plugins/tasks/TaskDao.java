package be.belgiplast.plugins.tasks;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;
@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MutableTaskImpl task);
    @Query("DELETE FROM task")
    void deleteAll();

    @Query("SELECT * from task ORDER BY id ASC")
    LiveData<List<MutableTaskImpl>> getAllTasks();

    @Query("SELECT * from task WHERE task.progress = 100 ORDER BY id ASC")
    LiveData<List<MutableTaskImpl>> getFinishedTasks();

    @Query("SELECT * from task WHERE task.progress < 100 ORDER BY id ASC")
    LiveData<List<MutableTaskImpl>> getUnfinishedTasks();

    @Query("SELECT * from task WHERE task.progress = 0 ORDER BY id ASC")
    LiveData<List<MutableTaskImpl>> getNewTasks();
}
