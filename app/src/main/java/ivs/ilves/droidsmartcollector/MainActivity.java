package ivs.ilves.droidsmartcollector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import ivs.ilves.droidsmartcollector.Collections.Collection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    //
    // SEND: Result of create new Collection;
    //
    public void sendResult(View view) {
        TextView collectionName = findViewById(R.id.collectionName);
        TextView collectionDescription = findViewById(R.id.collectionDescription);



        Collection collection = new Collection();


        int collectionIndex = collection.getCollectionNumber();


        collectionDescription.getText();

        collection.setCollectionName(String.valueOf(collectionName.getText()));
        collection.setCollectionDescription(String.valueOf(collectionDescription.getText()));

        collection.setCollectionNumber(collectionIndex);
        collection.setCollectionID(collection.getCollectionName(), collection.getCollectionNumber());


        //
        // DEBUG: Output all variables values
        //
        Log.i("MyLOG.DEBUG", "ClassName: " + collection.getClass());
        Log.i("MyLOG.DEBUG", "CollectionID: " + collection.getCollectionID());
        Log.i("MyLOG.DEBUG", "CollectionName: " + collection.getCollectionName());
        Log.i("MyLOG.DEBUG", "CollectionDescription: " + collection.getCollectionDescription());

    }
}
