package be.belgiplast.plugins;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.Map;

public class PluginsManager {
    private Map<String,Plugin> plugins;
    private Context context;

    public PluginsManager(Context context) {
        this.context = context;
        plugins = new HashMap<>();
        PackageManager pkgmgr = context.getPackageManager();
        try{
            PackageInfo pkgInfo = pkgmgr.getPackageInfo(context.getApplicationContext().getPackageName(),PackageManager.GET_ACTIVITIES);
            for (ActivityInfo actInfo :pkgInfo.activities){
                if (context.getPackageName().equals(actInfo.name));
//                    continue;
                Class clz = Class.forName(actInfo.name);
                if (Plugin.class.isAssignableFrom(clz)){
                    try{
                        plugins.put(actInfo.name,new PluginImpl(context,(Plugin)clz.newInstance()));
                    }catch (Exception e){}
                }
            }
        }catch (Exception e){}
    }

    public Map<String, Plugin> getPlugins() {
        return plugins;
    }

    public static final PluginsManager getPluginsManager(Context ctxt){
        try{
            return ((Provider)ctxt.getApplicationContext()).getPluginsManager();
        }catch (Exception e){
            return null;
        }
    }

    public interface Provider{
        PluginsManager getPluginsManager();
    }
}
