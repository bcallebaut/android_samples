package be.belgiplast.plugins.tasks;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import be.belgiplast.plugins.R;

class TaskHolder extends RecyclerView.ViewHolder implements TaskPartClickListener{
    private TaskView view;
    private TaskEditor editor;
    private TaskProgressEditor progress;
    private TaskClickListener listener;
    private MutableTaskImpl task;

    public TaskHolder(@NonNull View itemView) {
        super(itemView);
        this.view = (TaskView) itemView.findViewById(R.id.taskView);
        this.editor= (TaskEditor) itemView.findViewById(R.id.taskEditor);
        this.progress= (TaskProgressEditor) itemView.findViewById(R.id.taskProgressEditor);
        this.editor.setVisibility(View.GONE);
        this.progress.setVisibility(View.GONE);
    }

    public final TaskClickListener getListener() {
        return listener;
    }

    public final void setListener(TaskClickListener listener) {
        this.listener = listener;
    }

    public final void bind(MutableTaskImpl data){
        this.task = data;
        view.bind(data);
        view.setListener(this);
    }

    @Override
    public void nameClicked(boolean visible) {
        if (editor.getVisibility() == View.GONE){
            editor.setVisibility(View.VISIBLE);
        }else {
            editor.setVisibility(View.GONE);
            view.setName(editor.getName());
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        progress.setVisibility(View.GONE);
        listener.onTaskClicked(task);
    }

    @Override
    public void progressClicked(boolean visible) {
        if (progress.getVisibility() == View.GONE){
            progress.setVisibility(View.VISIBLE);
        }else {
            progress.setVisibility(View.GONE);
            view.setProgress(progress.getProgress());
        }
        editor.setVisibility(View.GONE);
        listener.onTaskClicked(task);
    }

    @Override
    public void iconClicked(boolean visible) {
        listener.onTaskClicked(task);
    }
}
