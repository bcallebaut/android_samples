package be.belgiplast.samples;

import android.app.Application;

import be.belgiplast.plugins.PluginSettings;
import be.belgiplast.plugins.PluginsManager;

public class PluginsApplication extends Application implements PluginsManager.Provider, PluginSettings.Provider {
    private PluginsManager mgr;
    private PluginSettings settings;

    @Override
    public void onCreate() {
        super.onCreate();
        mgr = new PluginsManager(this);
        settings = new PluginSettingsImpl();
    }

    @Override
    public final PluginsManager getPluginsManager() {
        return mgr;
    }

    @Override
    public final PluginSettings getPluginSetting() {
        return settings;
    }
}
