package com.ALS.als_testoftheday1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashScreen extends Activity {

	
	TextView txtMessage;
	 
    // Animation
    Animation animFadein;

 
	private static int SPLASH_TIME_OUT = 7000;

	@Override
	public void onAttachedToWindow() {
	    super.onAttachedToWindow();
	    Window window = getWindow();
	    window.setFormat(PixelFormat.RGBA_8888);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_splash_screen);


			ImageView img = (ImageView)findViewById(R.id.imgLogo);
		
//	        // load the animation
//	        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
//	                R.anim.together);        
//	        
//	        img.startAnimation(animFadein);
	        
			StartAnimations();

			
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() 
			{
			Intent i = new Intent(SplashScreen.this, MainActivity.class);
			startActivity(i);
			finish();
			}
			}, SPLASH_TIME_OUT);
			}
			
	private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
 
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.imgLogo);
        iv.clearAnimation();
        iv.startAnimation(anim);
 
    }
	}

