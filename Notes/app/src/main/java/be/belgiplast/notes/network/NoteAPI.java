package be.belgiplast.notes.network;

import java.util.Date;
import java.util.List;

import be.belgiplast.notes.business.Note;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NoteAPI {
    @GET("notes")
    Call<List<Note>> loadNotes();
    @GET("notes")
    Call<List<Note>> loadNewNotes(@Query("time") Date timestamp);
    @POST("notes/new")
    Call<Note> addNote(@Body Note note);
    @POST("notes")
    Call<Note> updateNote(@Body Note note);
    @DELETE("notes/{id}")
    Call<Note> deleteNote(@Path("id") long id);

}
