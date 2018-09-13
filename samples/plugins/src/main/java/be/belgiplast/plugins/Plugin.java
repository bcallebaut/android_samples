package be.belgiplast.plugins;

import android.content.Intent;

public interface Plugin {
    String getName();

    int getImageResource();

    String getDescription();

    Intent getIntent();
}
