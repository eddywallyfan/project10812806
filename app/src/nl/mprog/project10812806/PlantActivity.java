/* John Wesselingh project
 * 10812806
 * Plantactivity: Laat een geselecteerde plant zien en de klant een aantal selecteren. 
 * Hierna kan de klant de plant toevoegen aan een winkelwagen of terug naar de lijst.
 */
package nl.mprog.project10812806;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PlantActivity extends ActionBarActivity {
	Button button;
	
	// Sharedpreferences
	protected static final String MyPREFERENCES = DataActivity.MyPREFERENCES;
	protected static final String TAG_NAME = DataActivity.TAG_NAME;
	protected static final String TAG_PHONE = DataActivity.TAG_PHONE;
	protected static final String TAG_EMAIL = DataActivity.TAG_EMAIL;
	protected static final String TAG_COMPANY = DataActivity.TAG_COMPANY;
	protected static final String TAG_ADDRESS = DataActivity.TAG_ADDRESS;
	
	// De JSON nodes
	private static final String TAG_PN = "plantnaam";
	private static final String TAG_SIZE = "maatomschrijving";
	private static final String TAG_STOCK = "qty";
	private static final String TAG_PHOTO = "foto";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_plant);
	    // Vraag de intent op van de vorige activity
	    Intent in = getIntent();

	    // Vraag de JSON waardes op
	    String name = in.getStringExtra(TAG_PN);
	    String size = in.getStringExtra(TAG_SIZE);
	    String stock = in.getStringExtra(TAG_STOCK);
	    String photo = in.getStringExtra(TAG_PHOTO);
	    
	    // Maak een target voor Picasso
	    ImageView view = (ImageView) findViewById(R.id.picture);
	    
	    // Zet met Picasso de URL naar de foto om in een foto
	    Picasso.with(getBaseContext()).load(photo).placeholder(R.drawable.pop_wtm).fit().centerInside().into(view);
	    
	    // Zet de waardes in een textview
	    TextView lblName = (TextView) findViewById(R.id.plantName);
	    TextView lblSize = (TextView) findViewById(R.id.plantSize);
	    TextView lblStock = (TextView) findViewById(R.id.plantStock);

	    lblName.setText(name);
	    lblSize.setText(size);
	    lblStock.setText(stock); 
	} 
	
	// De button die ervoor zorgt dat de bestelling opgeslagen wordt
	// en de volgende activity start
	public void cartbtn (View view) {
		Intent in = getIntent();

	    // Vraag de JSON waardes op
		String name = in.getStringExtra(TAG_PN);
	    String size = in.getStringExtra(TAG_SIZE);
	    EditText edText = (EditText) findViewById(R.id.numbertxt);
    	String number = edText.getText().toString();
    	String photo = in.getStringExtra(TAG_PHOTO);
	   
	    // Maak een nieuwe orderline aan waar de geselecteerde waardes inkomen
		Orderline orderline = new Orderline();
		orderline.name = name;
		orderline.size = size;
		orderline.number = number;
		orderline.photo = photo;
		
		// Voeg de orderline toe aan de totale order
		Order order = Order.getInstance();
		order.add(orderline);
		
	    // Start de volgende activity
		SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
		String prefs = (sharedpreferences.getString(TAG_NAME, ""));
   		prefs += (sharedpreferences.getString(TAG_PHONE, ""));
   		prefs += (sharedpreferences.getString(TAG_EMAIL, ""));
   		prefs += (sharedpreferences.getString(TAG_COMPANY, ""));
   		prefs += (sharedpreferences.getString(TAG_ADDRESS, ""));
		
   		if (prefs == ""){
			Intent intent = new Intent(getApplicationContext(), DataActivity.class);
		    intent.putExtra(TAG_PHOTO, photo);
	        startActivity(intent);
		} else{
			Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);
		    intent.putExtra(TAG_PHOTO, photo);
	        startActivity(intent);
        }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.plant, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.contact) {
        	Intent intent = new Intent (this, ContactActivity.class);
        	startActivity(intent);
        }
		if (id == R.id.home) {
        	Intent intent = new Intent (this, MainActivity.class);
        	startActivity(intent);
        }
		if (id == R.id.cart) {
        	Intent intent = new Intent (this, ShoppingCartActivity.class);
        	startActivity(intent);
        }
		if (id == R.id.menulist) {
        	Intent intent = new Intent (this, CatalogActivity.class);
        	startActivity(intent);
        }
		return super.onOptionsItemSelected(item);
	}
}
