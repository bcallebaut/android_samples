package be.belgiplast.notes.model;
import android.support.annotation.NonNull;

import java.util.Date;

public class Note {

    public static final String FIELD_CREATION = "creation";
    public static final String FIELD_MODIFICATION = "modification";
    public static final String FIELD_TITLE = "titre";
    public static final String FIELD_DESCRIPTION = "description";

    private Date timestamp;
    private Date modification;
    private String name;
    private String description;

    public Note() {
        timestamp = new Date();
        modification = timestamp;
        name="";
        description = "";
    }

    public Note(Date timestamp, Date modification, String name, String description) {
        this.timestamp = timestamp;
        this.modification = modification;
        this.name = name;
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getModification() {
        return modification;
    }

    public void setModification(@NonNull Date modification) {
        this.modification = modification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
