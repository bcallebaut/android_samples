package be.belgiplast.plugins;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PluginSettingsHolder extends RecyclerView.ViewHolder {
    private final ImageView icon;
    private final TextView index;
    private final TextView name;
    private final ImageButton up;
    private final ImageButton down;
    private final View.OnClickListener upListener;
    private final View.OnClickListener downListener;
    private PluginSetting data;

    public PluginSettingsHolder(@NonNull View itemView) {
        super(itemView);
        index = itemView.findViewById(R.id.index);
        icon = itemView.findViewById(R.id.icon);
        name = itemView.findViewById(R.id.name);
        up = itemView.findViewById(R.id.up);
        down = itemView.findViewById(R.id.down);

        upListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null)
                    data.moveUp();
            }
        };

        downListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null)
                    data.moveDown();
            }
        };
    }

    public final void bind(PluginSetting data){
        this.data = data;
        name.setText(data.getName());
        index.setText(String.format("%d",data.getPosition() + 1));
        icon.setImageResource(data.getIcon());
        up.setOnClickListener(upListener);
        down.setOnClickListener(downListener);
    }
}
