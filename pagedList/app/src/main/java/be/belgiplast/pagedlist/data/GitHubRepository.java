package be.belgiplast.pagedlist.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import be.belgiplast.pagedlist.api.ErrorListener;
import be.belgiplast.pagedlist.api.SuccessListener;
import be.belgiplast.pagedlist.api.Util;
import be.belgiplast.pagedlist.db.GithubLocalCache;

import be.belgiplast.pagedlist.api.GitHubService;
import be.belgiplast.pagedlist.model.CacheListener;
import be.belgiplast.pagedlist.model.Repo;
import be.belgiplast.pagedlist.model.RepoSearchResult;

public class GitHubRepository {
    private static final int NETWORK_PAGE_SIZE = 50;

    private GitHubService service;
    private GithubLocalCache cache;


    private int lastRequestedPage = 1;
    private MutableLiveData<String> networkErrors = new MutableLiveData<>();
    private boolean isRequestInProgress = false;

    public GitHubRepository(GitHubService service, GithubLocalCache cache) {
        this.service = service;
        this.cache = cache;
    }

    public RepoSearchResult search(String query){
        Log.d("GitHubRepository","New Query : " + query);
        lastRequestedPage = 1;
        requestAndSaveData(query);
        LiveData<List<Repo>> data = cache.reposByName(query);
        return new RepoSearchResult(data, networkErrors);
    }

    public void requestMore(String query){
        requestAndSaveData(query);
    }

    private void requestAndSaveData(String query){
        if (isRequestInProgress) return;

        isRequestInProgress = true;
        Util.searchRepos(service, query, lastRequestedPage, NETWORK_PAGE_SIZE,
                new SuccessListener() {
                    @Override
                    public void onSuccess(List<Repo> repos) {
                        cache.insert(repos, new CacheListener() {
                            @Override
                            public void insertFinished() {
                                lastRequestedPage++;
                                isRequestInProgress = false;
                            }
                        });
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onError(String error) {
                        networkErrors.postValue(error);
                        isRequestInProgress = false;
                    }
                }

        );
    }
}
