package be.belgiplast.samples;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.TextView;

import static be.belgiplast.samples.R.layout.bottom_nav;

public class TabbedView extends ConstraintLayout {

    private ViewPager simpleAdapterViewFlipper;
    private NavigationAdapter adapter;
    private LayoutInflater inflater;

    public TabbedView(Context context) {
        this(context, null);

    }

    public boolean add(String name,Fragment fragment) {
        adapter.add(name,fragment);
        ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", simpleAdapterViewFlipper.getCurrentItem() + 1, adapter.getCount()));
        return true;
    }

    public boolean add(String name,int res) {
        adapter.add(name,res);
        return true;
    }

    public View getItem(int position) {
        return adapter.getItem(position).getView();
    }

    public TabbedView(Context context, @Nullable AttributeSet attrs){
        this(context,attrs,R.style.Theme_TabbedView);
    }

    public TabbedView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs,defStyle);

        String backStr = context.getString(R.string.prev);
        String nextStr = context.getString(R.string.next);

        /*
        TypedArray typedArray;
        typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.TabbedView);

        mSpinnerValues = typedArray
                .getTextArray(R.styleable.SideSpinner_values);
        typedArray.recycle();
        */

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(bottom_nav, this);

        adapter = new NavigationAdapter(((FragmentActivity)context).getSupportFragmentManager());

        simpleAdapterViewFlipper = (ViewPager ) findViewById(R.id.simpleAdapterViewFlipper); // get the reference of AdapterViewFlipper
        simpleAdapterViewFlipper.setAdapter(adapter); // set adapter for AdapterViewFlipper. Here adapter is object of custom adapter
        simpleAdapterViewFlipper.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", position + 1, simpleAdapterViewFlipper.getChildCount()));
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", position + 1, simpleAdapterViewFlipper.getChildCount()));
            }
        });


        ((Button)findViewById(R.id.prev)).setText(backStr);
        findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (simpleAdapterViewFlipper.getChildCount() == 0){
//                    simpleAdapterViewFlipper.setCurrentItem(0);
//                }
                if (simpleAdapterViewFlipper.getChildCount() > 0) {
                    simpleAdapterViewFlipper.setCurrentItem(simpleAdapterViewFlipper.getCurrentItem() -1);
                    ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", simpleAdapterViewFlipper.getCurrentItem() + 1, simpleAdapterViewFlipper.getChildCount()));
                    //tabLayout.set
                }

            }
        });
        ((Button)findViewById(R.id.next)).setText(nextStr);
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (simpleAdapterViewFlipper.getChildCount() == 0){
//                    simpleAdapterViewFlipper.setCurrentItem(0);
//                }
//                if (simpleAdapterViewFlipper.getCurrentItem() > simpleAdapterViewFlipper.getChildCount() + 1) {
                    simpleAdapterViewFlipper.setCurrentItem(simpleAdapterViewFlipper.getCurrentItem() + 1);
//                }
            }
        });
        ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", simpleAdapterViewFlipper.getCurrentItem() + 1, simpleAdapterViewFlipper.getChildCount()));
    }
}
