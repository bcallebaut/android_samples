package be.belgiplast.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import be.belgiplast.plugins.tasks.MutableTask;
import be.belgiplast.plugins.tasks.MutableTaskImpl;
import be.belgiplast.plugins.tasks.Task;
import be.belgiplast.plugins.tasks.TasksView;

public class TestActivity extends AppCompatActivity {

    TasksView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tv = (TasksView)findViewById(R.id.testview);
        //tv.getTasks().add(new MutableTaskImpl(R.drawable.ic_add,"blah","blah blah",75));
        ImageView iv = findViewById(R.id.imageView2);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.addTask(new MutableTaskImpl());
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        tv.onActivityResult(requestCode,resultCode,data);
    }
}
