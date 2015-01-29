/* John Wesselingh
 * 10812806
 * Listadapter die gebruikt wordt voor de winkelwagen.
 * Hij neemt een Orderline als input en zet hem in dezelfde layout als het assortiment
 */
package nl.mprog.project10812806;
import java.util.ArrayList;
import com.squareup.picasso.Picasso;

import android.content.Context;
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
   
            // Associeer de holders met de juiste Text- en ImageView
            holder.image = (ImageView) v.findViewById(R.id.image);
            holder.plantname = (TextView) v.findViewById(R.id.plantName);
            holder.plantsize = (TextView) v.findViewById(R.id.plantSize);
            holder.plantnumber = (TextView) v.findViewById(R.id.plantStock);
            
            v.setTag(holder);
        }
           
        // Vraag de huidige positie van elk element op en zet ze in een String	
        String count = (order.get(position).number);
        String name = (order.get(position).name);
        String size = (order.get(position).size);
        String pic = (order.get(position).photo);
        
        // Als er geen foto beschikbaar is gebruik je de standaardfoto achter deze link
        if (pic.isEmpty() ){
        	pic = "https://www.treecommerce.nl/partijfotos/1580229.jpg";
        }
        
        ViewHolder hold =(ViewHolder) v.getTag();
        
        // Gebruik Picasso om de foto in hold.image te laden. Met de placeholder pop_wtm
        Picasso.with(getContext()).load(pic).placeholder(R.drawable.pop_wtm).fit().centerInside().into(hold.image);
   
        hold.plantname.setText(name);
        hold.plantsize.setText(size);
        hold.plantnumber.setText(count);
           
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
