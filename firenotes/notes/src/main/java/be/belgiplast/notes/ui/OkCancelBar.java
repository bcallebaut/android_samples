package be.belgiplast.notes.ui;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.design.button.MaterialButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import be.belgiplast.notes.R;
import be.belgiplast.notes.model.Note;

public class OkCancelBar extends ConstraintLayout {
    private MaterialButton ok;
    private MaterialButton cancel;
    private OkCancelListener listener = null;

    public OkCancelBar(Context context) {
        this(context, null);
    }

    public OkCancelBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OkCancelBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ok_cancel_view, this, true);

        ok = findViewById(R.id.ok_button);
        cancel = findViewById(R.id.cancel_button);

        ok.setOnClickListener(v ->{
            findListener();
            listener.onOK(OkCancelBar.this);
        });
        cancel.setOnClickListener(v ->{
            findListener();
            listener.onCancel(OkCancelBar.this);
        });

        this.setFocusable(true);
    }

    private void findListener() {
        if (listener == null)
            listener = OkCancelProvider.findListener((View)OkCancelBar.this);
        if (listener == null)
            listener = new OkCancelListener(){
                @Override
                public void onOK(View v) {}
                @Override
                public void onCancel(View v) {}
            };
    }
}
