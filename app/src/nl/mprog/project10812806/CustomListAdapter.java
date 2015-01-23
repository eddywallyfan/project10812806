package nl.mprog.project10812806;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.mprog.project10812806.AssortimentActivity;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String>{
	private static final String TAG = "hoppa";
    private LayoutInflater mLayoutInflater;
    ArrayList<HashMap<String, String>> plantList = new ArrayList<HashMap<String, String>>();

	public CustomListAdapter(Context context, ArrayList<HashMap<String, String>> map) {
        super(context, 0);
        mLayoutInflater = LayoutInflater.from(context);
        this.plantList = map;
    }

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder = null;
        plantList.get(position);//.get(TAG_FOTO);
        //Log.i(TAG, "position");
        if (v == null) {
            v = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();

            holder.image = (ImageView) v.findViewById(R.id.image);
            holder.plantnaam = (TextView) v.findViewById(R.id.PlantNaam);
            holder.plantmaat = (TextView) v.findViewById(R.id.PlantMaat);
            holder.plantaantal = (TextView) v.findViewById(R.id.PlantAantal);
            
            Picasso.with(getContext()).load(plantList.get(position).get("TAG_FOTO")).into(holder.image);

          //  holder.plantnaam.setText(holder.plantnaam);
           // holder.plantmaat.setText(holder.plantmaat);
            //holder.plantaantal.setText(holder.plantaantal);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

     //   final Orderline plant = getItem(position);
        
        
		
        return v;
    }

    class ViewHolder {
        ImageView image;
        TextView plantnaam;
        TextView plantmaat;
        TextView plantaantal;
    }
}
