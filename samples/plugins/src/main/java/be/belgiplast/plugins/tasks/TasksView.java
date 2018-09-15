package be.belgiplast.plugins.tasks;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class TasksView extends RecyclerView {

    private TaskAdapter adapter;

    public TasksView(Context context) {
        this(context, null,0);
    }

    public TasksView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TasksView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        adapter = new TaskAdapter();
        adapter.setListener(new TaskClickListener(){

            @Override
            public void onTaskClicked(Task task) {

            }
        });
        setAdapter(adapter);
        LayoutManager mLayoutManager = new LinearLayoutManager(context.getApplicationContext());
        //context.getApplicationContext().getPackageManager().
        setLayoutManager(mLayoutManager);
        setItemAnimator(new DefaultItemAnimator());
        setAdapter(adapter);

    }
}
