package be.belgiplast.plugins.tasks;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import be.belgiplast.plugins.CRUDInterface;
import be.belgiplast.plugins.PluginSettingsHolder;
import be.belgiplast.plugins.R;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> implements TaskClickListener{

    private LiveData<List<MutableTaskImpl>> tasks;
    private TaskClickListener listener;
    private TaskEditor editor;
    private TaskView view;
    private TaskViewModel src;

    public TaskAdapter() {
    }

    public TaskAdapter(TaskViewModel src) {
        this.src = src;
        tasks = src.getAllTasks();
    }

    public List<? extends Task> getTasks() {
        return tasks.getValue();
    }

    public final TaskClickListener getListener() {
        return listener;
    }

    public final void setListener(TaskClickListener listener) {
        this.listener = listener;
    }

    public int indexOf(Object o) {
        return getTasks().indexOf(o);
    }

    public void addTask(MutableTaskImpl task){
        src.insert(task);
        int index = indexOf(task);
        notifyItemInserted(index);
    }

    public void removeTask(MutableTaskImpl task){
        int index = indexOf(task);
        src.delete(task);
        notifyItemRemoved(index);
    }

//    public void setTasks(List<? extends Task> tasks) {
//        this.tasks.clear();
//        this.tasks.addAll(tasks);
//    }

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
        taskHolder.bind((MutableTaskImpl)tasks.getValue().get(i));
        taskHolder.setListener(listener);
    }

    @Override
    public int getItemCount() {
        try{
            return tasks.getValue().size();
        }catch (Exception e){
            return 0;

        }
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
                src.update((MutableTaskImpl)task);
            }
        }catch (Exception e){}
    }
}
