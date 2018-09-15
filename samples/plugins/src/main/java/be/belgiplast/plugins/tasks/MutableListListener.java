package be.belgiplast.plugins.tasks;

public interface MutableListListener<T> {
    void itemAdded(T item, int position);
    void itemRemoved(T item, int position);
    void itemMoved(T item, int position, int target);
}
