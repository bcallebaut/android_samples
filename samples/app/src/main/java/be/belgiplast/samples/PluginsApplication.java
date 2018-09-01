package be.belgiplast.samples;

import android.app.Application;

public class PluginsApplication extends Application implements PluginsManager.Provider{
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
}
