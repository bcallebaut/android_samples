package be.belgiplast.plugins.tasks;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import be.belgiplast.plugins.PluginSettingsHolder;
import be.belgiplast.plugins.R;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> implements TaskClickListener{

    private List<Task> tasks = new ArrayList<>();
    private TaskClickListener listener;

    public TaskAdapter() {
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public final TaskClickListener getListener() {
        return listener;
    }

    public final void setListener(TaskClickListener listener) {
        this.listener = listener;
    }

    public int indexOf(Object o) {
        return tasks.indexOf(o);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        /*
        if (tasks instanceof MutableList){
            try{
                ((MutableList<Task>)tasks).setListener(new MutableListListener<Task>() {
                    @Override
                    public void itemAdded(Task item, int position) {
                        //TaskAdapter.this.tasks
                    }

                    @Override
                    public void itemRemoved(Task item, int position) {

                    }

                    @Override
                    public void itemMoved(Task item, int position, int target) {

                    }
                });
            }catch (Exception e){

            }
        }
        */
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        TaskHolder holder = new TaskHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder taskHolder, int i) {
        taskHolder.bind(tasks.get(i));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public void onTaskClicked(Task task) {
        if (listener != null)
            listener.onTaskClicked(task);
    }
}
