package be.belgiplast.plugins.tasks;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class DatabaseTaskList implements List<MutableTask> {
    private ArrayList<MutableTask> tasks = new ArrayList<>();

    

    public void trimToSize() {
        tasks.trimToSize();
    }

    public void ensureCapacity(int minCapacity) {
        tasks.ensureCapacity(minCapacity);
    }

    @Override
    public int size() {
        return tasks.size();
    }

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return tasks.contains(o);
    }

    @Override
    public int indexOf(Object o) {
        return tasks.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return tasks.lastIndexOf(o);
    }

    @Override
    public Object clone() {
        return tasks.clone();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return tasks.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] a) {
        return tasks.toArray(a);
    }

    @Override
    public MutableTask get(int index) {
        return tasks.get(index);
    }

    @Override
    public MutableTask set(int index, MutableTask element) {
        return tasks.set(index, element);
    }

    @Override
    public boolean add(MutableTask mutableTask) {
        return tasks.add(mutableTask);
    }

    @Override
    public void add(int index, MutableTask element) {
        tasks.add(index, element);
    }

    @Override
    public MutableTask remove(int index) {
        return tasks.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        return tasks.remove(o);
    }

    @Override
    public void clear() {
        tasks.clear();
    }

    @Override
    public boolean addAll(Collection<? extends MutableTask> c) {
        return tasks.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends MutableTask> c) {
        return tasks.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return tasks.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return tasks.retainAll(c);
    }

    @NonNull
    @Override
    public ListIterator<MutableTask> listIterator(int index) {
        return tasks.listIterator(index);
    }

    @NonNull
    @Override
    public ListIterator<MutableTask> listIterator() {
        return tasks.listIterator();
    }

    @NonNull
    @Override
    public Iterator<MutableTask> iterator() {
        return tasks.iterator();
    }

    @NonNull
    @Override
    public List<MutableTask> subList(int fromIndex, int toIndex) {
        return tasks.subList(fromIndex, toIndex);
    }

    @Override
    public void forEach(Consumer<? super MutableTask> action) {
        tasks.forEach(action);
    }

    @Override
    public Spliterator<MutableTask> spliterator() {
        return tasks.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super MutableTask> filter) {
        return tasks.removeIf(filter);
    }

    @Override
    public void replaceAll(UnaryOperator<MutableTask> operator) {
        tasks.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super MutableTask> c) {
        tasks.sort(c);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return tasks.containsAll(c);
    }

    @Override
    public String toString() {
        return tasks.toString();
    }

    @Override
    public Stream<MutableTask> stream() {
        return tasks.stream();
    }

    @Override
    public Stream<MutableTask> parallelStream() {
        return tasks.parallelStream();
    }
}
