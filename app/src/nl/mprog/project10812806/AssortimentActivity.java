/* John Wesselingh project
 * 10812806
 * Assortiment activity, hier komt een lijst met planten.
 */
package nl.mprog.project10812806;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AssortimentActivity extends Activity {

	private List<Product> mProductList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assortiment);
		
		// Obtain a reference to the product catalog
		mProductList = ShoppingCartHelper.getCatalog(getResources());
		
		// Create the list
		ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
		listViewCatalog.setAdapter(new ProductAdapter(mProductList, getLayoutInflater(), false));
		listViewCatalog.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent productDetailsIntent = new Intent(getBaseContext(),PlantActivity.class);
				productDetailsIntent.putExtra(ShoppingCartHelper.PRODUCT_INDEX, position);
				startActivity(productDetailsIntent);
			}
		});
 
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
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent (this, PlantActivity.class);
    	startActivity(intent);
	}
}
