package be.belgiplast.notes.api;

import be.belgiplast.notes.business.Note;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NoteService {
    String TAG = "GithubService";
    String IN_QUALIFIER = "in:name,description";
    String BASE_URL = "http://192.168.1.10:18080/";

    @GET("myapp")
    Call<NoteSearchResponse> searchNotes(@Query("from") long query,
                                         @Query("page") int page,
                                         @Query("per_page") int itemsPerPage);

    @POST("myapp")
    @FormUrlEncoded
    Call<NoteSearchResponse> createNote(@Field("name") String name,@Field("description") String description);


}
