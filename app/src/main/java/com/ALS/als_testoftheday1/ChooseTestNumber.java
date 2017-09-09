package com.ALS.als_testoftheday1;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseTestNumber extends Activity {

static int choice;
EditText chooseTest; 
static boolean isSubmitted=false;
int numberOfTests=4;
int finalIndex;
int limit;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_choose_test_number);
		
		chooseTest = (EditText)findViewById(R.id.testNumber);
		
//		Toast.makeText(this, "final index : " + PreviousTestsActivity.finalIndex, Toast.LENGTH_LONG).show();
		
		GregorianCalendar gc = new GregorianCalendar();
//		Calendar gc = Calendar.getInstance();
		int currentWeek = gc.get(Calendar.WEEK_OF_YEAR);
		int baseWeek = -4;
		
//		Toast.makeText(this, "Current week : " + currentWeek, Toast.LENGTH_LONG).show();
		
		int requiredWeek = currentWeek - baseWeek;
		finalIndex = 15 * (requiredWeek - 1) + 14;
		
		int day = gc.get(Calendar.DAY_OF_WEEK);


		if (day == 1)
			finalIndex = 15 * (requiredWeek - 1) + 14;
		else if (day != 1 && MainActivity.selectedSubject.equals("History"))
			finalIndex = 15 * (requiredWeek - 1) - 1;
		else {
			if (MainActivity.selectedDay > day && requiredWeek != 1) {
				finalIndex = 15 * (requiredWeek - 1) - 1;
			}
		}

		if(requiredWeek==1 && (MainActivity.selectedDay>day || (MainActivity.selectedDay==1 && day!=1)) && day!=1)
		{
//			finalIndex = 15 * (requiredWeek - 1) - 1;
			finalIndex=-1;
//			Toast.makeText(getApplicationContext(), "No Tests Available", Toast.LENGTH_LONG).show();
//			Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//			startActivity(intent);
			
		}
		if (requiredWeek > numberOfTests)
			finalIndex = (15*numberOfTests)-1;

		// Toast.makeText(this, "Final Index is :" + finalIndex + "skip : "+
		// skipIndex,

		 limit = (finalIndex+1)/15;

		
		TextView choose = (TextView)findViewById(R.id.total);
		choose.setText("Total tests available : " + limit);
//		choice = Integer.parseInt(chooseTest.getText().toString());
//		if(choice>4)
//		{
//			Toast.makeText(this, "Please enter Test number less than equal to 4", Toast.LENGTH_LONG).show();
//		}
		
	}
	
	public void submitChoice(View view)
	{
				
		//choice = Integer.parseInt(chooseTest.getText().toString());
//		String choice1;
//		choice1 = chooseTest.getText().toString();
		isSubmitted = true;
		
		if(chooseTest.getText().toString().matches(""))
		{
			Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show();
			return;
		}
		
		choice = Integer.parseInt(chooseTest.getText().toString());
		
		if(choice<=limit && limit!=0)
		{
		Intent intent = new Intent(this,PreviousTestsActivity.class);
		startActivity(intent);
		}
		else if(choice>limit)
		{
		Toast.makeText(this, "Please enter Test number less than equal to " + limit, Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(getApplicationContext(), "No Tests Available", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(intent);
		}
	}

	
	public void startFromBeginning(View view)
	{
		isSubmitted=false;
		Intent intent = new Intent(this,PreviousTestsActivity.class);
		startActivity(intent);
	}
	
	
	
}
