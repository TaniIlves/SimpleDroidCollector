package ivs.ilves.droidsmartcollector;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ivs.ilves.droidsmartcollector.activities.InputCollectionActivity;
import ivs.ilves.droidsmartcollector.adapters.CollectionAdapter;
import ivs.ilves.droidsmartcollector.collections.Collection;
import ivs.ilves.droidsmartcollector.tools.DBHelper;

import static ivs.ilves.droidsmartcollector.tools.DebugTool.writeLog;


public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    String[] collectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //
        // CONNECT: To DB
        //
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        //
        // CREATE: Query for all data from table and get a Cursor
        //
        Cursor cursor = db.query("collection", null, null,
                null, null, null, null);


        //
        // SET: Cursor position at first row in query
        //      if query haven't any rows, return False
        //
        if (cursor.moveToFirst()) {

            //
            // DEFINE: Rows numbers by name in query
            //
            int RowNoColIndex = cursor.getColumnIndex("indexNo");
            int idColIndex = cursor.getColumnIndex("id");
            int nameColIndex = cursor.getColumnIndex("name");
            int descriptionColIndex = cursor.getColumnIndex("description");

            String allTableRows = "--- Rows in table: ---\n";

            ArrayList<String> collectionName = new ArrayList<>();

            ArrayList<Collection> collectionItems = new ArrayList<>();

            int i = 1;
            collectionName.add(0, "Select collection");

            do {
                //
                // GET: Values by column numbers and put all to variables
                //
                allTableRows += "\nRowNo = " + cursor.getString(RowNoColIndex) +
                        ", \nID = " + cursor.getString(idColIndex) +
                        ", \nName = " + cursor.getString(nameColIndex) +
                        ", \nDescription = " + cursor.getString(descriptionColIndex) +
                        "\n+++++++++++++";


                //
                // ADD: New values to List
                //
                collectionName.add(i, cursor.getString(nameColIndex));


                //
                // ADD: New objects to List
                //
                collectionItems.add(new Collection(i, cursor.getString(idColIndex), cursor.getString(nameColIndex), cursor.getString(descriptionColIndex)));

                writeLog('d', "Row " + i + " = " + collectionName.get(i));

                i++;


                //
                // GOTO: Next row. If it's not found - exit from cycle
                //
            } while (cursor.moveToNext());


            TextView collectionListField = findViewById(R.id.collectionListField);
            collectionListField.setText(allTableRows);
            collectionListField.setVisibility(View.VISIBLE);


            //
            // CREATE: Adapter for Spinner
            //
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, collectionName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            //
            // CREATE: New spinner with ArrayAdapter
            //
            Spinner spinner = findViewById(R.id.selectCollection);
            spinner.setAdapter(adapter);

            //
            // SET: Title
            //
            //spinner.setPrompt("Select collection");

            //
            // SELECT: 0 element
            //
            //spinner.setSelection(0);

            //
            // SET: Listener for item select
            //
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                /**
                 *
                 *
                 * @param parent
                 * @param view
                 * @param position
                 * @param id
                 */
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    //
                    // SHOW: Toast with selected position
                    //
                    if (position != 0) {
                        Toast.makeText(getBaseContext(), "Position = " +
                                position, Toast.LENGTH_SHORT).show();
                    }
                }

                /**
                 *
                 *
                 * @param arg0
                 */
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });


            //
            // CREATE: Adapter for ListView
            //
            ListView listView = findViewById(R.id.listView);
            CollectionAdapter listViewAdapter = new CollectionAdapter(this, collectionItems);
            listView.setAdapter(listViewAdapter);


        } else {
            writeLog('d', "0 rows");
        }

        cursor.close();
        dbHelper.close();


        //
        // DEBUG: Output all variables values
        //
    }

    /**
     * INITIATE: New Activity.
     *
     * @param view View
     */
    public void goToCreateNewCollection(View view) {

        Intent intent = new Intent(this, InputCollectionActivity.class);              // CREATE: New object Activity form Intent
        startActivity(intent);                                                                      // START: New Activity

    }

    //
    // START: Menu methods Area
    //

    /**
     * CREATE: Main menu
     *
     * @param menu Name of main Menu
     * @return Result of create (true)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }


    /**
     * CREATE: Clickable Item Settings of menu
     *
     * @param item
     */
    public void onSettings(MenuItem item) {

        Toast.makeText(this, "There are no settings yet", Toast.LENGTH_LONG).show();

    }

    // EOF: Menu methods Area
}

