package be.belgiplast.plugins;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface CRUDInterface<I> {
    void insert(I t);
    void delete(I t);
    void update(I t);
//    LiveData<List<I>> get(Object... constraints);
}
