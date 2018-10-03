package be.belgiplast.samples;

import android.app.Application;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

import be.belgiplast.plugins.PluginSettings;
import be.belgiplast.plugins.PluginsManager;
import be.belgiplast.plugins.RequestQueueProvider;

public class PluginsApplication extends Application implements PluginsManager.Provider, PluginSettings.Provider, RequestQueueProvider {
    private PluginsManager mgr;
    private PluginSettings settings;
    private DiskBasedCache cache;
    private RequestQueue queue;
    private Network network;

    @Override
    public void onCreate() {
        super.onCreate();
        mgr = new PluginsManager(this);
        settings = new PluginSettingsImpl();
        cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        network = new BasicNetwork(new HurlStack());
        queue = new RequestQueue(cache, network);
        queue.start();
    }

    @Override
    public final PluginsManager getPluginsManager() {
        return mgr;
    }

    @Override
    public final PluginSettings getPluginSetting() {
        return settings;
    }

    @Override
    public RequestQueue getRequestQueue() {
        return queue;
    }
}
