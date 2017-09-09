package com.ALS.als_testoftheday1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

public class PreviousTestsReviewPaper extends ActionBarActivity {

	int flag=0,flag1=0;;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_previoustestsreview_paper);
//		Toast.makeText(getApplicationContext(), "length of wrong is " + TestOfTheDay.wrongIndex, Toast.LENGTH_LONG).show();
	}

	public void showRightQuestions(View view)
	{
		for(int i=0; i<15; i++)
		{
			if(PreviousTestsActivity.rightQuestions[i]==null)
				flag=1;
			else 
			{
				flag=0;
				break;}
		}
		if(flag==1)
			Toast.makeText(getApplicationContext(), "No right Questions", Toast.LENGTH_LONG).show();
		else{
		Intent intent;
		intent = new Intent(getApplicationContext(),PreviousTestsRightQuestions.class);
		startActivity(intent);
		}
		}
	
	public void showWrongQuestions(View view)
	{
		for(int i=0; i<15; i++)
		{
			if(PreviousTestsActivity.wrongQuestions[i]==null)
				flag1=1;
			else 
			{
				flag1=0;
				break;
			}
				
		}
		if(flag1==1)
			Toast.makeText(getApplicationContext(), "No wrong Questions", Toast.LENGTH_LONG).show();
		else{
		Intent intent;
		intent = new Intent(getApplicationContext(),PreviousTestsWrongQuestions.class);
		startActivity(intent);
	}
	}
	public void backHome(View view)
	{
		PreviousTestsRightQuestions.isReviewed=PreviousTestsWrongQuestions.isReviewed=false;
		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(intent);
	}
}
