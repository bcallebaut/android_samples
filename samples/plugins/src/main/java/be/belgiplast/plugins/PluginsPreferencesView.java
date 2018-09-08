package be.belgiplast.plugins;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class PluginsPreferencesView extends RecyclerView {
    private PluginSettingsAdapter adapter;

    public PluginsPreferencesView(Context context) {
        this(context, null,0);
    }

    public PluginsPreferencesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PluginsPreferencesView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        adapter = new PluginSettingsAdapter(context);
        setAdapter(adapter);
        LayoutManager mLayoutManager = new LinearLayoutManager(context.getApplicationContext());
        //context.getApplicationContext().getPackageManager().
        setLayoutManager(mLayoutManager);
        setItemAnimator(new DefaultItemAnimator());
//        addItemDecoration(new SimpleDividerItemDecoration(context.getApplicationContext()));
        setAdapter(adapter);

    }
}
