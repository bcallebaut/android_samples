package be.belgiplast.samples;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.AdapterViewFlipper;
import android.widget.TextView;

import be.belgiplast.plugins.Plugin;
import be.belgiplast.plugins.PluginsManager;

public class NavDrawerActivity extends AppCompatActivity implements Runnable, Plugin {

    private DrawerLayout mDrawerLayout;
    private com.trncic.library.DottedProgressBar pb;
    private AdapterViewFlipper simpleAdapterViewFlipper;
    private PluginsManager plugins;
    private PreferredPluginsTracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        //pb = findViewById(R.id.progress);

        plugins = new PluginsManager(this);
        for (Plugin plugin : plugins.getPlugins().values()){
            System.out.println(plugin.getName());
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setItemHorizontalPadding(16);
        //navigationView.setBackgroundResource(R.drawable.item_gradient);
        //navigationView.setItemBackgroundResource(R.drawable.item_gradient2);
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
        tracker = new PreferredPluginsTracker(navigationView,((PluginsApplication)getApplicationContext()).getPluginSetting());

        group1.add("Import");
        group1.add("Gallery");
        SubMenu group2 = navigationView.getMenu().addSubMenu("General");
        group2.add("Slideshow");
        group2.add("Manage");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_white_18dp);

        TabbedView tv = findViewById(R.id.tabbedView);
        tv.add("test1",R.layout.test1);
        tv.add("test2",R.layout.test2);
        tv.add("test3",R.layout.test3);
        tv.add("test4",R.layout.test4);
        /*

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

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper); // get the reference of AdapterViewFlipper
        simpleAdapterViewFlipper.setAdapter(adapter); // set adapter for AdapterViewFlipper. Here adapter is object of custom adapter
//        simpleAdapterViewFlipper.startFlipping();
//        simpleAdapterViewFlipper.setFlipInterval(4000); // set 4 seconds for interval time
*/
/*
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
*/
/*
        findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simpleAdapterViewFlipper.getDisplayedChild() > 0) {
                    simpleAdapterViewFlipper.showPrevious();
                    ((TextView)findViewById(R.id.progress)).setText(String.format("%d / %d",simpleAdapterViewFlipper.getDisplayedChild() + 1 ,simpleAdapterViewFlipper.getCount()));
                    //tabLayout.set
                }

            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simpleAdapterViewFlipper.getCount()  > simpleAdapterViewFlipper.getDisplayedChild() + 1) {
                    simpleAdapterViewFlipper.showNext();
                    try{
                        ((TextView)findViewById(R.id.progress)).setText(String.format("%d / %d",simpleAdapterViewFlipper.getDisplayedChild() + 1 ,simpleAdapterViewFlipper.getCount()));
                    }catch (NullPointerException e){}
                }
            }
        });
*/


    }

    @Override
    protected void onResume() {
        super.onResume();
//        ((TextView)findViewById(R.id.progress)).setText(String.format("%d / %d",simpleAdapterViewFlipper.getDisplayedChild() + 1 ,simpleAdapterViewFlipper.getCount()));
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

    @Override
    public void run() {

    }

    @Override
    public String getName() {
        return "Mauris faucibus";
    }

    @Override
    public int getImageResource() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Mauris faucibus ex vitae urna porta fringilla. Aliquam hendrerit, ex sed consectetur commodo, augue quam rhoncus nisi, sit amet blandit mi est non arcu. Sed eros justo, dictum vel commodo quis, iaculis at sapien. Sed sagittis elementum arcu ac porttitor. Donec viverra urna tortor, quis blandit nisl viverra a. Quisque nec felis nisl. Nullam justo eros, tristique ut orci vitae, tempor pulvinar nibh. Donec hendrerit gravida mauris et maximus. Sed ultricies vestibulum tortor, non molestie nisi aliquet vel. Vestibulum elementum ultrices tincidunt. Vestibulum hendrerit sagittis tempus. Etiam sapien nibh, sagittis vel imperdiet ac, iaculis gravida sapien. ";
    }
}
