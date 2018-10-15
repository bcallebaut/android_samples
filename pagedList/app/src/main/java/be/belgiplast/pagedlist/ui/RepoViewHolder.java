package be.belgiplast.pagedlist.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.belgiplast.pagedlist.R;
import be.belgiplast.pagedlist.model.Repo;

public class RepoViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView description;
    private TextView stars;
    private TextView language;
    private TextView forks;

    private Repo repo = null;

    public RepoViewHolder(final View view) {
        super(view);
        name = view.findViewById(R.id.repo_name);
        description = view.findViewById(R.id.repo_description);
        stars = view.findViewById(R.id.repo_stars);

        language = view.findViewById(R.id.repo_language);
        forks = view.findViewById(R.id.repo_forks);
        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(repo.getUrl()));
                view.getContext().startActivity(intent);
            }
        });
    }

    public void bind(Repo repo) {
        if (repo == null) {
            Resources resources = itemView.getResources();
            name.setText(resources.getString(R.string.loading));
            description.setVisibility(View.GONE);
            language.setVisibility(View.GONE);
            stars.setText(resources.getString(R.string.unknown));
            forks.setText(resources.getString(R.string.unknown));
        } else {
            showRepoData(repo);
        }
    }

    private void showRepoData(Repo repo) {
        this.repo = repo;
        name.setText(repo.getFullName());

        // if the description is missing, hide the TextView
        int descriptionVisibility = View.GONE;
        if (repo.getDescription() != null) {
            description.setText(repo.getDescription());
            descriptionVisibility = View.VISIBLE;
        }
        description.setVisibility(descriptionVisibility);

        try {
            stars.setText(String.valueOf(repo.getStars()));
            forks.setText(String.valueOf(repo.getForks()));
        }catch (Exception e){
            e.printStackTrace();
        }

        // if the language is missing, hide the label and the value
        int languageVisibility = View.GONE;
        if (repo.getLanguage() != null && repo.getLanguage().length() > 0) {
            Resources resources = this.itemView.getContext().getResources();
            language.setText(resources.getString(R.string.language, repo.getLanguage()));
            languageVisibility = View.VISIBLE;
        }
        language.setVisibility(languageVisibility);
    }


    public static RepoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_view_item, parent, false);
        return new RepoViewHolder(view);
    }

}
