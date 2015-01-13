/* John Wesselingh project
 * 10812806
 * Plantactivity: Laat een geselecteerde plant zien en de klant een aantal selecteren. 
 * Hierna kan de klant de plant toevoegen aan een winkelwagen of terug naar de lijst.
 */
package nl.mprog.project10812806;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlantActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant);
		
		List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());
		int productIndex = getIntent().getExtras().getInt(
				ShoppingCartHelper.PRODUCT_INDEX);
		final Product selectedProduct = catalog.get(productIndex);
		
		// Set the proper image and text
		ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);
		productImageView.setImageDrawable(selectedProduct.productImage);
		
		TextView productTitleTextView = (TextView) findViewById(R.id.TextViewProductTitle);
		productTitleTextView.setText(selectedProduct.title);
		
		TextView productDetailsTextView = (TextView) findViewById(R.id.TextViewProductDetails);
		productDetailsTextView.setText(selectedProduct.description);
		
		
		// Update the current quantity in the cart
		TextView textViewCurrentQuantity = (TextView) findViewById(R.id.textViewCurrentlyInCart);
		textViewCurrentQuantity.setText("Currently in Cart: "
		+ ShoppingCartHelper.getProductQuantity(selectedProduct));
		
		// Save a reference to the quantity edit text
		final EditText editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);
		
		Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);
		addToCartButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Check to see that a valid quantity was entered
				int quantity = 0;
				
				try {
					quantity = Integer.parseInt(editTextQuantity.getText()
					.toString());
					
					if (quantity < 0) {
						Toast.makeText(getBaseContext(),
						"Please enter a quantity of 0 or higher",
						Toast.LENGTH_SHORT).show();
						return;
					}
				} 
				
				catch (Exception e) {
					Toast.makeText(getBaseContext(),
					"Please enter a numeric quantity",
					Toast.LENGTH_SHORT).show();
					return;
				}
				// If we make it here, a valid quantity was entered
				ShoppingCartHelper.setQuantity(selectedProduct, quantity);
				// Close the activity
				finish();
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
	public void winkelwagen (View view) {
    	Intent intent = new Intent (this, WinkelwagenActivity.class);
    	startActivity(intent);
    	}
	
	public void startknop (View view) {
    	Intent intent = new Intent (this, AssortimentActivity.class);
    	startActivity(intent);
    	}
}
