package com.ALS.als_testoftheday1;


import java.util.GregorianCalendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReciever extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent intentQuestion = new Intent(context,
				TestOfTheDay.class);
		PendingIntent pintent = PendingIntent.getActivity(context, 0,
				intentQuestion, 0);

		final NotificationManager mgr = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification quesitonNotification = new Notification(
				R.drawable.testpaper, "Test Of The Day",
				System.currentTimeMillis());

		quesitonNotification.setLatestEventInfo(context, "Test Of The Day",
				"Today's Test is here!", pintent);
		

		// After uncomment this line you will see number of notification arrived
		// note.number=2;
		quesitonNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		mgr.notify(1234, quesitonNotification);
		
		
		
		
		
		
		Long time = new GregorianCalendar().getTimeInMillis() + 24 * 60 * 60 * 1000;

		Intent intentAlarm = new Intent(context, AlarmReciever.class);

		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, 1, intentAlarm,
				
						PendingIntent.FLAG_UPDATE_CURRENT));
		
		Toast.makeText(context, "Alarm scheduled", Toast.LENGTH_LONG).show();
	}

}
