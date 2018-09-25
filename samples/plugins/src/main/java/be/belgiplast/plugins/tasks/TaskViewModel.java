package be.belgiplast.plugins.tasks;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepository;

    private LiveData<List<MutableTaskImpl>> mAllWords;

    public TaskViewModel (Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllWords = mRepository.getAllTasks();
    }

    LiveData<List<MutableTaskImpl>> getAllTasks() { return mAllWords; }

    public void insert(MutableTaskImpl word) { mRepository.insert(word); }

    public void update(MutableTaskImpl word) { mRepository.update(word); }

    public void delete(MutableTaskImpl word) { mRepository.delete(word); }
}
