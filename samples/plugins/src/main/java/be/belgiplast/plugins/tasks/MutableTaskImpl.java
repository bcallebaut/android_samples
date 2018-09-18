package be.belgiplast.plugins.tasks;

public class MutableTaskImpl implements MutableTask {
    private int icon;
    private String name;
    private String description;
    private int progress;

    @Override
    public int getIcon() {
        return icon;
    }

    @Override
    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getProgress() {
        return progress;
    }

    @Override
    public void setProgress(int progress) {
        this.progress = progress;
    }
}
