package be.belgiplast.samples;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class NavigationAdapter extends FragmentPagerAdapter {
    private List<String> viewNames = new ArrayList<>();
    private List<Fragment> views = new ArrayList<>();
    private LayoutInflater inflter;

    public NavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    public void add(String name,Fragment fragment){
        viewNames.add(name);
        views.add(fragment);
        super.notifyDataSetChanged();
    }

    public void add(String name,int res){
        viewNames.add(name);
        views.add(new NavigationFragment(res));
        super.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = views.get(i);
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        return fragment;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return viewNames.get(position);
    }
}

