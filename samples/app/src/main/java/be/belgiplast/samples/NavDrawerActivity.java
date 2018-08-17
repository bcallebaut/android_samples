package be.belgiplast.samples;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

public class NavDrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemHorizontalPadding(16);
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
