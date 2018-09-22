package be.belgiplast.plugins.tasks;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import be.belgiplast.plugins.R;

public class TaskProgressEditor extends ConstraintLayout implements MutableTask{

    private SeekBar progress;

    private MutableTask binding;

    private TaskClickListener listener;

    public TaskProgressEditor(Context context) {
        this(context,null);
    }

    public TaskProgressEditor(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TaskProgressEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.task_progress_editor,this);
        binding = new MutableTaskImpl();
        progress = findViewById(R.id.seekBar);

        progress.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                binding.setProgress(progress.getProgress());
            }
        });
    }

    public TaskClickListener getListener() {
        return listener;
    }

    public void setListener(TaskClickListener listener) {
        this.listener = listener;
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
            return progress.getProgress();
        }   catch (NullPointerException npe){
            return 0;
        }
    }

    public void setName(String name){
        binding.setName(name);
    }

    @Override
    public void setDescription(String description) {
        binding.setDescription(description);
    }

    @Override
    public void setIcon(int icon) {
        binding.setIcon(icon);
    }

    @Override
    public void setProgress(int progress) {
        binding.setProgress(progress);
        this.progress.setProgress(progress);
    }
}
