package be.belgiplast.plugins;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PluginSettingsAdapter extends RecyclerView.Adapter<PluginSettingsHolder> {
    private Context context;
    private PluginSettings settings;

    public PluginSettingsAdapter(Context context) {
        this.context = context;
        settings = ((PluginSettings.Provider)context.getApplicationContext()).getPluginSetting();
    }

    @Override
    public void onBindViewHolder(@NonNull PluginSettingsHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @NonNull
    @Override
    public PluginSettingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plugin_layout, parent, false);
        PluginSettingsHolder holder = new PluginSettingsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PluginSettingsHolder pluginSettingsHolder, int i) {
        pluginSettingsHolder.bind(settings.get(i));
    }

    @Override
    public int getItemCount() {
        return settings.getCount();
    }
}
