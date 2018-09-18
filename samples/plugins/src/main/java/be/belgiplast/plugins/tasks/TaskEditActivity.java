package be.belgiplast.plugins.tasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import be.belgiplast.plugins.R;

public class TaskEditActivity extends AppCompatActivity {

    private TaskEditor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit);

        final ImageButton cancel = (ImageButton)findViewById(R.id.cancelBtn);
        final ImageButton save = (ImageButton)findViewById(R.id.saveBtn);
        editor = findViewById(R.id.taskeditor);
        editor.bind(new MutableTaskImpl());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.cancel();
                Intent intent = getIntent();
                intent.putExtra("name",editor.getName());
                intent.putExtra("description",editor.getDescription());
                setResult(1,intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("name",editor.getName());
                intent.putExtra("description",editor.getDescription());
                setResult(0,intent);
                finish();
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Intent intent = getIntent();
        if (intent != null){
            if (intent.getStringExtra("name") != null){
                editor.setName(intent.getStringExtra("name"));
            }
            if (intent.getStringExtra("description") != null){
                editor.setDescription(intent.getStringExtra("description"));
            }
        }
    }
}
