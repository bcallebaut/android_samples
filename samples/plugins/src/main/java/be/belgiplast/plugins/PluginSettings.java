package be.belgiplast.plugins;

public interface PluginSettings {
    PluginSetting get(int i);
    void add(Plugin plugin);
    void remove(Plugin plugin);
    void moveUp(Plugin plugin);
    void moveDown(Plugin plugin);
    PluginSetting getSettings(Plugin plugin);
    PluginSetting getSettings(int index);
    int getCount();
    public void setListener(ContentListener content);

    interface Provider{
        PluginSettings getPluginSetting();
    }

    interface ContentListener{
        void notifyDatasetChanged();
        void notifyMoveUp(int position);
        void nottifyMoveDown(int position);
    }
}
