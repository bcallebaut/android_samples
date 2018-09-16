package be.belgiplast.plugins.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.List;

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
                int requestCode = adapter.indexOf(task);
                Intent intent = new Intent(getContext(),TaskEditActivity.class);
                ((Activity)getContext()).startActivityForResult(intent, requestCode | 0x12000);
            }
        });
        setAdapter(adapter);
        LayoutManager mLayoutManager = new LinearLayoutManager(context.getApplicationContext());
        //context.getApplicationContext().getPackageManager().
        setLayoutManager(mLayoutManager);
        setItemAnimator(new DefaultItemAnimator());
        setAdapter(adapter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if ((requestCode & 0x12000) != 0x12000)
            return;
        requestCode = requestCode &~0x12000;
        if (resultCode == 0){
            MutableTask tsk = (MutableTask)adapter.getTasks().get(requestCode);
            tsk.setName(data.getStringExtra("name"));
            tsk.setDescription(data.getStringExtra("description"));
        }
    }

    public List<Task> getTasks() {
        return adapter.getTasks();
    }
}
