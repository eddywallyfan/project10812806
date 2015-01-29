/* John Wesselingh project
 * 10812806
 * Het beginscherm van de app, hier gebeuren weinig spannende dingen.
 */
package nl.mprog.project10812806;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Orderline orderline = new Orderline();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        	setContentView(R.layout.activity_main);
        	// Check of er een werkende internetverbinding is
        	isOnline();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_contact) {
        	Intent intent = new Intent (this, ContactActivity.class);
        	startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
   
    // Fuctie die controleerd of er een werkende internetverbinding is en een Toast geeft als dit niet het geval is
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(getBaseContext(), "Sorry, u heeft geen werkende internetverbinding", Toast.LENGTH_LONG).show();
            return false;
        }
    return true; 
    }
    
    // Methodes voor de twee knoppen bij het gewone scherm
    public void startButton (View view) {
    	Intent intent = new Intent (this, CatalogActivity.class);
    	startActivity(intent);
    }
   
    public void contactButton (View view) {
    	Intent intent = new Intent (this, ContactActivity.class);
    	startActivity(intent);
    }
}
