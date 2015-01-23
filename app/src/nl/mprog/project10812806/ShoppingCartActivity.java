package nl.mprog.project10812806;

import java.util.ArrayList;
import java.util.HashMap;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ShoppingCartActivity extends Activity {

	private static final String TAG_PN = "plantnaam";
	private static final String TAG_MAAT = "maatomschrijving";
	private static final String TAG_AANTAL = "aantal";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_winkelwagen);
		
		Intent in = getIntent();
		String naam = in.getStringExtra(TAG_PN);
	    String maat = in.getStringExtra(TAG_MAAT);
	    String aantal = in.getStringExtra(TAG_AANTAL);

	    ArrayList<HashMap<String, String>> plantList = new ArrayList<HashMap<String, String>>();
	    HashMap<String, String> map = new HashMap<String, String>();
		
	    map.put(TAG_PN, naam);
		map.put(TAG_MAAT, maat);
		map.put(TAG_AANTAL, aantal);
		
		plantList.add(map);
	   
		
	    ListAdapter adapter = new SimpleAdapter(this, plantList,
            	R.layout.list_item,
            new String[] { naam, maat, aantal}, new int[] {
                    R.id.PlantNaam, R.id.PlantMaat, R.id.PlantAantal 
                    });

	    ListView list1 = (ListView) findViewById(R.id.cart_list);
		list1.setAdapter(adapter);
		
	}	
	
	public void bestelBtn() {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"johnwes@live.nl"});
		i.putExtra(Intent.EXTRA_SUBJECT, "bestelling");
		i.putExtra(Intent.EXTRA_TEXT   , Order.getInstance().getList());
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(ShoppingCartActivity.this, "Er is geen programma gevonden om een email te versturen", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shoppingcart, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
