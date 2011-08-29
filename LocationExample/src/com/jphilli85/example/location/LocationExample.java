package com.jphilli85.example.location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Geocoder;
import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class LocationExample extends Activity implements LocationListener {
	private static final String TAG = "LocationDemo";
	private static final String[] S = { "Out of Service",
			"Temporarily Unavailable", "Available" };

	private TextView output;
	private LocationManager locationManager;
	private String bestProvider;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Get the output UI
		output = (TextView) findViewById(R.id.output);

		// Get the location manager
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// List all providers:
		List<String> providers = locationManager.getAllProviders();
		for (String provider : providers) {
			printProvider(provider);
		}

		Criteria criteria = new Criteria();
		bestProvider = locationManager.getBestProvider(criteria, true);
		output.append("\n\nBEST Provider:\n");
		printProvider(bestProvider);

		output.append("\n\nLocations (starting with last known):");
		Location location = locationManager.getLastKnownLocation(bestProvider);
		printLocation(location);
		
		getAddressFromLocation(location, this, new GeocoderHandler());
	}

	/** Register for the updates when Activity is in foreground */
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(bestProvider, 20000, 1000, this);
	}

	/** Stop the updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	public void onLocationChanged(Location location) {
		printLocation(location);
	}

	public void onProviderDisabled(String provider) {
		// let okProvider be bestProvider
		// re-register for updates
		output.append("\n\nProvider Disabled: " + provider);
	}

	public void onProviderEnabled(String provider) {
		// is provider better than bestProvider?
		// is yes, bestProvider = provider
		output.append("\n\nProvider Enabled: " + provider);
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		output.append("\n\nProvider Status Changed: " + provider + ", Status="
				+ S[status] + ", Extras=" + extras);
	}

	private void printProvider(String provider) {
		LocationProvider info = locationManager.getProvider(provider);
		output.append(info.toString() + "\n\n");
	}

	private void printLocation(Location location) {
		if (location == null)
			output.append("\nLocation[unknown]\n\n");
		else {
			output.append("\n\n" + location.toString());
			getAddressFromLocation(location, this, new GeocoderHandler());
		}
	}

	
	
	
	public static void getAddressFromLocation(
            final Location location, final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override public void run() {
            	if (location == null) return;
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());   
                String result = null;
                try {
                    List<Address> list = geocoder.getFromLocation(
                            location.getLatitude(), location.getLongitude(), 5);
                    if (list != null && list.size() > 0) {                    	
                    	for (Address a : list){
                    	//Address a = list.get(0);
                    	result += "\nsize: " + String.valueOf(list.size()) + "\n"+ a.toString();
                    	for (int i=0; i<=a.getMaxAddressLineIndex(); ++i) 
                        	 result += "\nline "+i+": " + a.getAddressLine(i) + "\n";
                    	}
                    	//result=list.toString();
                    	//Address address = list.get(0);
                        // sending back first address line and locality
                        //result = address.getAddressLine(1) + " - " + address.getCountryCode();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Impossible to connect to Geocoder", e);
                } finally {
                    Message msg = Message.obtain();
                    msg.setTarget(handler);
                    if (result != null) {
                        msg.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        msg.setData(bundle);
                    } else 
                        msg.what = 0;
                    msg.sendToTarget();
                }
            }
        };
        thread.start();
    }
    
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String result;
            switch (message.what) {
            case 1:
                Bundle bundle = message.getData();
                result = bundle.getString("address");
                break;
            default:
                result = null;
            }
            // replace by what you need to do
            output.append("\n\n" + result);
        }   
    }
	
}