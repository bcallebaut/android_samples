package be.belgiplast.pagedlist.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import be.belgiplast.pagedlist.model.Repo;

/**
 * Database schema that holds the list of repos.
 */
@Database(
        entities = {Repo.class},
        version = 1,
        exportSchema = false
)
public abstract class RepoDatabase extends RoomDatabase {
    public abstract RepoDao reposDao();

    private static volatile RepoDatabase INSTANCE;

    public static RepoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RepoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RepoDatabase.class, "Github")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
