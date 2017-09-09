package com.ALS.als_testoftheday1;

//import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
//import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ALS.als_testoftheday1.adapter.NavDrawerListAdapter;
import com.ALS.als_testoftheday1.model.NavDrawerItem;

public class MainActivity extends ActionBarActivity {
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	AlertDialog subjectDialog;
	
	
    boolean doubleBackToExitPressedOnce;

	int TIME_DIALOG_ID = 1;
	int hourSelected, minuteSelected;
	int mHour, mMinute;
	
	Calendar c = Calendar.getInstance();

	
	static String selectedSubject="";
	 
	static int selectedDay;
	
	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
				
		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
//		mDrawerRelativeLayout = (RelativeLayout)findViewById(R.id.left_drawer);
		
//		ImageView headerImage = (ImageView)findViewById(R.drawable.alslogo);
		
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		
//		 View header = getLayoutInflater().inflate(R.layout.header, null);

		  //addHeaderView is to add custom content into first row
//		    mDrawerList.addHeaderView(header);
//		    yourListView.setAdapter(yourAdapter);
//		
//		View header=getLayoutInflater().inflate(R.layout.header, null);
//		  ImageView pro=(ImageView)header.findViewById(R.id.img_first);
//		  pro.setOnClickListener(new OnClickListener() {
//		   @Override
//		   public void onClick(View arg0) {
//		    // TODO Auto-generated method stub
//		    Toast.makeText(getApplicationContext(), "Clicked",                                                                                    Toast.LENGTH_SHORT).show();
//		   }
//		  });
//		  mDrawerList.addHeaderView(header);
//		mDrawerList.addHeaderView(headerImage);
		
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Find People
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// Photos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// Communities, Will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		// Pages
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		// What's hot, We  will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));


		

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	mDrawerLayout.openDrawer(mDrawerList);
	
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}
	
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) 
//	{
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.mymeny, menu);
//		return true;
//	}
//	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {	
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	


//
//	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
//		// the callback received when the user "sets" the TimePickerDialog
//		// in the dialog
//		
//		public void onTimeSet(TimePicker view, int hourOfDay, int min) {
//			hourSelected = hourOfDay;
//			minuteSelected = min;
//
//			 final Calendar c = Calendar.getInstance();
//	         int mYear = c.get(Calendar.YEAR);
//	         int mMonth = c.get(Calendar.MONTH);
//	         int mDay = c.get(Calendar.DAY_OF_MONTH)+1;
//	         
//	         GregorianCalendar gc = new GregorianCalendar(mYear, mMonth, mDay, hourSelected, minuteSelected,0);
//	         if(minuteSelected==mMinute)
//	         {
//	        	 Toast.makeText(getApplicationContext(), "Alarm not scheduled", Toast.LENGTH_LONG).show();
//	         }
//	         else
//	         scheduleTheAlarm(gc.getTimeInMillis());
//		}
//	};
//
//
//	//protected Dialog onCreateDialog(int id) {
////		return new TimePickerDialog(getActivity(), mTimeSetListener, mHour, mMinute,
////				false);
//	//}
//
//	public Dialog createDialog(int id) {
//
//		return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,
//				false);
//	}
//
//
//	void scheduleTheAlarm(long time)
//	{
//		Long time1 = new GregorianCalendar().getTimeInMillis() + 1 * 10 * 1000;
//
//		Intent intentAlarm = new Intent(this, AlarmReciever.class);
//
//		AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//
//		alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent
//				.getBroadcast(this, 1, intentAlarm,
//						PendingIntent.FLAG_UPDATE_CURRENT));
//		Toast.makeText(this, "Alarm scheduled", Toast.LENGTH_LONG).show();
//	}

	
	
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) 
//	{
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.alarm_settings) {
//			mHour = c.get(Calendar.HOUR_OF_DAY);
//			mMinute = c.get(Calendar.MINUTE);
//			showDialog(TIME_DIALOG_ID);
//			
//			return true;
//		}
//		
//		
//		if (id == R.id.help) {
//			
//			Intent intent;
//			intent = new Intent(getApplicationContext(),Help.class);
//			startActivity(intent);
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// toggle nav drawer on selecting action bar app icon/title
//		if (mDrawerToggle.onOptionsItemSelected(item)) {
//			return true;
//		}
//		// Handle action bar actions click
//		switch (item.getItemId()) {
//		case R.id.action_settings:
//			return true;
//		default:
//			return super.onOptionsItemSelected(item);
//		}
//	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
//		 if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//		menu.findItem(R.id.alarm_settings).setVisible(!drawerOpen);
//		menu.findItem(R.id.help).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	
	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new OurTeachersFragment();
			break;
		case 2:
			fragment = new CoursesAndFacultyFragment();
			break;
		case 3:
			fragment = new AdmissionsFragment();
			break;
		case 4:
			fragment = new ContactUsFragment();
			break;
		case 5:
			fragment = new AboutAlsFragment();
			break;
		case 6:
			fragment = new HelpFragment();
			break;
		case 7:
		{
//			context = getActivity();
//			Uri marketUri = Uri.parse("market://details?id=" + getPackageName());
//			startActivity(new Intent.ACTION_VIEW).setData(marketUri);
			Uri uri = Uri.parse("market://details?id=" + getPackageName());
		    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
		    // To count with Play market backstack, After pressing back button, 
		    // to taken back to our application, we need to add following flags to intent. 
		    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
		                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
		                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		    try {
		        startActivity(goToMarket);
		    } catch (ActivityNotFoundException e) {
		        startActivity(new Intent(Intent.ACTION_VIEW,
		                Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
		    }
			break;
		}

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	public void previousTests(View view)
	{
		showSubjectChooserDialog();
	}



	public void teachers(View view)
	{
		Intent intent;
		intent = new Intent(getApplicationContext(), Teachers.class);
		startActivity(intent);
	}

	public void openTestOfTheDayActivity(View view)
	{
		Intent intent;
		intent = new Intent(getApplicationContext(),TestOfTheDay.class);
		startActivity(intent);
	}

	public void openQuoteOfTheDayActivity(View view)
	{
		Intent intent;
		intent = new Intent(getApplicationContext(),QuoteOfTheDayActivity.class);
		startActivity(intent);
	}

	public void openWordOfTheDayActivity(View view)
	{
//		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.merriam-webster.com/word-of-the-day/"));
//		startActivity(browserIntent);
		Intent intent;
		intent = new Intent(getApplicationContext(),WordOfTheDay.class);
		startActivity(intent);
	}

	public void openDidYouKnowActivity(View view)
	{
		Intent intent;
		intent = new Intent(getApplicationContext(),DoYouKnow.class);
		startActivity(intent);
	}

	void showSubjectChooserDialog()
	{

		final Intent intent = new Intent(getApplicationContext(),ChooseTestNumber.class);
		// Strings to Show In Dialog with Radio Buttons
		final CharSequence[] items = {" History "," Science "," Economics "," Polity ","Geography","Current Affairs","Mixed Bag"};
		            
		                // Creating and Building the Dialog 
		                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		                builder.setTitle("Select The Subject");
		                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int item) {
		                   
		                    
		                    switch(item)
		                    {
		                        case 0:
		                        	selectedSubject="History";
		                        	selectedDay=1;
		                        	startActivity(intent);
		                                 break;
		                        case 1:
		                        	selectedSubject="Science";
		                        	selectedDay=4;

		                        	startActivity(intent);
		                                // Your code when 2nd  option seletced
		                                
		                                break;
		                        case 2:
		                        	selectedSubject="Economics";
		                        	selectedDay=3;

		                        	startActivity(intent);
		                               // Your code when 3rd option seletced
		                                break;
		                        case 3:
		                        	selectedSubject="Polity";
		                        	selectedDay=2;

		                        	startActivity(intent);
		                                 // Your code when 4th  option seletced            
		                                break;
		                        case 4:
		                        	selectedSubject="Geography";
		                        	selectedDay=7;

		                        	startActivity(intent);
		                                 // Your code when 4th  option seletced            
		                                break;
		                        case 5:
		                        	selectedSubject="CurrentAffairs";
		                        	selectedDay=5;

		                        	startActivity(intent);
		                                 // Your code when 4th  option seletced            
		                                break;
		                        case 6:
		                        	selectedSubject="MixedBag";
		                        	selectedDay=6;

		                        	startActivity(intent);
		                                 // Your code when 4th  option seletced            
		                                break;
		                        
		                    }
		                    subjectDialog.dismiss();    
		                    }
		                });
		                subjectDialog = builder.create();
		                subjectDialog.show();
	}

	
	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
	        super.onBackPressed();
	        return;
	    }

	    this.doubleBackToExitPressedOnce = true;
	    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

	    new Handler().postDelayed(new Runnable() {

	        @Override
	        public void run() {
	            doubleBackToExitPressedOnce=false;                       
	        }
	    }, 2000);
	} 

}
