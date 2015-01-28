/* John Wesselingh project
 * 10812806
 * Plantactivity: Laat een geselecteerde plant zien en de klant een aantal selecteren. 
 * Hierna kan de klant de plant toevoegen aan een winkelwagen of terug naar de lijst.
 */
package nl.mprog.project10812806;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PlantActivity extends ActionBarActivity {
	Button button;
	private static final String TAG = "hop";
	// JSON node keys
	private static final String TAG_PN = "plantnaam";
	private static final String TAG_MAAT = "maatomschrijving";
	private static final String TAG_VOORRAAD = "qty";
	private static final String TAG_FOTO = "foto";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_plant);
	   // Log.i("TAG", "TAG "+ TAG_FOTO);
	    //Log.i("TAG", "TAG "+ TAG_PN);
	    // Vraag de intent op van de vorige activity
	    Intent in = getIntent();

	    // Vraag de JSON waardes op
	    String naam = in.getStringExtra(TAG_PN);
	    String maat = in.getStringExtra(TAG_MAAT);
	    String voorraad = in.getStringExtra(TAG_VOORRAAD);
	    String plaatje = in.getStringExtra(TAG_FOTO);
	  //  Log.i("foto", "foto "+ voorraad);
	   // Log.i("foto", "foto "+ naam);
	    
	    ImageView view = (ImageView) findViewById(R.id.plaatje);
	    //Context con = getBaseContext();
	    Picasso.with(getBaseContext()).load(plaatje).placeholder(R.drawable.pop_wtm).fit().centerInside().into(view);
	    
	    // Zet de waardes in een textview
	    TextView lblNaam = (TextView) findViewById(R.id.PlantNaam);
	    TextView lblMaat = (TextView) findViewById(R.id.PlantMaat);
	    TextView lblVoorraad = (TextView) findViewById(R.id.PlantAantal);

	    lblNaam.setText(naam);
	    lblMaat.setText(maat);
	    lblVoorraad.setText(voorraad);
	   
	} 
	
	public void cartbtn (View view) {
		Intent in = getIntent();

	    // Vraag de JSON waardes op
		String naam = in.getStringExtra(TAG_PN);
	    String maat = in.getStringExtra(TAG_MAAT);
	    EditText edText = (EditText) findViewById(R.id.aantaltxt);
    	String aantal = edText.getText().toString();
    	String foto = in.getStringExtra(TAG_FOTO);
	
	    
	    // Maak een nieuwe orderline aan waar de geselecteerde waardes inkomen
		Orderline orderline = new Orderline();
		orderline.plant_naam = naam;
		orderline.potmaat = maat;
		orderline.aantal = aantal;
		orderline.foto = foto;
		//Log.i("hup", orderline.aantal);
		
		// Voeg de orderline toe aan de totale order
		Order order = Order.getInstance();
		order.add(orderline);
		
	   // Log.i(TAG, "soep"+Order.getInstance().getList());
		
	    // Start de volgende activity
	    Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);
	    intent.putExtra(TAG_FOTO, foto);
        startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plant, menu);
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
		if (id == R.id.cart) {
        	Intent intent = new Intent (this, ShoppingCartActivity.class);
        	startActivity(intent);
        }
		if (id == R.id.menulist) {
        	Intent intent = new Intent (this, AssortimentActivity.class);
        	startActivity(intent);
        }
		return super.onOptionsItemSelected(item);
	}
}
