package com.jphilli85.example.map;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MapExample extends Activity implements LocationListener, OnClickListener {
	private static final String TAG = "MapDemo";
	private static final String[] S = { "Out of Service",
			"Temporarily Unavailable", "Available" };

	private TextView mTextView;
	private LocationManager mLocationManager;
	private String mBestProvider;
	private Location mLocation;
	private Button mButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.output);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mBestProvider = mLocationManager.getBestProvider(new Criteria(), true);
        mLocation = mLocationManager.getLastKnownLocation(mBestProvider);
        mTextView.setText(mLocation.toString());
        
        mButton.setOnClickListener(this);
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		mLocationManager.requestLocationUpdates(mBestProvider, 10000, 1000, this);
	}
    
    @Override
	protected void onPause() {
		super.onPause();
		mLocationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location arg0) {
		mLocation = arg0;		
		mTextView.append("\n\n" + mLocation.toString());
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		if ((Button)v == mButton) 
			startActivity(new Intent(this, MapPage.class)
			.putExtra("lat", mLocation.getLatitude())
			.putExtra("lon", mLocation.getLongitude()));
		
	}
}