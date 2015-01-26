package nl.mprog.project10812806;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ShoppingCartActivity extends ActionBarActivity {
	private static final String TAG = "hop";
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
		
		final Button button = (Button) findViewById(R.id.mailBtn);
        button.setOnClickListener(new View.OnClickListener() {
        	String order = Order.getInstance().getList().toString();
        	public void onClick(View v) {
        		Intent i = new Intent(Intent.ACTION_SEND);
        		i.setType("message/rfc822");
        		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"johnwes@live.nl"});
        		i.putExtra(Intent.EXTRA_SUBJECT, "bestelling via app");
        		i.putExtra(Intent.EXTRA_TEXT   , order);
        		Log.i("lgo", "dfmsg" + Order.getInstance().getList());
        		try {
        		    startActivity(Intent.createChooser(i, "Verstuur email via:"));
        		} catch (android.content.ActivityNotFoundException ex) {
        		    Toast.makeText(ShoppingCartActivity.this, "Er is geen programma gevonden om een email te versturen", Toast.LENGTH_SHORT).show();
        		}
        		
        	//	Intent intent = new Intent(ShoppingCartActivity.this, SluitActivity.class);
            	//startActivity(intent);
        	}
        });
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
		if (id == R.id.action_home) {
			Intent intent = new Intent (this, StartschermActivity.class);
	    	startActivity(intent);
		}
		if (id == R.id.action_contact) {
			Intent intent = new Intent (this, ContactActivity.class);
	    	startActivity(intent);
		}
		
		if (id == R.id.action_menulist) {
			Intent intent = new Intent (this, AssortimentActivity.class);
	    	startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}
}
