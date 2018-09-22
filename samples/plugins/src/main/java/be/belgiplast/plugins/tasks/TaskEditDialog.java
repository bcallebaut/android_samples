package be.belgiplast.plugins.tasks;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import be.belgiplast.plugins.R;

public class TaskEditDialog extends Dialog implements
        View.OnClickListener {
    public Context activity;
    public Button btnYes, btnNo;

    public TaskEditDialog(Context activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.task_edit_desc);
        btnYes = (Button) findViewById(R.id.okButton);
        //btnNo = (ImageButton) findViewById(R.id.);
        btnYes.setOnClickListener(this);
        //btnNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}

