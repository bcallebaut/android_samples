package be.belgiplast.pagedlist.api;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import be.belgiplast.pagedlist.model.Repo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Util {
    private static String TAG = "GithubService";
    private static String IN_QUALIFIER = "in:name,description";

    public static void searchRepos(
            GitHubService service,
            String query,
            int page,
            int itemsPerPage,
            final SuccessListener onSuccess,
            final ErrorListener onError) {
        Log.d(TAG, "query: $query, page: $page, itemsPerPage: $itemsPerPage");

        String apiQuery = query + IN_QUALIFIER;

        service.searchRepos(apiQuery, page, itemsPerPage).enqueue(
                new Callback<RepoSearchResponse>() {
                    public void onFailure(Call<RepoSearchResponse> call, Throwable t) {
                        Log.d(TAG, "fail to get data");
                        onError.onError(t.getMessage() != null ? t.getMessage() : "unknown error");
                    }

                    public void onResponse(Call<RepoSearchResponse> call, final Response<RepoSearchResponse> response) {
                        Log.d(TAG, "got a response $response");
                        if (response.isSuccessful()) {
                            List<Repo> repos = Collections.emptyList();
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
