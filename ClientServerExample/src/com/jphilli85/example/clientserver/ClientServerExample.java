package com.jphilli85.example.clientserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import android.widget.TextView;

public class ClientServerExample extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView t = (TextView) findViewById(R.id.text);
        
        t.setText(Secure.getString(getContentResolver(), Secure.ANDROID_ID));

        stuff();
    }
    
    public void stuff() {
    	String result = "";

    	//the year data to send

    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

    	nameValuePairs.add(new BasicNameValuePair("year","1980"));

    	//http post
    	//InputStream is;
    	try{

	        HttpClient httpclient = new DefaultHttpClient();

	        HttpPost httppost = new HttpPost("http://jphilli85.com/");

	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        HttpResponse response = httpclient.execute(httppost);

	        HttpEntity entity = response.getEntity();

	        InputStream is = entity.getContent();
	        
	        // convert response to string
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

	        StringBuilder sb = new StringBuilder();

	        
	        
	    	String line = null;
	
	    	while ((line = reader.readLine()) != null) {

	    		sb.append(line + "\n");

	    	}

	        is.close();

	        result=sb.toString();

    	}catch(Exception e){

	        Log.e("log_tag", "Error in http connection "+e.toString());

    	}

    	
    	//parse json data

    	try{
Log.i("ASIFUH", result);
			JSONObject json_data = new JSONObject(result);


                Log.i("log_tag","id: "+json_data.getInt("id")+

			    	", name: "+json_data.getString("name")+
			
			    	", sex: "+json_data.getInt("sex")+
			
			    	", birthyear: "+json_data.getInt("birthyear")

                );


    	}catch(JSONException e){

    	        Log.e("log_tag", "Error parsing data "+e.toString());

    	}
    }
}