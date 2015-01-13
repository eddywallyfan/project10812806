package nl.mprog.project10812806;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.winkelwagen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.contact) {
        	Intent intent = new Intent (this, ContactActivity.class);
        	startActivity(intent);
        }
		if (id == R.id.home) {
        	Intent intent = new Intent (this, StartschermActivity.class);
        	startActivity(intent);
        }
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent (this, PlantActivity.class);
    	startActivity(intent);
	}
}