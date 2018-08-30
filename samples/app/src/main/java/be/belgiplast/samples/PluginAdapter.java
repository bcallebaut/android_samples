package be.belgiplast.samples;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PluginAdapter extends RecyclerView.Adapter<PluginAdapter.Holder> {
    private List<Holder> availablePlugins = new ArrayList<>();
    private List<Holder> plugins = new ArrayList<>();

    public PluginAdapter() {
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plugin_layout, parent, false);

        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return availablePlugins.size();
    }

    public List<Holder> getAvailablePlugins() {
        return availablePlugins;
    }

    public List<Holder> getPlugins() {
        return plugins;
    }

    public static class Holder{
        private View view;
        private ImageView img;
        private TextView  name;
        private TextView description;
        private ImageButton btn;


        public Holder(View view) {
            setView(view);
        }

        public final void setView(View view) {
            this.view = view;
            setImg((ImageView)view.findViewById(R.id.plugin_image));
            setName((TextView)view.findViewById(R.id.plugin_name));
            setDescription((TextView)view.findViewById(R.id.plugin_description));
            btn = (ImageButton)view.findViewById(R.id.plugin_button);
            btn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                }
            });
        }

        public ImageView getImg() {
            return img;
        }

        private void setImg(ImageView img) {
            this.img = img;
        }

        public TextView getName() {
            return name;
        }

        private void setName(TextView name) {
            this.name = name;
        }

        public TextView getDescription() {
            return description;
        }

        private void setDescription(TextView description) {
            this.description = description;
        }
    }
}
