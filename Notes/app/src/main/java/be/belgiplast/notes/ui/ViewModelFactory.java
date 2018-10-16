package be.belgiplast.notes.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.support.annotation.NonNull;

import be.belgiplast.notes.data.NoteRepository;

public class ViewModelFactory implements Factory{
    private NoteRepository repository;

    public ViewModelFactory(NoteRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) throws IllegalArgumentException{
        if (modelClass.isAssignableFrom(NoteViewModel.class)){
            return (T) new NoteViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
