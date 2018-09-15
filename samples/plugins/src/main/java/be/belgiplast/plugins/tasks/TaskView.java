package be.belgiplast.plugins.tasks;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import be.belgiplast.plugins.R;

public class TaskView extends ConstraintLayout implements Task{
    private ImageView icon;
    private TextView name;
    private TextView progressText;
    private ProgressBar progress;

    private WeakReference<Task> binding;

    private TaskClickListener listener;

    public TaskView(Context context) {
        this(context,null);
    }

    public TaskView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.task_item,this);
        icon = findViewById(R.id.icon);
        name = findViewById(R.id.name);
        progressText = findViewById(R.id.progress);
        progress = findViewById(R.id.progressBar);
        progress.setMax(100);
    }

    public TaskClickListener getListener() {
        return listener;
    }

    public void setListener(TaskClickListener listener) {
        this.listener = listener;
    }

    public void setProgress(int value){
        if (value < 0) value = 0;
        if (value > 100) value = 100;
        progressText.setText(String.format("%d %",value));
        progress.setProgress(value);
    }

    public void bind(final Task task){
        binding = new WeakReference<>(task);
        icon.setImageResource(task.getIcon());
        name.setText(task.getName());
        setProgress(task.getProgress());

        if (task instanceof Observable){
            ((Observable)task).addObserver(new Observer(){

                @Override
                public void update(Observable o, Object arg) {
                    icon.setImageResource(task.getIcon());
                    name.setText(task.getName());
                    setProgress(task.getProgress());
                }
            });
        }

        icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onTaskClicked(task);
            }
        });
    }

    @Override
    public int getIcon() {
        try{
            return binding.get().getIcon();
        }   catch (NullPointerException npe){
            return 0;
        }
    }

    @Override
    public String getName() {
        try{
            return binding.get().getName();
        }   catch (NullPointerException npe){
            return "";
        }
    }

    @Override
    public int getProgress() {
        try{
            return binding.get().getProgress();
        }   catch (NullPointerException npe){
            return 0;
        }
    }

}
