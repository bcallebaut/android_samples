package be.belgiplast.plugins.tasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import be.belgiplast.plugins.R;

public class TaskEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit);

        final ImageButton cancel = (ImageButton)findViewById(R.id.cancelBtn);
        final ImageButton save = (ImageButton)findViewById(R.id.saveBtn);
        final TaskEditor editor = findViewById(R.id.taskeditor);

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
}
