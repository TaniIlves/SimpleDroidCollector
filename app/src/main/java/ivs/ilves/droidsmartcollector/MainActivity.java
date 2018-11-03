package ivs.ilves.droidsmartcollector;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import ivs.ilves.droidsmartcollector.Activities.InputCollectionActivity;
import ivs.ilves.droidsmartcollector.Tools.DBHelper;


public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new DBHelper(this);
        //
        // CONNECT: To DB
        //
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d("MyLOG.DEBUG", "--- Rows in my table: ---");
        //
        // CREATE: Query for all data from table and get cursor
        //
        Cursor cursor = db.query("collection", null, null,
                null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (cursor.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = cursor.getColumnIndex("id");
            int nameColIndex = cursor.getColumnIndex("name");
            int descriptionColIndex = cursor.getColumnIndex("description");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d("MyLOG.DEBUG",
                        "ID = " + cursor.getString(idColIndex) +
                                ", name = " + cursor.getString(nameColIndex) +
                                ", description = " + cursor.getString(descriptionColIndex));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (cursor.moveToNext());

            dbHelper.close();

        } else {
            Log.d("MyLOG.DEBUG", "0 rows");
        }
        cursor.close();
    }

    public void goToCreateNewCollection(View view) {

        Intent intent = new Intent(this, InputCollectionActivity.class);              // CREATE: New object Activity form Intent
        startActivity(intent);                                                                      // START: New Activity

    }
}

