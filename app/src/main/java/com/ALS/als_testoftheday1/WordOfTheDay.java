package com.ALS.als_testoftheday1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WordOfTheDay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word_of_the_day1);
		openWebPage();
	}

	public void openWebPage()
	{
		WebView mWebView= new WebView(this);
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
	    mWebView.loadUrl("http://www.merriam-webster.com/word-of-the-day");
	    setContentView(mWebView);
	}

	
}
