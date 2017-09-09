package com.ALS.als_testoftheday1;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class HomeFragment extends Fragment {

	Context context;
	Button test;
public HomeFragment(){}
	

ImageView imageView1;
RoundImage roundedImage;

int TIME_DIALOG_ID = 1;
int hourSelected, minuteSelected;
int mHour, mMinute;
Calendar c = Calendar.getInstance();

AlertDialog subjectDialog;
	
static String selectedSubject="";
 

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {


	setHasOptionsMenu(true);

    View rootView = inflater.inflate(R.layout.main_screen, container, false);
     
    
//    test = (Button)getActivity().findViewById(R.id.testoftheday);
//    
//    test.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//        	Intent intent;
//        	intent = new Intent(getActivity(),TestOfTheDay.class);
//        	startActivity(intent);
//        }
//    });
    
    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    
    return rootView;

    

}

//public void previousTests(View view)
//{
//	showSubjectChooserDialog();
//}
//
//
//
//public void teachers(View view)
//{
//	Intent intent;
//	intent = new Intent(getActivity(), Teachers.class);
//	startActivity(intent);
//}
//
//public void openTestOfTheDayActivity(View view)
//{
//	Intent intent;
//	intent = new Intent(getActivity(),TestOfTheDay.class);
//	startActivity(intent);
//}
//
//public void openQuoteOfTheDayActivity(View view)
//{
//	Intent intent;
//	intent = new Intent(getActivity(),QuoteOfTheDayActivity.class);
//	startActivity(intent);
//}
//
//public void openWordOfTheDayActivity(View view)
//{
//	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.merriam-webster.com/word-of-the-day/"));
//	startActivity(browserIntent);
//}
//
//public void openDidYouKnowActivity(View view)
//{
//	Intent intent;
//	intent = new Intent(getActivity(),DoYouKnow.class);
//	startActivity(intent);
//}
//
//




@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.mymeny, menu);
    super.onCreateOptionsMenu(menu,inflater);
}
	

@Override
public boolean onOptionsItemSelected(MenuItem item) 
{
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();
	if (id == R.id.alarm_settings) {
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
		createDialog(TIME_DIALOG_ID).show();

		
		return true;
	}
	
	
	if (id == R.id.help) {
		
		Intent intent;
		intent = new Intent(getActivity(),Help.class);
		startActivity(intent);
		return true;
	}
	
	if (id == R.id.rateapp) {
		context = getActivity();
//		Uri marketUri = Uri.parse("market://details?id=" + getPackageName());
//		startActivity(new Intent.ACTION_VIEW).setData(marketUri);
		Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
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
	                Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
	    }
		return true;
	}
	return super.onOptionsItemSelected(item);
}




//public void ScheduleAlarm(View view) {
//
//	mHour = c.get(Calendar.HOUR_OF_DAY);
//	mMinute = c.get(Calendar.MINUTE);
//
//	showDialog(TIME_DIALOG_ID);
//
//	
//
//	
//}


private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
	// the callback received when the user "sets" the TimePickerDialog
	// in the dialog
	
	public void onTimeSet(TimePicker view, int hourOfDay, int min) {
		hourSelected = hourOfDay;
		minuteSelected = min;

		 final Calendar c = Calendar.getInstance();
         int mYear = c.get(Calendar.YEAR);
         int mMonth = c.get(Calendar.MONTH);
         int mDay = c.get(Calendar.DAY_OF_MONTH)+1;
         
         GregorianCalendar gc = new GregorianCalendar(mYear, mMonth, mDay, hourSelected, minuteSelected,0);
         if(minuteSelected==mMinute)
         {
        	 Toast.makeText(getActivity(), "Alarm not scheduled", Toast.LENGTH_LONG).show();
         }
         else
         scheduleTheAlarm(gc.getTimeInMillis());
	}
};


//protected Dialog onCreateDialog(int id) {
//	return new TimePickerDialog(getActivity(), mTimeSetListener, mHour, mMinute,
//			false);
//}

public Dialog createDialog(int id) {

	return new TimePickerDialog(getActivity(), mTimeSetListener, mHour, mMinute,
			false);
}


void scheduleTheAlarm(long time)
{
	Long time1 = new GregorianCalendar().getTimeInMillis() + 1 * 10 * 1000;

	Intent intentAlarm = new Intent(getActivity(), AlarmReciever.class);

	AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

	alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent
			.getBroadcast(getActivity(), 1, intentAlarm,
					PendingIntent.FLAG_UPDATE_CURRENT));
	Toast.makeText(getActivity(), "Alarm scheduled", Toast.LENGTH_LONG).show();
}





//void showSubjectChooserDialog()
//{
//
//	final Intent intent = new Intent(getActivity(),PreviousTestsActivity.class);
//	// Strings to Show In Dialog with Radio Buttons
//	final CharSequence[] items = {" History "," Science "," Economics "," Polity ","Geography","Current Affairs"};
//	            
//	                // Creating and Building the Dialog 
//	                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//	                builder.setTitle("Select The Subject");
//	                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
//	                public void onClick(DialogInterface dialog, int item) {
//	                   
//	                    
//	                    switch(item)
//	                    {
//	                        case 0:
//	                        	selectedSubject="History";
//	                        	startActivity(intent);
//	                                 break;
//	                        case 1:
//	                        	selectedSubject="Science";
//	                        	startActivity(intent);
//	                                // Your code when 2nd  option seletced
//	                                
//	                                break;
//	                        case 2:
//	                        	selectedSubject="Economics";
//	                        	startActivity(intent);
//	                               // Your code when 3rd option seletced
//	                                break;
//	                        case 3:
//	                        	selectedSubject="Polity";
//	                        	startActivity(intent);
//	                                 // Your code when 4th  option seletced            
//	                                break;
//	                        case 4:
//	                        	selectedSubject="Geography";
//	                        	startActivity(intent);
//	                                 // Your code when 4th  option seletced            
//	                                break;
//	                        case 5:
//	                        	selectedSubject="CurrentAffairs";
//	                        	startActivity(intent);
//	                                 // Your code when 4th  option seletced            
//	                                break;
//	                        
//	                    }
//	                    subjectDialog.dismiss();    
//	                    }
//	                });
//	                subjectDialog = builder.create();
//	                subjectDialog.show();
//}


	
}
