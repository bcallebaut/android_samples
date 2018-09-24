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
    private TaskEditor editor;
    private TaskView view;

    public TaskAdapter() {
    }

    public List<? extends Task> getTasks() {
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

    public void addTask(MutableTask task){
        tasks.add((Task)task);
        int index = tasks.indexOf(task);
        notifyItemInserted(index);
    }

    public void removeTask(MutableTask task){
        int index = tasks.indexOf(task);
        tasks.remove(task);
        notifyItemRemoved(index);
    }

    public void setTasks(List<? extends Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        view = v.findViewById(R.id.taskView);
        editor = v.findViewById(R.id.taskEditor);
        try{
            editor.setVisibility(View.GONE);
        }catch (Exception e){

        }
        TaskHolder holder = new TaskHolder(v);
        holder.setListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder taskHolder, int i) {
        taskHolder.bind((MutableTask)tasks.get(i));
        taskHolder.setListener(listener);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public void onTaskClicked(Task task) {
        //if (listener != null)
        //    listener.onTaskClicked(task);
        try {
            if (editor.getVisibility() == View.GONE)
                editor.setVisibility(View.VISIBLE);
            else {
                editor.setVisibility(View.GONE);
                view.setName(editor.getName());
                view.setDescription(editor.getDescription());
            }
        }catch (Exception e){}
    }
}
