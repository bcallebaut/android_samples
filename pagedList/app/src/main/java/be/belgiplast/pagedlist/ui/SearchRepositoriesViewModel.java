package be.belgiplast.pagedlist.ui;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;


import java.util.List;

import be.belgiplast.pagedlist.data.GitHubRepository;
import be.belgiplast.pagedlist.model.Repo;
import be.belgiplast.pagedlist.model.RepoSearchResult;

public class SearchRepositoriesViewModel extends ViewModel {
    private GitHubRepository repository;
    private static final int VISIBLE_THRESHOLD = 5;

    private MutableLiveData queryLiveData = new MutableLiveData<String>();
    private LiveData<RepoSearchResult> repoResult;

    LiveData<List<Repo>> repos;
    LiveData<String> networkErrors;

    public SearchRepositoriesViewModel(final GitHubRepository repository) {
        this.repository = repository;
        repoResult = Transformations.map(queryLiveData, new Function<String,RepoSearchResult>() {
            @Override
            public RepoSearchResult apply(String s) {
                return repository.search(s);
            }
        });
        repos = Transformations.switchMap(repoResult, new Function<RepoSearchResult, LiveData<List<Repo>>>() {
            @Override
            public LiveData<List<Repo>> apply(RepoSearchResult input) {
                return input.getData();
            }
        });
        networkErrors = Transformations.switchMap(repoResult, new Function<RepoSearchResult, LiveData<String>>() {
            @Override
            public LiveData<String> apply(RepoSearchResult input) {
                return input.getNetworkErrors();
            }
        });

    }

    public void searchRepo(String queryString){
        queryLiveData.postValue(queryString);
    }

    public void listScrolled(int visibleItemCount, int lastVisibleItemPosition, int totalItemCount) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            String immutableQuery = getLastQueryValue();
            if (immutableQuery != null) {
                repository.requestMore(immutableQuery);
            }
        }
    }

    public String getLastQueryValue(){
        return (String)queryLiveData.getValue();
    }

    public LiveData<List<Repo>> getRepos() {
        return repos;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }
}
