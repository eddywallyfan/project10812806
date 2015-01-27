package nl.mprog.project10812806;

import java.util.ArrayList;
import java.util.HashMap;
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

public class CartAdapter extends ArrayAdapter<HashMap<String,String>>{

    private LayoutInflater mLayoutInflater;
    ArrayList<HashMap<String, String>> plantList = new ArrayList<HashMap<String, String>>();
 
	public CartAdapter(Context context, int textViewResourceId, ArrayList<HashMap<String, String>> map) {
        super(context, textViewResourceId, map);
        mLayoutInflater = LayoutInflater.from(context);
        this.plantList = map;
    }

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;
        plantList.get(position);
   
        if (v == null) {
            v = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
   
            holder.image = (ImageView) v.findViewById(R.id.Image);
            holder.plantnaam = (TextView) v.findViewById(R.id.PlantNaam);
            holder.plantmaat = (TextView) v.findViewById(R.id.PlantMaat);
            holder.plantaantal = (TextView) v.findViewById(R.id.PlantAantal);
            
            v.setTag(holder);
        }
           
            String pic = (plantList.get(position).get(AssortimentActivity.TAG_FOTO));
            String naam = (plantList.get(position).get(AssortimentActivity.TAG_PN));
            String voorraad = (plantList.get(position).get(AssortimentActivity.TAG_VOORRAAD));
            String maat = (plantList.get(position).get(AssortimentActivity.TAG_MAAT));
            Log.i("hoi", "naam"+naam);
           
            if (pic.isEmpty() ){
            	pic = "https://www.treecommerce.nl/partijfotos/1580229.jpg";
            }

            ViewHolder hold =(ViewHolder) v.getTag();
            Picasso.with(getContext()).load(pic).placeholder(R.drawable.pop_wtm).fit().centerInside().into(hold.image);
   
            hold.plantnaam.setText(naam);
            hold.plantmaat.setText(maat);
            hold.plantaantal.setText(voorraad);
              
        return v;
    }

    class ViewHolder {
        public ImageView image;
        public TextView plantnaam;
        public TextView plantmaat;
        public TextView plantaantal;
    }
}
