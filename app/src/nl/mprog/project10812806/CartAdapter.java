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

public class CartAdapter extends ArrayAdapter<Orderline>{
	
    private LayoutInflater mLayoutInflater;
    ArrayList<Orderline> order = Order.getInstance().getList();
 
	public CartAdapter(Context context, int textViewResourceId, ArrayList<Orderline> map) {
        super(context, textViewResourceId);
        mLayoutInflater = LayoutInflater.from(context);
        this.order = map;
    }
	
	@Override
	public int getCount() {
		return order.size();
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;
        order.get(position);
   
        if (v == null) {
            v = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
   
            holder.image = (ImageView) v.findViewById(R.id.Image);
            holder.plantnaam = (TextView) v.findViewById(R.id.PlantNaam);
            holder.plantmaat = (TextView) v.findViewById(R.id.PlantMaat);
            holder.plantaantal = (TextView) v.findViewById(R.id.PlantAantal);
            
            v.setTag(holder);
        }
           
            String aantal = (order.get(position).aantal);
            String naam = (order.get(position).plant_naam);
            String maat = (order.get(position).potmaat);
            String pic = (order.get(position).foto);
            
           
            if (pic.isEmpty() ){
            	pic = "https://www.treecommerce.nl/partijfotos/1580229.jpg";
            }

            ViewHolder hold =(ViewHolder) v.getTag();
            Picasso.with(getContext()).load(pic).placeholder(R.drawable.pop_wtm).fit().centerInside().into(hold.image);
   
            hold.plantnaam.setText(naam);
            hold.plantmaat.setText(maat);
            hold.plantaantal.setText(aantal);
            Log.i("hoi", "ordercart"+naam);
            Log.i("hoi", "ordercart"+order.get(position).plant_naam);
    		
        return v;
    }

    class ViewHolder {
        public ImageView image;
        public TextView plantnaam;
        public TextView plantmaat;
        public TextView plantaantal;
    }
}
