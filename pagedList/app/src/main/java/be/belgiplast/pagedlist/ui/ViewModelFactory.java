package be.belgiplast.pagedlist.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.support.annotation.NonNull;

import be.belgiplast.pagedlist.data.GitHubRepository;

public class ViewModelFactory implements Factory {
    private GitHubRepository repository;

    public ViewModelFactory(GitHubRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) throws IllegalArgumentException{
        if (modelClass.isAssignableFrom(SearchRepositoriesViewModel.class)){
            return (T) new SearchRepositoriesViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
