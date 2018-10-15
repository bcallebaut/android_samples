package be.belgiplast.pagedlist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import be.belgiplast.pagedlist.model.Repo;
import be.belgiplast.pagedlist.ui.ReposAdapter;
import be.belgiplast.pagedlist.ui.SearchRepositoriesViewModel;

public class MainActivity extends AppCompatActivity {

    private static String LAST_SEARCH_QUERY = "last_search_query";
    private static String DEFAULT_QUERY = "Android";

    private SearchRepositoriesViewModel viewModel;
    private ReposAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the view model
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this)).get(SearchRepositoriesViewModel.class);

        // add dividers between RecyclerView's row items
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        ((android.support.v7.widget.RecyclerView)this.findViewById(R.id.list)).addItemDecoration(decoration);
        setupScrollListener();

        initAdapter();
        String query = DEFAULT_QUERY;
        try {
            query = savedInstanceState.getString(LAST_SEARCH_QUERY) != null ? savedInstanceState.getString(LAST_SEARCH_QUERY) : DEFAULT_QUERY;
        }catch (Exception e){}
        viewModel.searchRepo(query);
        initSearch(query);
    }

    private void initAdapter() {
        adapter = new ReposAdapter();
        ((android.support.v7.widget.RecyclerView)this.findViewById(R.id.list)).setAdapter(adapter);
        viewModel.getRepos().observe(this, new Observer<List<Repo>>(){
            @Override
            public void onChanged(@Nullable List<Repo> repos) {
                Log.d("Activity", String.format("list: %d",repos.size()));
                showEmptyList(repos.size() == 0);
                adapter.submitList(repos);
            }
        });
        viewModel.getNetworkErrors().observe(this, new Observer<String>(){
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, "\uD83D\uDE28 Wooops " + s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initSearch(String query) {
        ((TextView)this.findViewById(R.id.search_repo)).setText(query);
        ((TextView)this.findViewById(R.id.search_repo)).setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_GO) {
                            updateRepoListFromInput();
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
        );
        ((TextView)this.findViewById(R.id.search_repo)).setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    updateRepoListFromInput();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void updateRepoListFromInput() {
        String input = ((TextView)this.findViewById(R.id.search_repo)).getText().toString().trim();

        if (!input.isEmpty()) {
            ((android.support.v7.widget.RecyclerView)this.findViewById(R.id.list)).scrollToPosition(0);
            viewModel.searchRepo(input.toString());
            adapter.submitList(null);
        }
    }

    private void showEmptyList(boolean show) {
        if (show) {
            this.findViewById(R.id.emptyList).setVisibility(View.VISIBLE);
            this.findViewById(R.id.list).setVisibility(View.GONE);
        } else {
            this.findViewById(R.id.emptyList).setVisibility(View.GONE);
            this.findViewById(R.id.list).setVisibility(View.VISIBLE);
        }
    }

    private void setupScrollListener() {
        final LinearLayoutManager layoutManager = (LinearLayoutManager)((android.support.v7.widget.RecyclerView)this.findViewById(R.id.list)).getLayoutManager();
        ((android.support.v7.widget.RecyclerView)this.findViewById(R.id.list)).addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = layoutManager.getChildCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount);
            }
        });
    }
}
