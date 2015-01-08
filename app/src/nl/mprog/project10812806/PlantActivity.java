package nl.mprog.project10812806;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlantActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant);
	}

	public void winkelwagen (View view) {
    	Intent intent = new Intent (this, WinkelwagenActivity.class);
    	startActivity(intent);
    	}
}
