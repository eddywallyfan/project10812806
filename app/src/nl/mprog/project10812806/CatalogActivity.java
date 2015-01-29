/* John Wesselingh project
 * 10812806
 * Assortiment activity, hier komt een lijst met planten.
 */
package nl.mprog.project10812806;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class CatalogActivity extends ActionBarActivity {
	
	// Sla JSON nodes op in string
	public static final String TAG_PN = "plantnaam";
	public static final String TAG_SIZE = "maatomschrijving";
	public static final String TAG_STOCK = "qty";
	public static final String TAG_PHOTO = "foto";
	
	public ArrayList<String> urlList = new ArrayList<String>();
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
       
        // Start een FetchItemsTask die een string maakt van de URL 
        FetchItemsTask task = new FetchItemsTask();
        task.execute();
        String answer = "";
        
        // Maak een arraylist waar de listview mee gevuld kan worden 
        ArrayList<HashMap<String, String>> plantList = new ArrayList<HashMap<String, String>>();
       
        try {
			answer = task.get();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
        
        // Maak een JSONArray van de string
        try {
			JSONArray jsonArray = new JSONArray(answer);
			
			for(int i = 0; i<jsonArray.length();i++){
				JSONObject plant = jsonArray.getJSONObject(i);

				String name = plant.getString(TAG_PN);
				String size = plant.getString(TAG_SIZE);
				String stock = plant.getString(TAG_STOCK);
				String photo = plant.getString(TAG_PHOTO);
	
				// Maak hashmap
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(TAG_PN, name);
				map.put(TAG_SIZE, size);
				map.put(TAG_STOCK, stock);
				map.put(TAG_PHOTO, photo);
			
				plantList.add(map);
				// Bewaar de link van de foto in een lijst, zodat deze meegestuurd 
				// kan worden naar de volgende activity.
				urlList.add(photo);
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
        
        // Zet de ArrayList plantList in een lijst
        Context context = getBaseContext();
		CustomListAdapter adapter = new CustomListAdapter(context, R.layout.list_item, plantList);
		ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(adapter);
    
        // Stel onClickListener in
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    String naam = ((TextView) view.findViewById(R.id.plantName)).getText().toString();
	            String maat = ((TextView) view.findViewById(R.id.plantSize)).getText().toString();
	            String voorraad = ((TextView) view.findViewById(R.id.plantStock)).getText().toString();

	            // Start activity en geef de correcte Strings mee
	            Intent intent = new Intent(getApplicationContext(), PlantActivity.class);
	            intent.putExtra(TAG_PN, naam);
	            intent.putExtra(TAG_SIZE, maat);
	            intent.putExtra(TAG_STOCK, voorraad);
	            intent.putExtra(TAG_PHOTO, urlList.get(position));
	            startActivity(intent);
			}       	
        });  
    }
    
	
	// Pak de URL en maak er een string van
    private class FetchItemsTask extends AsyncTask <Void, Void, String>{
    	@Override
    	protected String doInBackground(Void... string){
    		try{
    			String result = new UrlFetcher().getUrl("http://www.treecommerce.nl/stocklink/index.php?user=wtmdeboer&hash=cf07d18efe26f1461eb1a135cc8d08fe&lid=551534&fields=plantnaam%2cmaatomschrijving%2cqty%2cfoto%2copm&output=json");	
    			return result;
    		} catch (IOException ioe){
    			Log.e("Geen URL gepakt", "failed to fetch URL, ioe");
    		}
    		return null;
    	}
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.catalog, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_contact) {
        	Intent intent = new Intent (this, ContactActivity.class);
        	startActivity(intent);
        }
    	
        if (id == R.id.action_home) {
        	Intent i = new Intent (this, MainActivity.class);
        	startActivity(i);
        }
        
        if (id == R.id.action_cart) {
        	Intent i = new Intent (this, ShoppingCartActivity.class);
        	startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
