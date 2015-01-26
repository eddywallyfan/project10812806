/* John Wesselingh project
 * 10812806
 * Het beginscherm van de app, hier gebeuren weinig spannende dingen.
 */
package nl.mprog.project10812806;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class StartschermActivity extends ActionBarActivity {

	Orderline orderline = new Orderline();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (orderline.bedrijf == null){
        	setContentView(R.layout.eerste_gebruik);
        } else{
        	setContentView(R.layout.activity_startscherm);
        }
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
        if (id == R.id.action_contact) {
        	Intent intent = new Intent (this, ContactActivity.class);
        	startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void btn_eerste (View view) {
    	// Zet de uitkomsten van de edittekst vakjes in strings
    	EditText edText1 = (EditText) findViewById(R.id.editbedrijf);
    	String bedrijf = edText1.getText().toString();
    	EditText edText2 = (EditText) findViewById(R.id.klant_naam);
    	String contact = edText2.getText().toString();
    	EditText edText3 = (EditText) findViewById(R.id.editmail);
    	String email = edText3.getText().toString();
    	EditText edText4 = (EditText) findViewById(R.id.klant_factuur);
    	String factuur = edText4.getText().toString();
    	EditText edText5 = (EditText) findViewById(R.id.klant_telefoon);
    	String phone = edText5.getText().toString();
    	
    	// Zet de strings in de ordeline
    	orderline.bedrijf = bedrijf;
    	orderline.contactPersoon = contact;
    	orderline.email = email;
    	orderline.factuurAdres = factuur;
    	orderline.phoneNr = phone;
    	//Log.i("orderline", "tot hier"+ orderline.email);
    	Intent intent = new Intent (this, AssortimentActivity.class);
    	startActivity(intent);
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
