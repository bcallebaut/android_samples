package be.belgiplast.plugins.tasks;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<MutableTaskImpl>> mAllTasks;

    public TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getAllTasks();
    }

    public void insert (MutableTaskImpl word) {
        new insertAsyncTask(mTaskDao).execute(word);
    }

    public LiveData<List<MutableTaskImpl>> getAllTasks() {
        return mAllTasks;
    }

    private static class insertAsyncTask extends AsyncTask<MutableTaskImpl, Void, Void> {

        private TaskDao mAsyncTaskDao;

        insertAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MutableTaskImpl... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
