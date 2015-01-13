/* John Wesselingh project
 * 10812806
 * Het beginscherm van de app, hier gebeuren weinig spannende dingen.
 */
package nl.mprog.project10812806;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class StartschermActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscherm);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.startscherm, menu);
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
        return super.onOptionsItemSelected(item);
    }
    public void startknop (View view) {
    	Intent intent = new Intent (this, AssortimentActivity.class);
    	startActivity(intent);
    	}
    public void contact (View view) {
    	Intent intent = new Intent (this, ContactActivity.class);
    	startActivity(intent);
    	}
}
