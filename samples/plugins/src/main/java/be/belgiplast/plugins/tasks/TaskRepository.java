package be.belgiplast.plugins.tasks;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import be.belgiplast.plugins.CRUDInterface;

public class TaskRepository implements CRUDInterface<MutableTaskImpl> {
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

    public void update (MutableTaskImpl word) {
        new updateAsyncTask(mTaskDao).execute(word);
    }

    public void delete (MutableTaskImpl word) {
        new deleteAsyncTask(mTaskDao).execute(word);
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

    private static class updateAsyncTask extends AsyncTask<MutableTaskImpl, Void, Void> {

        private TaskDao mAsyncTaskDao;

        updateAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MutableTaskImpl... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<MutableTaskImpl, Void, Void> {

        private TaskDao mAsyncTaskDao;

        deleteAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MutableTaskImpl... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
