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
                    MenuItem item = new MenuItem() {
                        @Override
                        public int getItemId() {
                            return j;
                        }

                        @Override
                        public int getGroupId() {
                            return 0;
                        }

                        @Override
                        public int getOrder() {
                            return j;
                        }

                        @Override
                        public MenuItem setTitle(CharSequence title) {
                            return this;
                        }

                        @Override
                        public MenuItem setTitle(int title) {
                            return this;
                        }

                        @Override
                        public CharSequence getTitle() {
                            return setting.getName();
                        }

                        @Override
                        public MenuItem setTitleCondensed(CharSequence title) {
                            return this;
                        }

                        @Override
                        public CharSequence getTitleCondensed() {
                            return setting.getName();
                        }

                        @Override
                        public MenuItem setIcon(Drawable icon) {
                            return this;
                        }

                        @Override
                        public MenuItem setIcon(int iconRes) {
                            return this;
                        }

                        @Override
                        public Drawable getIcon() {
                            return view.getResources().getDrawable(setting.getIcon(), view.getContext().getTheme());
                        }

                        @Override
                        public MenuItem setIntent(Intent intent) {
                            return this;
                        }

                        @Override
                        public Intent getIntent() {
                            return null;
                        }

                        @Override
                        public MenuItem setShortcut(char numericChar, char alphaChar) {
                            return this;
                        }

                        @Override
                        public MenuItem setNumericShortcut(char numericChar) {
                            return this;
                        }

                        @Override
                        public char getNumericShortcut() {
                            return 0;
                        }

                        @Override
                        public MenuItem setAlphabeticShortcut(char alphaChar) {
                            return this;
                        }

                        @Override
                        public char getAlphabeticShortcut() {
                            return 0;
                        }

                        @Override
                        public MenuItem setCheckable(boolean checkable) {
                            return this;
                        }

                        @Override
                        public boolean isCheckable() {
                            return false;
                        }

                        @Override
                        public MenuItem setChecked(boolean checked) {
                            return this;
                        }

                        @Override
                        public boolean isChecked() {
                            return false;
                        }

                        @Override
                        public MenuItem setVisible(boolean visible) {
                            return this;
                        }

                        @Override
                        public boolean isVisible() {
                            return true;
                        }

                        @Override
                        public MenuItem setEnabled(boolean enabled) {
                            return this;
                        }

                        @Override
                        public boolean isEnabled() {
                            return true;
                        }

                        @Override
                        public boolean hasSubMenu() {
                            return false;
                        }

                        @Override
                        public SubMenu getSubMenu() {
                            return null;
                        }

                        @Override
                        public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
                            return this;
                        }

                        @Override
                        public ContextMenu.ContextMenuInfo getMenuInfo() {
                            return null;
                        }

                        @Override
                        public void setShowAsAction(int actionEnum) {

                        }

                        @Override
                        public MenuItem setShowAsActionFlags(int actionEnum) {
                            return this;
                        }

                        @Override
                        public MenuItem setActionView(View view) {
                            return this;
                        }

                        @Override
                        public MenuItem setActionView(int resId) {
                            return this;
                        }

                        @Override
                        public View getActionView() {
                            return null;
                        }

                        @Override
                        public MenuItem setActionProvider(ActionProvider actionProvider) {
                            return null;
                        }

                        @Override
                        public ActionProvider getActionProvider() {
                            return null;
                        }

                        @Override
                        public boolean expandActionView() {
                            return false;
                        }

                        @Override
                        public boolean collapseActionView() {
                            return false;
                        }

                        @Override
                        public boolean isActionViewExpanded() {
                            return false;
                        }

                        @Override
                        public MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
                            return null;
                        }
                    };
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
