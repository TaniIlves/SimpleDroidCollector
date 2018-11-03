package ivs.ilves.droidsmartcollector.Collections;

public class Collection {

    private int collectionIndex = 0;
    private String collectionID;
    private String collectionName;
    private String collectionDescription;

    //
    // GETTER and SETTER area.
    //
    public int getCollectionIndex() {
        return collectionIndex;
    }

    public void setCollectionIndex(int collectionIndex) {

        //if (collectionIndex != 0) {
        //this.collectionNumber = collectionIndex;
        //} else {
        this.collectionIndex += 1;
        //}
    }

    public String getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(String collectionName, int collectionNumber) {

        //this.collectionID = collectionName.replaceAll("\\s|\\d|[,.]","");
        this.collectionID = collectionName.replaceAll("[^A-Za-zА-Яа-я]", "");

        this.collectionID += "00" + String.valueOf(collectionNumber);
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionDescription() {
        return collectionDescription;
    }

    public void setCollectionDescription(String collectionDescription) {
        this.collectionDescription = collectionDescription;
    }
    // EOF: GETTER and SETTER area.


}
