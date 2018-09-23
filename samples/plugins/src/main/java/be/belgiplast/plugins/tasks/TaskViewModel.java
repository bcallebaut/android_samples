package be.belgiplast.plugins.tasks;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepository;

    private LiveData<List<MutableTaskImpl>> mAllTasks;

    public TaskViewModel (Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    LiveData<List<MutableTaskImpl>> getAllTasks() { return mAllTasks; }

    public void insert(MutableTaskImpl word) { mRepository.insert(word); }
}
