package be.belgiplast.plugins;

import android.content.Context;
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
    private List<Plugin> availablePlugins = new ArrayList<>();
    private List<Plugin> plugins = new ArrayList<>();
    private Context context;

    public PluginAdapter(Context context) {
        this.context = context;
        availablePlugins.addAll(((PluginsManager.Provider)context.getApplicationContext()).getPluginsManager().getPlugins().values());
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plugin_layout, parent, false);

        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Plugin plugin = availablePlugins.get(position);
        holder.name.setText(plugin.getName());
        holder.description.setText(plugin.getDescription());
        holder.getImg().setImageResource(plugin.getImageResource());
    }

    @Override
    public int getItemCount() {
        return availablePlugins.size();
    }

    public List<Plugin> getAvailablePlugins() {
        return availablePlugins;
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public static class Holder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView  name;
        private TextView description;
        private ImageButton btn;

        public Holder(View itemView) {
            super(itemView);
            setImg((ImageView)itemView.findViewById(R.id.plugin_image));
            setName((TextView)itemView.findViewById(R.id.plugin_name));
            setDescription((TextView)itemView.findViewById(R.id.plugin_description));
            /*
            btn = (ImageButton)itemView.findViewById(R.id.plugin_button);
            btn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                }
            });
*/
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
