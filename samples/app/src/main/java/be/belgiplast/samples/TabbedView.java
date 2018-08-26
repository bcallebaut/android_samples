package be.belgiplast.samples;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.TextView;

import static be.belgiplast.samples.R.layout.bottom_nav;

public class TabbedView extends ConstraintLayout {

    private AdapterViewFlipper simpleAdapterViewFlipper;
    private NavigationAdapter adapter;
    private LayoutInflater inflater;

    public TabbedView(Context context) {
        this(context, null);
    }

    public boolean add(View view) {
        boolean result = adapter.add(view);
        ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", simpleAdapterViewFlipper.getDisplayedChild() + 1, adapter.getCount()));
        return result;
    }

    public boolean add(int res) {
        boolean result = adapter.add(inflater.inflate(res,this));
        ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", simpleAdapterViewFlipper.getDisplayedChild() + 1, adapter.getCount()));
        return result;
    }

    public void clear() {
        adapter.clear();
    }

    public View remove(int i) {
        return adapter.remove(i);
    }

    public View getItem(int position) {
        return (View)adapter.getItem(position);
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

        adapter = new NavigationAdapter(this.getContext().getApplicationContext());

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper); // get the reference of AdapterViewFlipper
        simpleAdapterViewFlipper.setAdapter(adapter); // set adapter for AdapterViewFlipper. Here adapter is object of custom adapter


        ((Button)findViewById(R.id.prev)).setText(backStr);
        findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simpleAdapterViewFlipper.getChildCount() == 0){
                    simpleAdapterViewFlipper.setDisplayedChild(0);
                }
                if (simpleAdapterViewFlipper.getDisplayedChild() > 0) {
                    simpleAdapterViewFlipper.showPrevious();
                    ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", simpleAdapterViewFlipper.getDisplayedChild() + 1, simpleAdapterViewFlipper.getCount()));
                    //tabLayout.set
                }

            }
        });
        ((Button)findViewById(R.id.next)).setText(nextStr);
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simpleAdapterViewFlipper.getChildCount() == 0){
                    simpleAdapterViewFlipper.setDisplayedChild(0);
                }
                if (simpleAdapterViewFlipper.getCount() > simpleAdapterViewFlipper.getDisplayedChild() + 1) {
                    simpleAdapterViewFlipper.showNext();
                    try {
                        ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", simpleAdapterViewFlipper.getDisplayedChild() + 1, simpleAdapterViewFlipper.getCount()));
                    } catch (NullPointerException e) {
                    }
                }
            }
        });
        ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", simpleAdapterViewFlipper.getDisplayedChild() + 1, simpleAdapterViewFlipper.getCount()));
    }
}
