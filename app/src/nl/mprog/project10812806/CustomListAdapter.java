/* John Wesselingh
 * 10812806
 * Listadapter die gebruikt wordt om het assortiment in een lijst te laten zien
 * Lijkt in veel opzichten op de CartAdapter
 */
package nl.mprog.project10812806;

import java.util.ArrayList;
import java.util.HashMap;
import nl.mprog.project10812806.CatalogActivity;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<HashMap<String,String>>{

    private LayoutInflater mLayoutInflater;
    ArrayList<HashMap<String, String>> plantList = new ArrayList<HashMap<String, String>>();
    
	public CustomListAdapter(Context context, int textViewResourceId, ArrayList<HashMap<String, String>> map) {
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
   
            // Associeer de holders met de juiste Text- en ImageView
            holder.image = (ImageView) v.findViewById(R.id.image);
            holder.plantname = (TextView) v.findViewById(R.id.plantName);
            holder.plantsize = (TextView) v.findViewById(R.id.plantSize);
            holder.plantnumber = (TextView) v.findViewById(R.id.plantStock);
            
            v.setTag(holder);
        }
           
        // Vraag de huidige positie van elk element op en zet ze in een String
        String pic = (plantList.get(position).get(CatalogActivity.TAG_PHOTO));
        String name = (plantList.get(position).get(CatalogActivity.TAG_PN));
        String stock = (plantList.get(position).get(CatalogActivity.TAG_STOCK));
        String size = (plantList.get(position).get(CatalogActivity.TAG_SIZE));
      
        // Als er geen foto beschikbaar is gebruik je de standaardfoto achter deze link
        if (pic.isEmpty() ){
        	pic = "https://www.treecommerce.nl/partijfotos/1580229.jpg";
        }

        // Gebruik Picasso om de foto in hold.image te laden. Met de placeholder pop_wtm
        ViewHolder hold =(ViewHolder) v.getTag();
        Picasso.with(getContext()).load(pic).placeholder(R.drawable.pop_wtm).fit().centerInside().into(hold.image);
        
        hold.plantname.setText(name);
        hold.plantsize.setText(size);
        hold.plantnumber.setText(stock);
 
        return v;
    }

	// ViewHolder die ervoor zorgt dat de lijst efficienter ingeladen wordt
    class ViewHolder {
        public ImageView image;
        public TextView plantname;
        public TextView plantsize;
        public TextView plantnumber;
    }
}
