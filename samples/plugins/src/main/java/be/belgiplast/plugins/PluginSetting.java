package be.belgiplast.plugins;

import android.content.Intent;

public interface PluginSetting {
    void moveUp();
    void moveDown();
    String getName();
    int getIcon();
    int getPosition();
    Intent getIntent();
}
