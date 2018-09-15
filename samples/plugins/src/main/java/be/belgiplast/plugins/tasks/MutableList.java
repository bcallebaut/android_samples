package be.belgiplast.plugins.tasks;

public interface MutableList<T> {
    void setListener(MutableListListener<T> listener);
}
