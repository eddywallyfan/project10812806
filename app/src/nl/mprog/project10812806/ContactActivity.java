/* John Wesselingh project
 * 10812806
 * Contact activity: Schermpje met contactgegevens
 */
package nl.mprog.project10812806;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ContactActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_home) {
			Intent intent = new Intent (this, MainActivity.class);
	    	startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
