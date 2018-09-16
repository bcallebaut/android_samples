package be.belgiplast.plugins.tasks;

public interface MutableTask extends Task {
    void setIcon(int icon);
    void setName(String name);
    void setDescription(String description);
    void setProgress(int progress);
}
