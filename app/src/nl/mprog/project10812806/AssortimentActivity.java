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

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AssortimentActivity extends ListActivity implements OnItemClickListener{
	
	// Tag om te loggen
	private static final String TAG = "hopsakee";
	// Sla JSON nodes op in string
	private static final String TAG_PN = "plantnaam";
	private static final String TAG_MAAT = "maatomschrijving";
	private static final String TAG_AANTAL = "qty";
	private static final String TAG_FOTO = "foto";
	private static final String TAG_OPM = "opm";
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assortiment);
       
        // Start een FetchItemsTask die een string maakt van de URL 
        FetchItemsTask task = new FetchItemsTask();
        task.execute();
        String answer = "";
        
        // Maak een arraylist waar de listview mee gevuld kan worden 
        ArrayList<HashMap<String, String>> plantList = new ArrayList<HashMap<String, String>>();
       
        try {
			answer = task.get();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Maak een JSONArray van de string
        try {
			JSONArray jsonArray = new JSONArray(answer);
			for(int i = 0; i<jsonArray.length();i++){
				JSONObject plant = jsonArray.getJSONObject(i);

				String plantnaam = plant.getString(TAG_PN);
				String maat = plant.getString(TAG_MAAT);
				String aantal = plant.getString(TAG_AANTAL);
				String foto = plant.getString(TAG_FOTO);
				String opm = plant.getString(TAG_OPM);
				
				// Maak hashmap
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(TAG_PN, plantnaam);
				map.put(TAG_MAAT, maat);
				map.put(TAG_AANTAL, aantal);
				map.put(TAG_FOTO, foto);
				map.put(TAG_OPM, opm);
				
				
				plantList.add(map);
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
       // Context context = getBaseContext();
		//ArrayList<HashMap<String, String>> map = null;
		//CustomListAdapter adapter = new CustomListAdapter(context, map);
        ListAdapter adapter = new SimpleAdapter(this, plantList,
            	R.layout.list_item,
            new String[] { TAG_PN, TAG_MAAT, TAG_AANTAL}, new int[] {
                    R.id.PlantNaam, R.id.PlantMaat, R.id.PlantAantal 
                    });

        setListAdapter(adapter);
    
        ListView lv = getListView();   
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			    String naam = ((TextView) view.findViewById(R.id.PlantNaam)).getText().toString();
	            String maat = ((TextView) view.findViewById(R.id.PlantMaat)).getText().toString();
	            String aantal = ((TextView) view.findViewById(R.id.PlantAantal)).getText().toString();

	            // Start activity
	            Intent intent = new Intent(getApplicationContext(), PlantActivity.class);
	            intent.putExtra(TAG_PN, naam);
	            intent.putExtra(TAG_MAAT, maat);
	            intent.putExtra(TAG_AANTAL, aantal);
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
    			Log.e(TAG, "failed to fetch URL, ioe");
    		}
    		return null;
    	}
    }
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.assortiment, menu);
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
    	
        if (id == R.id.action_home) {
        	Intent i = new Intent (this, StartschermActivity.class);
        	startActivity(i);
        }
        
        if (id == R.id.action_cart) {
        	Intent i = new Intent (this, ShoppingCartActivity.class);
        	startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
    
}
