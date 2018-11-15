package be.belgiplast.notes.ui;

import android.view.View;
import android.view.ViewParent;

public class OkCancelProvider {
    public static OkCancelListener findListener(View v){
        if (v instanceof OkCancelListener)
            return (OkCancelListener)v;
        ViewParent vp = v.getParent();
        if (vp == null)
            return null;
        return findListener(vp);
    }

    public static OkCancelListener findListener(ViewParent v){
        if (v instanceof OkCancelListener)
            return (OkCancelListener)v;
        ViewParent vp = v.getParent();
        if (vp == null)
            return null;
        return findListener(vp);
    }
}
