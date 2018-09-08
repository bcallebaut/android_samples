package be.belgiplast.plugins;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class PluginAdapter extends RecyclerView.Adapter<PluginAdapter.Holder> {
    private List<Plugin> availablePlugins = new ArrayList<>();
    private List<Plugin> plugins = new ArrayList<>();
    private Context context;
    private PluginsView container;
    private PluginsManager mgr;
    private PluginSettings settings;

    public PluginAdapter(PluginsView view) {
        this.context = view.getContext();
        container = view;
        mgr = ((PluginsManager.Provider)context.getApplicationContext()).getPluginsManager();
        settings = ((PluginSettings.Provider)context.getApplicationContext()).getPluginSetting();
        availablePlugins.addAll(mgr.getPlugins().values());
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plugin_layout, parent, false);

        return new Holder(itemView,this);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Plugin plugin = availablePlugins.get(position);
        holder.name.setText(plugin.getName());
        holder.description.setText(plugin.getDescription());
        holder.getImg().setImageResource(plugin.getImageResource());
        holder.setIndex(position);
    }

    @Override
    public int getItemCount() {
        return mgr.getPlugins().values().size();
    }

    public List<Plugin> getAvailablePlugins() {
        return availablePlugins;
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    private void onItemClick(Holder holder,boolean checked){
        Plugin p = availablePlugins.get(holder.index);
        if (checked) {
            settings.add(p);
        }else{
            settings.remove(p);
        }
    }

    public static class Holder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView  name;
        private TextView description;
        private Button btn;
        private int index;

        public Holder(View itemView, final PluginAdapter adapter) {
            super(itemView);
            setImg((ImageView)itemView.findViewById(R.id.plugin_image));
            setName((TextView)itemView.findViewById(R.id.plugin_name));
            setDescription((TextView)itemView.findViewById(R.id.plugin_description));

            btn = (Button)itemView.findViewById(R.id.plugin_button);
            btn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    adapter.onItemClick(Holder.this,((ToggleButton)btn).isChecked());
                }
            });

        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
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
