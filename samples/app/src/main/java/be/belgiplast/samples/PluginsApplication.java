package be.belgiplast.samples;

import android.app.Application;
import android.databinding.ObservableArrayList;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

import be.belgiplast.plugins.ObservableProvider;
import be.belgiplast.plugins.PluginSettings;
import be.belgiplast.plugins.PluginsManager;
import be.belgiplast.plugins.RequestQueueProvider;
import be.belgiplast.plugins.tasks.MutableTaskImpl;
import be.belgiplast.plugins.tasks.TasksNetworker;

public class PluginsApplication extends Application implements PluginsManager.Provider, PluginSettings.Provider, RequestQueueProvider, ObservableProvider<MutableTaskImpl> {
    private PluginsManager mgr;
    private PluginSettings settings;
    private DiskBasedCache cache;
    private RequestQueue queue;
    private Network network;
    private ObservableArrayList<MutableTaskImpl> backend = new ObservableArrayList<>();
    private TasksNetworker taskOperations;

    @Override
    public void onCreate() {
        super.onCreate();
        mgr = new PluginsManager(this);
        settings = new PluginSettingsImpl();
        cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        network = new BasicNetwork(new HurlStack());
        queue = new RequestQueue(cache, network);
        queue.start();
        taskOperations = new TasksNetworker(this);
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

    @Override
    public ObservableArrayList<MutableTaskImpl> getObservable() {
        return backend;
    }

    public TasksNetworker getTaskOperations() {
        return taskOperations;
    }
}
