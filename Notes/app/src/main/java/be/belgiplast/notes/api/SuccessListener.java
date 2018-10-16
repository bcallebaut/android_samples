package be.belgiplast.notes.api;

import java.util.List;

import be.belgiplast.notes.business.Note;

public interface SuccessListener {
    void onSuccess(List<Note> repos);
}
