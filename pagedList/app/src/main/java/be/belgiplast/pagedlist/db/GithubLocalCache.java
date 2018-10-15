package be.belgiplast.pagedlist.db;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Executor;

import be.belgiplast.pagedlist.model.CacheListener;
import be.belgiplast.pagedlist.model.Repo;

public class GithubLocalCache {
    private RepoDao repoDao;
    private Executor ioExecutor;

    public GithubLocalCache() {
    }

    public GithubLocalCache(RepoDao repoDao, Executor ioExecutor) {
        this.repoDao = repoDao;
        this.ioExecutor = ioExecutor;
    }

    public void insert(final List<Repo> repos, final CacheListener l){
        ioExecutor.execute(new Runnable(){

            @Override
            public void run() {
                Log.d("GithubLocalCache", String.format("inserting %d repos",repos.size()));
                repoDao.insert(repos);
                l.insertFinished();
            }
        });
    }

    public LiveData<List<Repo>> reposByName(String name){
        String query = "%" + name.replaceAll(" ","%")+"%";
        return repoDao.reposByName(query);
    }
}
