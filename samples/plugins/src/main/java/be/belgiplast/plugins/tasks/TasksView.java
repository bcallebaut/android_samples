package be.belgiplast.plugins.tasks;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

import be.belgiplast.plugins.R;

public class TasksView extends RecyclerView {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private TaskAdapter adapter;
    private TaskViewModel mWordViewModel;

    public TasksView(Context context) {
        this(context, null,0);
    }

    public TasksView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TasksView(final Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        adapter = new TaskAdapter();
        mWordViewModel = ViewModelProviders.of((FragmentActivity)context).get(TaskViewModel.class);
        mWordViewModel.getAllTasks().observe((FragmentActivity)context, new Observer<List<MutableTaskImpl>>() {
            @Override
            public void onChanged(@Nullable final List<MutableTaskImpl> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setTasks(words);
            }
        });
        adapter.setListener(new TaskClickListener(){

            @Override
            public void onTaskClicked(Task task) {
                int requestCode = adapter.indexOf(task);

                final TaskEditDialog dialog = new TaskEditDialog(context);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                /*
                final Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.activity_task_edit);
                dialog.setTitle("Edit Task");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TaskEditor tv = dialog.findViewById(R.id.taskeditor);
                try {
                    tv.setName(task.getName());
                    tv.setDescription(task.getDescription());
                }catch (NullPointerException npe){
                    npe.printStackTrace();
                }

                ImageButton dialogButton = (ImageButton) dialog.findViewById(R.id.saveBtn);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
*/


                dialog.show();

                //TaskEditFragment newFragment = new TaskEditFragment();
                //FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
                //newFragment.setArguments(new Bundle());
                //newFragment.getArguments().putString("name",task.getName());
                //newFragment.getArguments().putString("description",task.getDescription());
                //newFragment.show(fm, "missiles");

                /*
                Intent intent = new Intent(getContext(),TaskEditActivity.class);
                intent.putExtra("name",task.getName());
                intent.putExtra("description",task.getDescription());
                ((Activity)getContext()).startActivityForResult(intent, requestCode | 0x1200);
                */
            }
        });
        setAdapter(adapter);
        LayoutManager mLayoutManager = new LinearLayoutManager(context.getApplicationContext());
        //context.getApplicationContext().getPackageManager().
        setLayoutManager(mLayoutManager);
        setItemAnimator(new DefaultItemAnimator());
        setAdapter(adapter);
    }

    public void addTask(MutableTask task) {
        adapter.addTask(task);
        this.invalidate();
    }

    public void removeTask(MutableTask task) {
        adapter.removeTask(task);
        this.invalidate();
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
        return (List<Task>)adapter.getTasks();
    }
}
