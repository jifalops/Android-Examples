package com.jphilli85.example.executorcompletionservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.jphilli85.example.executorservice.R;
import com.recellular.internal.Library.HttpHelper;

public class ExecutorCompletionServiceTestActivity extends Activity {    
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        try {
			(new PostTester()).sendPosts();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public class PostTester {
    	private static final int NTHREADS = 10;
        private final ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        
        void sendPosts() throws JSONException, IllegalArgumentException, 
        		InterruptedException, ExecutionException, IllegalStateException, IOException {
        	final String url =  
        		"http://recelltech.com/test/post_json_request.php";	
        	
        	final JSONObject request = new JSONObject();
        	request.put("a", 1);
        	request.put("b", 2);
        	request.put("c", 3);
        	
        	final List<NameValuePair> entityList = new ArrayList<NameValuePair>();			
            entityList.add(new BasicNameValuePair("request", request.toString()));
            
        	final HttpClient client = HttpHelper.getClient();
        	final HttpPost post = HttpHelper.preparePost(url, entityList);
        	
        	CompletionService<HttpResponse> completionService = 
        		new ExecutorCompletionService<HttpResponse>(executor);
        	
        	for (int i = 0; i < NTHREADS; ++i) {
        		completionService.submit(new Callable<HttpResponse>() {					
					@Override
					public HttpResponse call() throws Exception {						
						return client.execute(post);
					}
				});
        	}
        	

    		for (int i = 0; i < NTHREADS; ++i) {
    			Future<HttpResponse> f = completionService.take();
    			HttpResponse response = f.get();
    			handleResponse(response);
    		}	
        }
        
        void handleResponse(HttpResponse response) throws IllegalStateException, IOException {
        	HttpEntity entity = response.getEntity();

            // Not a repeatable entity, keep list initialization after getContent.
            InputStream is = entity.getContent();
            List<String> responseLines = new ArrayList<String>();
            
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is,"iso-8859-1"), 8);
            
            String line = null;            
            while ((line = reader.readLine()) != null) {
            	responseLines.add(line);
            }    
            reader.close();
            is.close();
            
            Toast.makeText(ExecutorCompletionServiceTestActivity.this, 
            		responseLines.get(0), Toast.LENGTH_LONG).show();
        }
    }
}