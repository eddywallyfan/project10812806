/* John Wesselingh project
 * 10812806
 * Activity waar de klant bedankt wordt voor de bestelling.
 */
package nl.mprog.project10812806;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;

public class SluitActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sluit);
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
}
