package nl.mprog.project10812806;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
public class AssortimentActivity extends ListActivity {

	static final String[] PLANTEN = new String[] {"Stephanandra inc. Crispa", "Ander plantje"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assortiment);
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,PLANTEN));
		 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		//getListView().setOnItemClickListener(this);
 
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent (this, PlantActivity.class);
    	startActivity(intent);
	}
}
