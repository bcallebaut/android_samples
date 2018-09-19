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
                intent.putExtra("name",task.getName());
                intent.putExtra("description",task.getDescription());
                ((Activity)getContext()).startActivityForResult(intent, requestCode | 0x1200);
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
        if ((requestCode & 0x1200) != 0x1200)
            return;
        requestCode = requestCode &~0x1200;
        if (resultCode == 0){
            Object obj = adapter.getTasks().get(requestCode);
            try {
                MutableTask tsk = (MutableTask) obj;
                tsk.setName(data.getStringExtra("name"));
                tsk.setDescription(data.getStringExtra("description"));
                adapter.notifyItemChanged(requestCode);
            }catch (ClassCastException cce){
                cce.printStackTrace();
            }
        }
    }

    public List<Task> getTasks() {
        return adapter.getTasks();
    }
}
