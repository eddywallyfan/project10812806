/*package nl.mprog.project10812806;

import java.util.List;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String>{
	
    private LayoutInflater mLayoutInflater;

	public CustomListAdapter(Context context, List<Plant> objects) {
        super(context, 0, objects);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder = null;
        if (v == null) {
            v = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();

            holder.image = (ImageView) v.findViewById(R.id.image);
            holder.plantnaam = (TextView) v.findViewById(R.id.PlantNaam);
            holder.plantmaat = (TextView) v.findViewById(R.id.PlantMaat);
            holder.plantaantal = (TextView) v.findViewById(R.id.PlantAantal);
            

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        final Product plant = getItem(position);

        Picasso.with(getContext()).load(url.trim()).placeholder(R.drawable.no_thumb)
        .into(poster);

        holder.plantnaam.setText(plant.plantnaam);
        holder.plantmaat.setText(plant.breeds.get(0));

        return v;
    }

    class ViewHolder {
        ImageView image;
        TextView plantnaam;
        TextView plantmaat;
        TextView plantaantal;
    }
}*/
