package be.belgiplast.plugins;

public interface PluginSetting {
    void moveUp();
    void moveDown();
    String getName();
    int getIcon();
    int getPosition();
}
