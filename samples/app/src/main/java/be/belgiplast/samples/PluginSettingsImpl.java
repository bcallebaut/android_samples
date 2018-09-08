package be.belgiplast.samples;

import java.util.ArrayList;
import java.util.LinkedList;

import be.belgiplast.plugins.Plugin;
import be.belgiplast.plugins.PluginSetting;
import be.belgiplast.plugins.PluginSettings;

public class PluginSettingsImpl implements PluginSettings {
    private LinkedList<PluginSettingImpl> plugins;
    private ContentListener listener;

    public PluginSettingsImpl() {
        plugins = new LinkedList<>();
    }

    @Override
    public PluginSetting get(int i) {
        return plugins.get(i);
    }

    @Override
    public void add(Plugin plugin) {
        plugins.add(new PluginSettingImpl(plugin));
        if (listener != null)
            listener.notifyDatasetChanged();
    }

    @Override
    public PluginSetting getSettings(Plugin plugin) {
        for (PluginSettingImpl ps : plugins){
            if (ps.doesHandlePlugin(plugin))
                return ps;
        }
        return null;
    }

    @Override
    public PluginSetting getSettings(int index) {
        return plugins.get(index);
    }

    @Override
    public void remove(Plugin setting) {
        plugins.remove(getSettings(setting));
        if (listener != null)
            listener.notifyDatasetChanged();
    }

    @Override
    public void moveUp(Plugin plugin) {
        moveUp((PluginSettingImpl)getSettings(plugin));
    }

    @Override
    public void moveDown(Plugin plugin) {
        moveDown((PluginSettingImpl)getSettings(plugin));
    }

    private void moveUp(PluginSettingImpl setting) {
        int index = plugins.indexOf(setting);
        if (index > 0 ){
            plugins.remove(index);
            plugins.add(index - 1, setting);
            if (listener != null)
                listener.notifyDatasetChanged();
        }
    }

    private void moveDown(PluginSettingImpl setting) {
        int index = plugins.indexOf(setting);
        if (index >= 0  && index < plugins.size() - 2){
            plugins.remove(index);
            plugins.add(index + 1, setting);
            if (listener != null)
                listener.notifyDatasetChanged();
        }
    }

    @Override
    public int getCount() {
        return plugins.size();
    }

    @Override
    public void setListener(ContentListener content) {
        this.listener = content;
    }

    private class PluginSettingImpl implements PluginSetting{
        Plugin plugin;

        public PluginSettingImpl(Plugin plugin) {
            this.plugin = plugin;
        }

        public boolean doesHandlePlugin(Plugin plugin){
            return this.plugin.equals(plugin);
        }

        @Override
        public void moveUp() {
            PluginSettingsImpl.this.moveUp(this);
        }

        @Override
        public void moveDown() {
            PluginSettingsImpl.this.moveDown(this);
        }

        @Override
        public String getName() {
            return plugin.getName();
        }

        @Override
        public int getIcon() {
            return plugin.getImageResource();
        }
    }
}
