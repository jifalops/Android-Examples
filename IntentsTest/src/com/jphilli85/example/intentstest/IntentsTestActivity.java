package com.jphilli85.example.intentstest;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IntentsTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ((Button) findViewById(R.id.uriButton)).setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				String geo = "geo:42.32605505,-83.8796296?z=14";
				String url = "http://maps.google.com?ll=42.32605505,-83.8796296?z=14";				
//				target += ((EditText) findViewById(R.id.uriEditText))
//				.getText().toString().trim();
				
				try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(geo))); }
				catch (ActivityNotFoundException e) {
					try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); }
					catch (ActivityNotFoundException e1) {
						Toast.makeText(IntentsTestActivity.this, "Activity not found for URI: " + url, 
								Toast.LENGTH_SHORT).show();
						
					}
				}
			}
		});
    }
}