package be.belgiplast.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.belgiplast.plugins.tasks.Task;
import be.belgiplast.plugins.tasks.TasksView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        /*
        TasksView tv = (TasksView)findViewById(R.id.testview);
        tv.getTasks().add(new Task(){

            @Override
            public int getIcon() {
                return R.drawable.ic_add;
            }

            @Override
            public String getName() {
                return "blah";
            }

            @Override
            public String getDescription() {
                return "blah blah";
            }

            @Override
            public int getProgress() {
                return 75;
            }
        });
        */
    }
}
