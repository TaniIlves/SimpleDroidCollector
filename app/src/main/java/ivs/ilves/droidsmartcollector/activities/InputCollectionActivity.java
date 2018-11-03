package ivs.ilves.droidsmartcollector.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import ivs.ilves.droidsmartcollector.collections.Collection;
import ivs.ilves.droidsmartcollector.MainActivity;
import ivs.ilves.droidsmartcollector.R;
import ivs.ilves.droidsmartcollector.tools.DBHelper;


public class InputCollectionActivity extends AppCompatActivity {

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_collection_data);

        dbHelper = new DBHelper(this);
    }

    /**
     * SEND: Result of create new Collection;
     *
     * @param view Get View
     */
    public void sendResult(View view) {

        TextView collectionName = findViewById(R.id.collectionName);                                // DEFINE: View for CollectionName field
        TextView collectionDescription = findViewById(R.id.collectionDescription);                  // DEFINE: View for CollectionDescription field
        //TextView debugTextOut = findViewById(R.id.debugTextOut);

        Collection collection = new Collection();                                                   // CREATE: New object 'collection' from 'Collection' class


        int collectionIndex = collection.getCollectionIndex();

        collection.setCollectionIndex(collectionIndex);                                             // SET: CollectionIndex
        collection.setCollectionName(String.valueOf(collectionName.getText()));                     // SET: CollectionName
        collection.setCollectionDescription(String.valueOf(collectionDescription.getText()));       // SET: CollectionDescription

        collection.setCollectionID(collection.getCollectionName(), collection.getCollectionIndex());// SET: CollectionID

        //
        // CREATE: object for data
        //
        ContentValues cv = new ContentValues();

        //
        // SET: Data for writing to DB
        //
        cv.put("id", collection.getCollectionID());
        cv.put("name", collectionName.getText().toString());
        cv.put("description", collectionDescription.getText().toString());

        //
        // BEGIN: Database works area
        //
        // CONNECT: To DB
        //
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //
        // WRITE: Data to BD and get rowID
        //
        try {
            long rowID = db.insert("collection", null, cv);
            Log.d("MyLOG.DEBUG", "row inserted, ID = " + rowID);
        } catch (SQLException e) {
            Log.i("MyLOG.WARNING", String.valueOf(e));
        }

        //
        // CLOSE: DB connection
        //
        dbHelper.close();
        //EOF: DATABASE area


        Intent intent = new Intent(this, MainActivity.class);                         // CREATE: New object activity form Intent
        startActivity(intent);                                                                      // START: New Activity



        //
        // DEBUG: Output all variables values
        //
        Log.i("MyLOG.DEBUG", "File path: " + getFileStreamPath(collection.getCollectionName()));

        Log.i("MyLOG.DEBUG", "ClassName: " + collection.getClass());
        Log.i("MyLOG.DEBUG", "CollectionID: " + collection.getCollectionID());
        Log.i("MyLOG.DEBUG", "CollectionName: " + collection.getCollectionName());
        Log.i("MyLOG.DEBUG", "CollectionDescription: " + collection.getCollectionDescription());
    }
}





//FileTool.writeFile(this, "collections.xml", collection.getCollectionName());
//FileTool.readFile(this, "collections.xml");


//      debugTextOut.setText("New Collection: \n* ID: " + collection.getCollectionID() +
//      "\n* Name: " + collection.getCollectionName() +
//      "\n* Description: " + collection.getCollectionDescription() +
//      "\n* was created");
