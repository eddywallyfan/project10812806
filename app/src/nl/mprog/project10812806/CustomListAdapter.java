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

public class CustomListAdapter extends ArrayAdapter<HashMap<String,String>>{

	private static final String TAG = "hoppa";
    private LayoutInflater mLayoutInflater;
    ArrayList<HashMap<String, String>> plantList = new ArrayList<HashMap<String, String>>();
    /*public String[] maps;
    String sum = "";{
    for (HashMap<String, String> hash : plantList) {
        for (String current : hash.values()) {
            sum = sum + current + "<#>";
        }
    }
    maps = sum.split("<#>");}
    */
	public CustomListAdapter(Context context, int textViewResourceId, ArrayList<HashMap<String, String>> map) {
        super(context, textViewResourceId, map);
        mLayoutInflater = LayoutInflater.from(context);
        this.plantList = map;
        //Log.i("hoi", "hoppa "+map);
    }

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		//Log.i("hoi", "joppiesaus");
        View v = convertView;
        ViewHolder holder;
        plantList.get(position);//.get(TAG_FOTO);
       // Log.i(TAG, "position");
        if (v == null) {
            v = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
   
            holder.image = (ImageView) v.findViewById(R.id.Image);
            holder.plantnaam = (TextView) v.findViewById(R.id.PlantNaam);
            holder.plantmaat = (TextView) v.findViewById(R.id.PlantMaat);
            holder.plantaantal = (TextView) v.findViewById(R.id.PlantAantal);
            
            v.setTag(holder);
            /*Log.i("1e", "1e"+getContext());
            Log.i("2e", "2e"+holder.image);
            Log.i("2e", "2e"+holder.plantaantal);
            Log.i("2e", "2e"+holder.plantmaat);
           
            Log.i("4e", "4e"+(plantList.get(position).get(AssortimentActivity.TAG_FOTO)));
            Log.i("3e", "3e"+Picasso.with(getContext()).load(plantList.get(position).get(AssortimentActivity.TAG_FOTO)));
           */ 
            String pic = (plantList.get(position).get(AssortimentActivity.TAG_FOTO));
            String naam = (plantList.get(position).get(AssortimentActivity.TAG_PN));
            String voorraad = (plantList.get(position).get(AssortimentActivity.TAG_VOORRAAD));
            String maat = (plantList.get(position).get(AssortimentActivity.TAG_MAAT));
            Log.i("hoi", "naam"+naam);
           // String[] s = {naam, voorraad, maat};
           
            //if (pic.isEmpty() ){
            //	pic = "https://www.treecommerce.nl/partijfotos/1580229.jpg";
            //}
           // Log.i("7e", "7e"+pic);
            Picasso.with(getContext()).load(pic).placeholder(R.drawable.pop_wtm).into(holder.image);
            
            ViewHolder hold =(ViewHolder) v.getTag();
            //String[] maps = this.plantList;
            //String s = maps[position];
            hold.plantnaam.setText(naam);
            hold.plantmaat.setText(maat);
            hold.plantaantal.setText(voorraad);
           // Log.i("log", "log"+hold.plantnaam);

            v.setTag(hold);
        } else {
            holder = (ViewHolder) v.getTag();
        }

     //   final Orderline plant = getItem(position);
       
        return v;
    }
	/*@Override
	public long getItemId (int position){
		String item = getItem(position);
		return midMap.get(item);
	}*/

    class ViewHolder {
        public ImageView image;
        public TextView plantnaam;
        public TextView plantmaat;
        public TextView plantaantal;
    }
}
