/*John Wesselingh
 * 10812806
 * Activity die een listview maakt van de geselecteerde Order (via de klasses Order en Orderline)
 * Vervolgens wordt de uiteindelijke list in een email bericht gestopt waarna de client (gmail) het overneemt
 */
package nl.mprog.project10812806;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class ShoppingCartActivity extends ActionBarActivity {

	// Dit zijn de klantgegevens uit de SharedPreferences
	protected static final String MyPREFERENCES = DataActivity.MyPREFERENCES;
	protected static final String TAG_NAME = DataActivity.TAG_NAME;
	protected static final String TAG_PHONE = DataActivity.TAG_PHONE;
	protected static final String TAG_EMAIL = DataActivity.TAG_EMAIL;
	protected static final String TAG_COMPANY = DataActivity.TAG_COMPANY;
	protected static final String TAG_ADDRESS = DataActivity.TAG_ADDRESS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoppingcart);
	    
	    ArrayList<Orderline> orderLine = Order.getInstance().getList();
	   
	    // Maak een listview aan van de Orderline
		Context context = getBaseContext();
		final CartAdapter adapter = new CartAdapter(context, R.layout.list_item, orderLine);

	    ListView list1 = (ListView) findViewById(R.id.cart_list);
		list1.setAdapter(adapter);
		
		// Zorg ervoor dat een item uit de lijst verdwijnt als er lang op geklikt wordt
		list1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				ArrayList <Orderline> orderline = Order.getInstance().getList();
				orderline.remove(position);
				adapter.notifyDataSetChanged();
				return false;
			}       	
        }); 
		
		// Knop die gebruikt wordt om de email aan te maken
		final Button button = (Button) findViewById(R.id.mailBtn);
        button.setOnClickListener(new View.OnClickListener() {
        
        	public void onClick(View v) {
        		
        		// Vraag de sharedPreferences op en zet ze in een String
        		SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        		String prefs = "Contactpersoon: ";
        		prefs += (sharedpreferences.getString(TAG_NAME, ""));
        		prefs += "\nTelefoonnummer: ";
        		prefs += (sharedpreferences.getString(TAG_PHONE, ""));
           		prefs += "\nEmail: ";
           		prefs += (sharedpreferences.getString(TAG_EMAIL, ""));
           		prefs += "\nBedrijf: ";
           		prefs += (sharedpreferences.getString(TAG_COMPANY, ""));
           		prefs += "\nFactuuradres: ";
           		prefs += (sharedpreferences.getString(TAG_ADDRESS, ""));
            	
           		String order = "";
           		ArrayList<Orderline> orderList = Order.getInstance().getList();

            	for (int i = 0; i < orderList.size(); i++) {
            		Orderline orderLine = orderList.get(i);
            		order = order + orderLine.toString();
            	}
            	
            	// Voeg de preferences toe aan de order
            	order = order + prefs;
        		
            	// Hier wordt een scherm getoond waar de gebruiker een programma
            	// kan selecteren om een email te sturen. De mail is al ingevuld met alle nodige gegevens
        		Intent i = new Intent(Intent.ACTION_SEND);
        		i.setType("message/rfc822");
        		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"johnwes@live.nl"});
        		i.putExtra(Intent.EXTRA_SUBJECT, "Bestelling via app");
        		i.putExtra(Intent.EXTRA_TEXT   , order);
        	
        		try {
        		    startActivity(Intent.createChooser(i, "Verstuur email via:"));
        		} catch (android.content.ActivityNotFoundException ex) {
        		    Toast.makeText(ShoppingCartActivity.this, "Er is geen programma gevonden om een email te versturen", Toast.LENGTH_SHORT).show();
        		}
        		setContentView(R.layout.order_taken);
        	}
        });
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.shoppingcart, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_home) {
			Intent intent = new Intent (this, MainActivity.class);
	    	startActivity(intent);
		}
		if (id == R.id.action_contact) {
			Intent intent = new Intent (this, ContactActivity.class);
	    	startActivity(intent);
		}
		
		if (id == R.id.action_menulist) {
			Intent intent = new Intent (this, CatalogActivity.class);
	    	startActivity(intent);
		}		
		return super.onOptionsItemSelected(item);
	}
}
