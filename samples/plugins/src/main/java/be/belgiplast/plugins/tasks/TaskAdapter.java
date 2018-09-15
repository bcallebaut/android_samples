package be.belgiplast.plugins.tasks;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> implements TaskClickListener{

    private List<Task> tasks;
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
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TaskView view = new TaskView(viewGroup.getContext());
        viewGroup.addView(view);
        return new TaskHolder(view);
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
