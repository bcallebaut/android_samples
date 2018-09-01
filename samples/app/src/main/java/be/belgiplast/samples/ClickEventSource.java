package be.belgiplast.samples;

import android.view.View;

public interface ClickEventSource {
    void registerClickListener(int res, View.OnClickListener listener);
    void registerClickListener(View view, View.OnClickListener listener);
    void unregisterClickListener(int res, View.OnClickListener listener);
    void unregisterClickListener(View view, View.OnClickListener listener);
}
