package be.belgiplast.plugins.tasks;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import be.belgiplast.plugins.R;

class TaskHolder extends RecyclerView.ViewHolder implements TaskClickListener{
    private TaskView view;
    private TaskClickListener listener;

    public TaskHolder(@NonNull View itemView) {
        super(itemView);
        this.view = (TaskView) itemView.findViewById(R.id.taskView);
    }

    @Override
    public void onTaskClicked(Task task) {
        if (listener != null)
            listener.onTaskClicked(task);
    }

    public final TaskClickListener getListener() {
        return listener;
    }

    public final void setListener(TaskClickListener listener) {
        this.listener = listener;
    }

    public final void bind(Task data){
        view.bind(data);
        view.setListener(listener);
    }
}
