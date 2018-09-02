package be.belgiplast.plugins;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.support.v7.widget.DividerItemDecoration;

public class PluginsView extends RecyclerView {
    private PluginAdapter adapter;

    public PluginsView(Context context) {
        this(context, null,0);
    }

    public PluginsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PluginsView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        adapter = new PluginAdapter(context);
        setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context.getApplicationContext());
        //context.getApplicationContext().getPackageManager().
        setLayoutManager(mLayoutManager);
        setItemAnimator(new DefaultItemAnimator());
//        addItemDecoration(new SimpleDividerItemDecoration(context.getApplicationContext()));
        setAdapter(adapter);
    }
}
