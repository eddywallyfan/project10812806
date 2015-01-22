/* John Wesselingh project
 * 10812806
 * Plantactivity: Laat een geselecteerde plant zien en de klant een aantal selecteren. 
 * Hierna kan de klant de plant toevoegen aan een winkelwagen of terug naar de lijst.
 */
package nl.mprog.project10812806;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlantActivity extends Activity {
	Button button;

	// JSON node keys
	private static final String TAG_PN = "plantnaam";
	private static final String TAG_MAAT = "maatomschrijving";
	private static final String TAG_AANTAL = "aantal";
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_plant);

	    // Vraag de intent op van de vorige activity
	    Intent in = getIntent();

	    // Vraag de JSON waardes op
	    String naam = in.getStringExtra(TAG_PN);
	    String maat = in.getStringExtra(TAG_MAAT);
	    String aantal = in.getStringExtra(TAG_AANTAL);

	    // Zet de waardes in een textview
	    TextView lblNaam = (TextView) findViewById(R.id.PlantNaam);
	    TextView lblMaat = (TextView) findViewById(R.id.PlantMaat);
	    TextView lblAantal = (TextView) findViewById(R.id.PlantAantal);

	    lblNaam.setText(naam);
	    lblMaat.setText(maat);
	    lblAantal.setText(aantal);
	   
				/*Intent in = getIntent();

			    // Get JSON values from previous intent
			    String naam = in.getStringExtra(TAG_PN);
			    String maat = in.getStringExtra(TAG_MAAT);
			    String aantal = in.getStringExtra(TAG_AANTAL);
		        */

	} 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.winkelwagen, menu);
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
}
