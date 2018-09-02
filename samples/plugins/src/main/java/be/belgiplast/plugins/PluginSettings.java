package be.belgiplast.plugins;

public interface PluginSettings {
    PluginSetting get(int i);
    void add(PluginSetting setting);
    void remove(PluginSetting setting);
    void moveUp(PluginSetting setting);
    void moveDown(PluginSetting setting);
    int getCount();

    interface Provider{
        PluginSettings getPluginSetting();
    }
}
