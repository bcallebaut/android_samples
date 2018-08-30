package be.belgiplast.samples;

public class PluginImpl implements Plugin {
    private String name;
    private int imageRes;
    private String description;

    public PluginImpl(String name, int imageRes, String description) {
        this.name = name;
        this.imageRes = imageRes;
        this.description = description;
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
}
