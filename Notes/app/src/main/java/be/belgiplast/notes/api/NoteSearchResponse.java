package be.belgiplast.notes.api;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

import be.belgiplast.notes.business.Note;

public class NoteSearchResponse {
    @SerializedName("total_count") int total = 0;
    @SerializedName("items")
    List<Note> items = Collections.emptyList();
    int nextPage = -1;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Note> getItems() {
        return items;
    }

    public void setItems(List<Note> items) {
        this.items = items;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
