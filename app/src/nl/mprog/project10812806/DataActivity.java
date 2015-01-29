/* John Wesselingh project
 * 10812806
 * Activity die klantgegevens opvraagt en opslaat in SharedPreferences
 */
package nl.mprog.project10812806;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class DataActivity extends ActionBarActivity {

	TextView name ;
    TextView phone;
    TextView email;
    TextView company;
    TextView address;
   
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String TAG_NAME = "nameKey"; 
    public static final String TAG_PHONE = "phoneKey"; 
    public static final String TAG_EMAIL = "emailKey"; 
    public static final String TAG_COMPANY = "companyKey"; 
    public static final String TAG_ADDRESS = "addressKey";
	
    private static final String TAG_PHOTO = CatalogActivity.TAG_PHOTO; 

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_data);
	    
	    // Associeer de aangemaakte TextViews met de juiste items in first_visit xml
        address = (TextView) findViewById(R.id.edit_address);
        name = (TextView) findViewById(R.id.edit_name);
        phone = (TextView) findViewById(R.id.edit_phone);
        company = (TextView) findViewById(R.id.edit_company);
        email = (TextView) findViewById(R.id.edit_mail);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(TAG_NAME))
        {
            name.setText(sharedpreferences.getString(TAG_NAME, ""));
        }
      
        if (sharedpreferences.contains(TAG_PHONE))
        {
      	    phone.setText(sharedpreferences.getString(TAG_PHONE, ""));
        }
      
        if (sharedpreferences.contains(TAG_EMAIL))
        {
        	email.setText(sharedpreferences.getString(TAG_EMAIL, ""));
        }
      
        if (sharedpreferences.contains(TAG_COMPANY))
        {
        	company.setText(sharedpreferences.getString(TAG_COMPANY, ""));
        }
      
        if (sharedpreferences.contains(TAG_ADDRESS))
        {
        	address.setText(sharedpreferences.getString(TAG_ADDRESS,""));
        }     
    }

    // Zet de verzamelde gegevens in SharedPreferences
    public void putInSP(View view){
    	Intent in = getIntent();
	    String photo = in.getStringExtra(TAG_PHOTO);
    	
	    String n  = name.getText().toString();
        String t  = phone.getText().toString();
        String e  = email.getText().toString();
        String c  = company.getText().toString();
        String a  = address.getText().toString();
      
        Editor editor = sharedpreferences.edit();
        editor.putString(TAG_NAME, n);
        editor.putString(TAG_PHONE, t);
        editor.putString(TAG_EMAIL, e);
        editor.putString(TAG_COMPANY, c);
        editor.putString(TAG_ADDRESS, a);
        
        editor.commit(); 
       
        // Ga naar de volgende activity
        Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);
		intent.putExtra(TAG_PHOTO, photo);
        startActivity(intent);
    }
   
   	@Override
   	public boolean onCreateOptionsMenu(Menu menu) {
	   	getMenuInflater().inflate(R.menu.menu_order_taken, menu);
	   	return true;
   	}
}