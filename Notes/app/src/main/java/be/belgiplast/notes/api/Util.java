package be.belgiplast.notes.api;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import be.belgiplast.notes.business.Note;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Util {
    private static String TAG = "GithubService";
    private static String IN_QUALIFIER = "in:name,description";

    public static void searchRepos(
            NoteService service,
            long query,
            int page,
            int itemsPerPage,
            final SuccessListener onSuccess,
            final ErrorListener onError) {
        Log.d(TAG, "query: $query, page: $page, itemsPerPage: $itemsPerPage");

        String apiQuery = query + IN_QUALIFIER;

        service.searchRepos(apiQuery, page, itemsPerPage).enqueue(
                new Callback<NoteSearchResponse>() {
                    public void onFailure(Call<NoteSearchResponse> call, Throwable t) {
                        Log.d(TAG, "fail to get data");
                        onError.onError(t.getMessage() != null ? t.getMessage() : "unknown error");
                    }

                    public void onResponse(Call<NoteSearchResponse> call, final Response<NoteSearchResponse> response) {
                        Log.d(TAG, "got a response $response");
                        if (response.isSuccessful()) {
                            List<Note> repos = Collections.emptyList();
                            if (response.body().items != null)
                                repos = response.body().items;
                            onSuccess.onSuccess(repos);
                        } else {
                            onError.onError(response.errorBody().toString() != null ? response.errorBody().toString() : "Unknown error");
                        }
                    }
                }
        );
    }
}
