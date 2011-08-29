package com.jphilli85.example.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewExample extends Activity {
	private static final String HOST = "localhost";
	private static final String INTERFACE = "Android";
	
	private WebView mWebView; 
	private WebSettings mWebSettings;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("http://" + HOST);
        mWebView.addJavascriptInterface(new JavaScriptInterface(this), INTERFACE);
        
        mWebView.setWebViewClient(new WebViewClient() {
        	@Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getHost().equals(HOST)) {
                    // This is my web site, so do not override; let my WebView load the page
                    return false;
                }
                // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        });
        
        mWebView.setWebChromeClient(new WebChromeClient() {
        	public boolean onConsoleMessage(ConsoleMessage cm) {
        	    Log.d("MyApplication", cm.message() + " -- From line "
        	                         + cm.lineNumber() + " of "
        	                         + cm.sourceId() );
        	    return true;
            }
        });
        
        
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
	}
}