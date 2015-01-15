/* John Wesselingh project
 * 10812806
 * Assortiment activity, hier komt een lijst met planten.
 */
package nl.mprog.project10812806;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import nl.mprog.project10812806.JSONParser;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AssortimentActivity extends Activity {

	/*ListView list;
	TextView pnaam;
	TextView pmaat;
	TextView paantal;
	Button Btngetdata;
	ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();
	
	//URL to get JSON Array
	private static String url = "http://www.treecommerce.nl/stocklink/index.php?user=wtmdeboer&hash=cf07d18efe26f1461eb1a135cc8d08fe&lid=551534&fields=trefnaam%2cplantnaam%2cmaatomschrijving%2cqty%2cprijs%2cfoto%2copm&output=json";
	
	//JSON Node Names ,,,,prijs,foto,opm
	private static final String TAG_TN = "trefnaam";
	private static final String TAG_PN = "plantnaam";
	private static final String TAG_MAAT = "maatomschrijving";
	private static final String TAG_AANTAL = "qty";
	private static final String TAG_PRIJS = "prijs";
	private static final String TAG_FOTO = "foto";
	private static final String TAG_OPMERKING = "opm";
	JSONArray android = null;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assortiment);*/
	ListView list;
	TextView ver;
	TextView name;
	TextView api;
	Button Btngetdata;
	ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();
	//URL to get JSON Array
	private static String url = "http://api.learn2crack.com/android/jsonos/";
	//JSON Node Names
	private static final String TAG_OS = "android";
	private static final String TAG_VER = "ver";
	private static final String TAG_NAME = "name";
	private static final String TAG_API = "api";
	JSONArray android = null;
	    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assortiment);
        oslist = new ArrayList<HashMap<String, String>>();
        Btngetdata = (Button)findViewById(R.id.getdata);
        Btngetdata.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View view) {
        		new JSONParse().execute();
        	}
        });
    }
    
	private class JSONParse extends AsyncTask<String, String, JSONObject> {
		private ProgressDialog pDialog;
		@Override
        protected void onPreExecute() {
			super.onPreExecute();
            ver = (TextView)findViewById(R.id.pnaam);      
            name = (TextView)findViewById(R.id.pmaat);
            api = (TextView)findViewById(R.id.paantal);
            pDialog = new ProgressDialog(AssortimentActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
		}
      
		@Override
        
		protected JSONObject doInBackground(String... args) {       
			JSONParser jParser = new JSONParser();      
			// Getting JSON from URL       
			JSONObject json = jParser.getJSONFromUrl(url);        
			return json;     
		}
       
		@Override     
		protected void onPostExecute(JSONObject json) {
			pDialog.dismiss();
			try {
				// Getting JSON Array from URL            
				android = json.getJSONArray(TAG_OS);           
				for(int i = 0; i < android.length(); i++){           
					JSONObject c = android.getJSONObject(i);           
					
					// Storing  JSON item in a Variable           
					String ver = c.getString(TAG_VER);           
					String name = c.getString(TAG_NAME);           
					String api = c.getString(TAG_API);           
					
					// Adding value HashMap key => value           
					HashMap<String, String> map = new HashMap<String, String>();   
					map.put(TAG_VER, ver);        
					map.put(TAG_NAME, name);          
					map.put(TAG_API, api);
					
					oslist.add(map);           
					list=(ListView)findViewById(R.id.list);          
					ListAdapter adapter = new SimpleAdapter(AssortimentActivity.this, oslist,
							R.layout.list_item,
							new String[] { TAG_VER,TAG_NAME, TAG_API }, new int[] {                 
							R.id.pnaam,R.id.pmaat, R.id.paantal});
					list.setAdapter(adapter);    
					list.setOnItemClickListener(new AdapterView.OnItemClickListener() {          
						@Override              
						public void onItemClick(AdapterView<?> parent, View view,                   
								int position, long id) {
							Toast.makeText(AssortimentActivity.this, "You Clicked at "+oslist.get(+position).get("name"), Toast.LENGTH_SHORT).show();            
						}              
					});           
				}       
			} 
			catch (JSONException e) {        
				e.printStackTrace();       
			}      
		}    
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
