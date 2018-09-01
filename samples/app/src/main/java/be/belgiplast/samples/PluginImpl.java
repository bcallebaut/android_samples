package be.belgiplast.samples;

import android.app.Activity;

public class PluginImpl implements Plugin {
    private String name;
    private int imageRes;
    private String description;
    private Class<? extends Activity> implClass;

    public PluginImpl(String name, int imageRes, String description, Class<Activity> implClass) {
        this.name = name;
        this.imageRes = imageRes;
        this.description = description;
        this.implClass = implClass;
    }

    public PluginImpl(Plugin plugin) {
        this.name = plugin.getName();
        this.imageRes = plugin.getImageResource();
        this.description = plugin.getDescription();
        this.implClass = (Class<? extends Activity>)plugin.getClass();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getImageResource() {
        return imageRes;
    }

    public void setImageResource(int imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class<? extends Activity> getImplClass() {
        return implClass;
    }
}
