package be.belgiplast.plugins;
import android.databinding.*;


public interface ObservableProvider<I> {
    ObservableArrayList<I> getObservable();
}
