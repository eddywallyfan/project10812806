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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class AssortimentActivity extends ListActivity {
	
	private static final String TAG = "hopsakee";
	// Sla JSON nodes op in string
	private static final String TAG_PN = "plantnaam";
	private static final String TAG_MAAT = "maatomschrijving";
	private static final String TAG_AANTAL = "qty";
	private static final String TAG_FOTO = "foto";
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assortiment);

        FetchItemsTask task = new FetchItemsTask();
        task.execute();
        String answer = "";
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
        try {
			JSONArray jsonArray = new JSONArray(answer);
			//Log.i(TAG, "jsonarraylog"+jsonArray);
			for(int i = 0; i<jsonArray.length();i++){
				JSONObject plant = jsonArray.getJSONObject(i);
				//Log.i(TAG, "plant1voor1: "+plant.getString("plantnaam"));
				String plantnaam = plant.getString(TAG_PN);
				String maat = plant.getString(TAG_MAAT);
				String aantal = plant.getString(TAG_AANTAL);
				String foto = plant.getString(TAG_FOTO);
				
				// Maak hashmap
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(TAG_PN, plantnaam);
				map.put(TAG_MAAT, maat);
				map.put(TAG_AANTAL, aantal);
				map.put(TAG_FOTO, foto);
				
				plantList.add(map);
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        ListAdapter adapter = new SimpleAdapter(this, plantList,
            	R.layout.list_item,
            new String[] { TAG_PN, TAG_MAAT, TAG_AANTAL}, new int[] {
                    R.id.PlantNaam, R.id.PlantMaat, R.id.PlantAantal });

        setListAdapter(adapter);
    
        //ListView lv = getListView();
    }
    
    private class FetchItemsTask extends AsyncTask <Void, Void, String>{
    	@Override
    	protected String doInBackground(Void... string){
    		try{
    			String result = new UrlFetcher().getUrl("http://www.treecommerce.nl/stocklink/index.php?user=wtmdeboer&hash=cf07d18efe26f1461eb1a135cc8d08fe&lid=551534&fields=plantnaam%2cmaatomschrijving%2cqty%2cfoto&output=json");
    			
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
		getMenuInflater().inflate(R.menu.winkelwagen, menu);
		return true;
	}
    
}
