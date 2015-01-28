/* John Wesselingh project
 * 10812806
 * Activity waar de klant bedankt wordt voor de bestelling.
 */
package nl.mprog.project10812806;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class GegevensActivity extends ActionBarActivity {

	   TextView naam ;
	   TextView telefoon;
	   TextView email;
	   TextView bedrijf;
	   TextView factuuradres;
	   
	   public static final String MyPREFERENCES = "MyPrefs" ;
	   public static final String TAG_naam = "naamKey"; 
	   public static final String TAG_telefoon = "telefoonKey"; 
	   public static final String TAG_email = "emailKey"; 
	   public static final String TAG_bedrijf = "bedrijfKey"; 
	   public static final String TAG_factuuradres = "factuuradresKey"; 

	   SharedPreferences sharedpreferences;

	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.eerste_gebruik);

	      factuuradres = (TextView) findViewById(R.id.klant_factuur);
	      naam = (TextView) findViewById(R.id.klant_naam);
	      telefoon = (TextView) findViewById(R.id.klant_telefoon);
	      bedrijf = (TextView) findViewById(R.id.editbedrijf);
	      email = (TextView) findViewById(R.id.editmail);

	      sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

	      if (sharedpreferences.contains(TAG_naam))
	      {
	         naam.setText(sharedpreferences.getString(TAG_naam, ""));

	      }
	      if (sharedpreferences.contains(TAG_telefoon))
	      {
	         telefoon.setText(sharedpreferences.getString(TAG_telefoon, ""));

	      }
	      if (sharedpreferences.contains(TAG_email))
	      {
	         email.setText(sharedpreferences.getString(TAG_email, ""));

	      }
	      if (sharedpreferences.contains(TAG_bedrijf))
	      {
	         bedrijf.setText(sharedpreferences.getString(TAG_bedrijf, ""));

	      }
	      if (sharedpreferences.contains(TAG_factuuradres))
	      {
	         factuuradres.setText(sharedpreferences.getString(TAG_factuuradres,""));

	      }
	   }

	   public void putInSP(View view){
	      String n  = naam.getText().toString();
	      String t  = telefoon.getText().toString();
	      String e  = email.getText().toString();
	      String b  = bedrijf.getText().toString();
	      String f  = factuuradres.getText().toString();
	      
	      Editor editor = sharedpreferences.edit();
	      editor.putString(TAG_naam, n);
	      editor.putString(TAG_telefoon, t);
	      editor.putString(TAG_email, e);
	      editor.putString(TAG_bedrijf, b);
	      editor.putString(TAG_factuuradres, f);

	      editor.commit(); 

	   }
	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu; this adds items to the action bar if it is present.
	      getMenuInflater().inflate(R.menu.sluit, menu);
	      return true;
	   }

}

