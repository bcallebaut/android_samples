package be.belgiplast.pagedlist.api;

import java.util.List;

import be.belgiplast.pagedlist.model.Repo;

public interface SuccessListener {
    void onSuccess(List<Repo> repos);
}
