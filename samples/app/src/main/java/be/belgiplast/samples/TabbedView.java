package be.belgiplast.samples;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
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
        return adapter.add(view);
    }

    public boolean add(int res) {
        return adapter.add(inflater.inflate(res,this));
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

    public TabbedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(bottom_nav, this);

        adapter = new NavigationAdapter(this.getContext().getApplicationContext());

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper); // get the reference of AdapterViewFlipper
        simpleAdapterViewFlipper.setAdapter(adapter); // set adapter for AdapterViewFlipper. Here adapter is object of custom adapter

        findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simpleAdapterViewFlipper.getDisplayedChild() > 0) {
                    simpleAdapterViewFlipper.showPrevious();
                    ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", simpleAdapterViewFlipper.getDisplayedChild() + 1, simpleAdapterViewFlipper.getCount()));
                    //tabLayout.set
                }

            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simpleAdapterViewFlipper.getCount() > simpleAdapterViewFlipper.getDisplayedChild() + 1) {
                    simpleAdapterViewFlipper.showNext();
                    try {
                        ((TextView) findViewById(R.id.progress)).setText(String.format("%d / %d", simpleAdapterViewFlipper.getDisplayedChild() + 1, simpleAdapterViewFlipper.getCount()));
                    } catch (NullPointerException e) {
                    }
                }
            }
        });
    }
}
