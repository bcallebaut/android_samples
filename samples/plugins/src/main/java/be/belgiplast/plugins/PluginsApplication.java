package be.belgiplast.plugins;

import android.app.Application;

public class PluginsApplication extends Application implements PluginsManager.Provider, PluginSettings.Provider{
    private PluginsManager mgr;

    @Override
    public void onCreate() {
        super.onCreate();
        mgr = new PluginsManager(this);
    }

    @Override
    public PluginsManager getPluginsManager() {
        return mgr;
    }

    @Override
    public PluginSettings getPluginSetting() {
        return null;
    }
}
