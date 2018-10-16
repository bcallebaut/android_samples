package be.belgiplast.notes.ui;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

public class NoteItemView extends ConstraintLayout {
    public NoteItemView(Context context) {
        this(context, null);
    }

    public NoteItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoteItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
