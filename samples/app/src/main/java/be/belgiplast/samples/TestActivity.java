package be.belgiplast.samples;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.JsonObjectRequest;

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
                //tv.addTask(new MutableTaskImpl());
                //new LoadTasks().execute(TestActivity.this);
                MutableTaskImpl tsk = new MutableTaskImpl();
                tsk.setName("foo");
                tsk.setDescription("bar");
                JsonObjectRequest req = ((PluginsApplication)getApplication()).getTaskOperations().getAddTask(tsk);
                ((PluginsApplication)getApplication()).getRequestQueue().add(req);
            }
        });
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        tv.onActivityResult(requestCode,resultCode,data);
    }
}
