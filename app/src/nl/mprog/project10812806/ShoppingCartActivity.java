/*John Wesselingh
 * 10812806
 * Activity die een listview maakt van de geselecteerde Order (via de klasses Order en Orderline)
 * Vervolgens wordt de uiteindelijke list in een email bericht gestopt waarna de client (gmail) het overneemt
 */
package nl.mprog.project10812806;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ShoppingCartActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_winkelwagen);
	    
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
        		String order = "";
            	ArrayList<Orderline> orderList = Order.getInstance().getList();

            	for (int i = 0; i < orderList.size(); i++) {
            		Orderline orderLine = orderList.get(i);
            		order = order + orderLine.toString();
            	}
        		
        		Intent i = new Intent(Intent.ACTION_SEND);
        		i.setType("message/rfc822");
        		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"johnwes@live.nl"});
        		i.putExtra(Intent.EXTRA_SUBJECT, "bestelling via app");
        		i.putExtra(Intent.EXTRA_TEXT   , order);
        	
        		try {
        		    startActivity(Intent.createChooser(i, "Verstuur email via:"));
        		} catch (android.content.ActivityNotFoundException ex) {
        		    Toast.makeText(ShoppingCartActivity.this, "Er is geen programma gevonden om een email te versturen", Toast.LENGTH_SHORT).show();
        		}
        		setContentView(R.layout.activity_sluit);
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
