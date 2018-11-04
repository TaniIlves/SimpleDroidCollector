package ivs.ilves.droidsmartcollector.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ivs.ilves.droidsmartcollector.R;
import ivs.ilves.droidsmartcollector.collections.Collection;

public class CollectionAdapter extends BaseAdapter {

    private List<Collection> collectionList;
    private LayoutInflater layoutInflater;

    /**
     * Main constructor of Adapter
     *
     * @param context        Current context
     * @param collectionList List of objects in Collection class
     */
    public CollectionAdapter(Context context, List<Collection> collectionList) {

        this.collectionList = collectionList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /**
     * GET: Count of elements in List
     *
     * @return Count of elements
     */
    @Override
    public int getCount() {
        return collectionList.size();
    }

    /**
     * GET: Item by position
     *
     * @param position Position of Item
     * @return Item by position
     */
    @Override
    public Object getItem(int position) {
        return collectionList.get(position);
    }

    /**
     * GET: Position of Item
     *
     * @param position Position of Item
     * @return Position of Item
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        }

        Collection collection = getCollection(position);

        TextView textView = view.findViewById(R.id.itemView);
        textView.setText(collection.getCollectionName());

        return view;
    }

    /**
     * @param position
     * @return
     */
    private Collection getCollection(int position) {

        return (Collection) getItem(position);
    }
}
