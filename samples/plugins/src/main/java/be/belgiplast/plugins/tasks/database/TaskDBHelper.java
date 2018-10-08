package be.belgiplast.plugins.tasks.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import be.belgiplast.plugins.tasks.MutableTaskImpl;

public class TaskDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasks";

    private static final String TABLE_NAME = "tasks";
    private static final String COLUMN_ID ="id";
    private static final String COLUMN_NAME ="name";
    private static final String COLUMN_DESCRIPTION ="name";
    private static final String COLUMN_TIMESTAMP ="timestamp";


    private static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + "(" +
            COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP," +
            COLUMN_NAME + " TEXT,"+
            COLUMN_DESCRIPTION + " TEXT"+
            ")";


    public TaskDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public MutableTaskImpl getTask(long id){
        MutableTaskImpl result = new MutableTaskImpl();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_ID,COLUMN_TIMESTAMP,COLUMN_NAME,COLUMN_DESCRIPTION},
                COLUMN_ID +"=?",
                new String[]{String.valueOf(id)},null,null,"COLUMN_TIMESTAMP",null
                );
        if (cursor != null){
            cursor.moveToFirst();
            result.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            result.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            result.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
        }else return null;

        return result;
    }

    public MutableTaskImpl getTask(Date id){
        MutableTaskImpl result = new MutableTaskImpl();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_ID,COLUMN_TIMESTAMP,COLUMN_NAME,COLUMN_DESCRIPTION},
                COLUMN_TIMESTAMP +"=?",
                new String[]{String.valueOf(id.getTime())},null,null,"COLUMN_TIMESTAMP",null
        );
        if (cursor != null){
            cursor.moveToFirst();
            result.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            result.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            result.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
        }else return null;

        return result;
    }
}
