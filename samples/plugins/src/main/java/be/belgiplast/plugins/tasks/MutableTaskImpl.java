package be.belgiplast.plugins.tasks;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "task")
public class MutableTaskImpl implements MutableTask {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id = 0;
    private int icon;
    @NonNull
    private String name = "no name";
    private String description = "no description";
    @NonNull
    private int progress = 0;

    public MutableTaskImpl() {
    }

    @Ignore
    public MutableTaskImpl(int icon, String name, String description, int progress) {
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
