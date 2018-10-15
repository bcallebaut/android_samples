package be.belgiplast.pagedlist;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import java.util.concurrent.Executors;

import be.belgiplast.pagedlist.api.GitHubService;
import be.belgiplast.pagedlist.data.GitHubRepository;
import be.belgiplast.pagedlist.db.GithubLocalCache;
import be.belgiplast.pagedlist.db.RepoDatabase;
import be.belgiplast.pagedlist.ui.ViewModelFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injection {
    public static GithubLocalCache provideCache(Context context) {
        RepoDatabase database = RepoDatabase.getDatabase(context);
        return new GithubLocalCache(database.reposDao(), Executors.newSingleThreadExecutor());
    }

    public static GitHubRepository provideGithubRepository(Context context) {
        return new GitHubRepository(create(), provideCache(context));
    }

    public static GitHubService create(){
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logger).build();
        return new Retrofit.Builder()
                .baseUrl(GitHubService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService.class);
    }

    public static ViewModelProvider.Factory provideViewModelFactory(Context context )  {
        return new ViewModelFactory(provideGithubRepository(context));
    }
}
