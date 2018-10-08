package be.belgiplast.notes.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import be.belgiplast.notes.business.Note;
import be.belgiplast.notes.business.UserRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    static final String BASE_URL = "http://192.168.1.10:18080/myapp/";
    private NoteAPI noteApi;
    UserRepository repository;

    private NoteListCallback listCallback = new NoteListCallback();
    private NoteCallback singleCallback = new NoteCallback();



    public Controller(UserRepository repository) {
        this.repository = repository;
    }

    public void start() {
        Gson gson = new GsonBuilder().setLenient().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement jsonElement, Type typeOF, JsonDeserializationContext context) throws JsonParseException {
                return new Date(jsonElement.getAsLong());
                /*
                throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                        + "\". Supported formats: \n");
                        */
            }
        }).create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        noteApi = retrofit.create(NoteAPI.class);
        loadNotes();
    }

    public void loadNotes() {
        Call<List<Note>> call = noteApi.loadNotes();
        call.enqueue(listCallback);
    }

    public void loadNewNotes(Date timestamp) {
        Call<List<Note>> call = noteApi.loadNewNotes(timestamp);
        call.enqueue(listCallback);
    }

    public void addNote(Note note) {
        Call<Note> call = noteApi.addNote(note);
        call.enqueue(singleCallback);
    }

    public void updateNote(Note note) {
        Call<Note> call = noteApi.updateNote(note);
        call.enqueue(singleCallback);
    }

    public void deleteNote(Note note) {
        Call<Note> call = noteApi.deleteNote(note.getId());
        call.enqueue(singleCallback);
    }

    private class NoteListCallback implements Callback<List<Note>> {
        @Override
        public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
            if (response.isSuccessful()) {
                List<Note> changesList = response.body();
                changesList.forEach(change -> System.out.println(change.getName()));
                repository.updateNotes(changesList);
            } else {
                System.out.println(response.errorBody());
            }
        }

        @Override
        public void onFailure(Call<List<Note>> call, Throwable t) {
            t.printStackTrace();
        }
    }

    private class NoteCallback implements Callback<Note> {
        @Override
        public void onResponse(Call<Note> call, Response<Note> response) {
            if (response.isSuccessful()) {
                Note changesList = response.body();
                //repository.
            } else {
                System.out.println(response.errorBody());
            }
        }

        @Override
        public void onFailure(Call<Note> call, Throwable t) {
            t.printStackTrace();
        }
    }
}
