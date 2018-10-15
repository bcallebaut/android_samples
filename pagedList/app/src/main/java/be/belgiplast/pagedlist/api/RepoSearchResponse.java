package be.belgiplast.pagedlist.api;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

import be.belgiplast.pagedlist.model.Repo;

public class RepoSearchResponse {
    @SerializedName("total_count") int total = 0;
    @SerializedName("items")
    List<Repo> items = Collections.emptyList();
    int nextPage = -1;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Repo> getItems() {
        return items;
    }

    public void setItems(List<Repo> items) {
        this.items = items;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
