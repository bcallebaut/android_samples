package be.belgiplast.samples;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.TextView;

public class NavDrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private com.trncic.library.DottedProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        pb = findViewById(R.id.progress);


        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setItemHorizontalPadding(16);
        navigationView.setBackgroundResource(R.drawable.item_gradient);
        navigationView.setItemBackgroundResource(R.drawable.item_gradient2);
        TextView header = new TextView(this);
        header.setText("Blah");
        navigationView.addHeaderView(header);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
        //((NavigationMenuView)navigationView.getChildAt(0)).addItemDecoration(new DividerItemDecoration(NavDrawerActivity.this,DividerItemDecoration.VERTICAL));
        SubMenu group1 = navigationView.getMenu().addSubMenu("Preferred");

        group1.add("Import");
        group1.add("Gallery");
        SubMenu group2 = navigationView.getMenu().addSubMenu("General");
        group2.add("Slideshow");
        group2.add("Manage");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_black_18dp);

        NavigationAdapter adapter = new NavigationAdapter(this);
        TextView v1 = new TextView(this);
        v1.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        v1.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        v1.setText("page 1");
        adapter.add(v1);
        v1 = new TextView(this);
        v1.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        v1.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        v1.setText("page 2");
        adapter.add(v1);
        v1 = new TextView(this);
        v1.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        v1.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        v1.setText("page 3");
        adapter.add(v1);

        final AdapterViewFlipper simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper); // get the reference of AdapterViewFlipper
        simpleAdapterViewFlipper.setAdapter(adapter); // set adapter for AdapterViewFlipper. Here adapter is object of custom adapter
//        simpleAdapterViewFlipper.startFlipping();
//        simpleAdapterViewFlipper.setFlipInterval(4000); // set 4 seconds for interval time
/*
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
*/
        findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simpleAdapterViewFlipper.getDisplayedChild() > 0) {
                    simpleAdapterViewFlipper.showPrevious();
                    //tabLayout.set
                }

            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simpleAdapterViewFlipper.getCount()  > simpleAdapterViewFlipper.getDisplayedChild() + 1)
                    simpleAdapterViewFlipper.showNext();
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
