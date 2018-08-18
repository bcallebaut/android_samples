package be.belgiplast.samples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class NavigationAdapter extends BaseAdapter {
    private Context context;
    private List<View> views = new ArrayList<>();
    private LayoutInflater inflter;

    public NavigationAdapter(Context applicationContext) {
        this.context = context;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public boolean add(View view) {
        return views.add(view);
    }

    public void clear() {
        views.clear();
    }

    public View remove(int i) {
        return views.remove(i);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object getItem(int position) {
        return views.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = views.get(position);
        return view;
    }
}
