package be.belgiplast.samples;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewFragment extends Fragment {
    private View view;

    public ViewFragment() {
    }

    @SuppressLint("ValidFragment")
    public ViewFragment(View view) {
        this.view= view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //container.addView(view);
        return view;
    }
}
