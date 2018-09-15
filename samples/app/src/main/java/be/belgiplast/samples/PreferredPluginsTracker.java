package be.belgiplast.samples;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import be.belgiplast.plugins.PluginSetting;
import be.belgiplast.plugins.PluginSettings;

public class PreferredPluginsTracker {
    private NavigationView view;
    private PluginSettings settings;

    public PreferredPluginsTracker(final NavigationView view, PluginSettings settings) {
        this.view = view;
        this.settings = settings;
        settings.addListener(new PluginSettings.ContentListener(){

            @Override
            public void notifyDatasetChanged() {
                Menu menu = PreferredPluginsTracker.this.view.getMenu();
                menu.clear();
                for (int i = 0; i < PreferredPluginsTracker.this.settings.getCount() ; i++){
                    final PluginSetting setting = PreferredPluginsTracker.this.settings.get(i);
                    final int j = i;
                    MenuItem item = menu.add(setting.getName());
                    item.setIcon(setting.getIcon());

                    item.setIntent(setting.getIntent());
                    item.setCheckable(false);
                    item.setEnabled(true);

                    item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            view.getContext().startActivity(item.getIntent());
                            return false;
                        }
                    });

                }
            }

            @Override
            public void notifyMoveUp(int position) {

            }

            @Override
            public void notifyMoveDown(int position) {

            }
        });
    }
}
