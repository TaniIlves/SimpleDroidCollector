package ivs.ilves.droidsmartcollector.Tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        //
        // DEFINE: Superclass constructor
        //
        super(context, "collectionDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyLOG:DEBUG", "--- onCreate database ---");

        //
        // CREATE: new table with that fields
        //
        db.execSQL("create table collection ("
                + "indexNo integer primary key autoincrement,"
                + "id text,"
                + "name text,"
                + "description text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
