/* John Wesselingh project
 * 10812806
 * Het scherm waar de winkelwagen op te zien is. 
 * De klant kan kiezen voor het plaatsen van een bestelling, teruggaan naar de lijst. 
 * of de plant uit de winkelwagen halen.
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class WinkelwagenActivity extends Activity {

	private List<Product> mCartList;
	private ProductAdapter mProductAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_winkelwagen);

		mCartList = ShoppingCartHelper.getCartList();
		
		// Make sure to clear the selections
		for(int i=0; i<mCartList.size(); i++) {
			mCartList.get(i).selected = false;
		}
		
		// Create the list
		final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
		mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true);
		listViewCatalog.setAdapter((ListAdapter) mProductAdapter);
		
		listViewCatalog.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
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
	
	@Override
	protected void onResume() {
		super.onResume();
		// Refresh the data
		if(mProductAdapter != null) {
			mProductAdapter.notifyDataSetChanged();
		}
	}
}
