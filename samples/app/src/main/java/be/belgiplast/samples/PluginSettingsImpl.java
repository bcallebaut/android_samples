package be.belgiplast.samples;

import java.util.ArrayList;
import java.util.LinkedList;

import be.belgiplast.plugins.Plugin;
import be.belgiplast.plugins.PluginSetting;
import be.belgiplast.plugins.PluginSettings;

public class PluginSettingsImpl implements PluginSettings {
    private LinkedList<PluginSetting> plugins;

    public PluginSettingsImpl() {
        plugins = new LinkedList<>();
    }

    @Override
    public PluginSetting get(int i) {
        return plugins.get(i);
    }

    @Override
    public void add(PluginSetting setting) {
        plugins.add(setting);
    }

    @Override
    public void remove(PluginSetting setting) {
        plugins.remove(setting);
    }

    @Override
    public void moveUp(PluginSetting setting) {
        int index = plugins.indexOf(setting);
        if (index > 0 ){
            plugins.remove(index);
            plugins.add(index - 1, setting);
        }

    }

    @Override
    public void moveDown(PluginSetting setting) {
        int index = plugins.indexOf(setting);
        if (index >= 0  && index < plugins.size() - 2){
            plugins.remove(index);
            plugins.add(index + 1, setting);
        }
    }

    @Override
    public int getCount() {
        return plugins.size();
    }
}
