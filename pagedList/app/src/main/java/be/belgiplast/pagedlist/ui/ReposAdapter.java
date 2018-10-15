package be.belgiplast.pagedlist.ui;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import be.belgiplast.pagedlist.model.Repo;

public class ReposAdapter extends ListAdapter<Repo,RecyclerView.ViewHolder> {
    public ReposAdapter() {
        super(REPO_COMPARATOR);
    }

    public ReposAdapter(@NonNull AsyncDifferConfig<Repo> config) {
        super(config);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RepoViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Repo repoItem = getItem(position);
        if (repoItem != null) {
            ((RepoViewHolder)holder).bind(repoItem);
        }
    }

    private static DiffUtil.ItemCallback<Repo> REPO_COMPARATOR = new DiffUtil.ItemCallback<Repo>() {


        @Override
        public boolean areItemsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.getFullName().equals(newItem.getFullName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem == newItem;
        }
    };
}
