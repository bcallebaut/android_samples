package be.belgiplast.notes.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoteService {
    String TAG = "GithubService";
    String IN_QUALIFIER = "in:name,description";
    String BASE_URL = "https://192.168.1.10:18080/";

    @GET("myapp")
    Call<NoteSearchResponse> searchRepos(@Query("q") long query,
                                         @Query("page") int page,
                                         @Query("per_page") int itemsPerPage);


}
