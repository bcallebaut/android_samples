package be.belgiplast.plugins.tasks;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import be.belgiplast.plugins.R;

public class TaskEditor extends ConstraintLayout implements MutableTask{

    private String origName;
    private String origDescription;

    private TextView name;
    private TextView description;

    private MutableTask binding;

    private TaskClickListener listener;

    public TaskEditor(Context context) {
        this(context,null);
    }

    public TaskEditor(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TaskEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.task_editor,this);
        name = findViewById(R.id.taskName);
        description = findViewById(R.id.description);
    }

    public TaskClickListener getListener() {
        return listener;
    }

    public void setListener(TaskClickListener listener) {
        this.listener = listener;
    }

    public void cancel(){
        if (binding == null)
            return;
        binding.setName(origName);
        binding.setDescription(origDescription);
    }

    public void bind(final MutableTask task){
        origName = task.getName();
        origDescription = task.getDescription();

        binding = task;
        name.setText(task.getName());
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                task.setName(s.toString());
            }
        });
        description.setText(task.getDescription());
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                task.setDescription(s.toString());
            }
        });
    }

    @Override
    public int getIcon() {
        try{
            return binding.getIcon();
        }   catch (NullPointerException npe){
            return 0;
        }
    }

    @Override
    public String getName() {
        try{
            return binding.getName();
        }   catch (NullPointerException npe){
            return "";
        }
    }

    @Override
    public String getDescription() {
        try{
            return binding.getDescription();
        }   catch (NullPointerException npe){
            return "";
        }
    }

    @Override
    public int getProgress() {
        try{
            return binding.getProgress();
        }   catch (NullPointerException npe){
            return 0;
        }
    }

    public void setName(String name){
        binding.setName(name);
        origName = name;
        this.name.setText(name);
    }

    @Override
    public void setDescription(String description) {
        binding.setDescription(description);
        origDescription = description;
        this.description.setText(description);
    }

    @Override
    public void setIcon(int icon) {
        binding.setIcon(icon);
    }

    @Override
    public void setProgress(int progress) {
        binding.setProgress(progress);
    }
}
