package com.ALS.als_testoftheday1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class ReviewPaper extends ActionBarActivity {

	int flag=0,flag1=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_review_paper);
//		Toast.makeText(getApplicationContext(), "length of wrong and right is " + TestOfTheDay.wrongIndex + " " + TestOfTheDay.rightIndex, Toast.LENGTH_LONG).show();
	}

	public void showRightQuestions(View view)
	{
		for(int i=0; i<15; i++)
		{
			if(TestOfTheDay.rightQuestions[i]==null)
				flag=1;
			else 
				{flag=0;
				break;
				}
		}
		if(flag==1)
			Toast.makeText(getApplicationContext(), "No right Questions", Toast.LENGTH_LONG).show();
		else{
		Intent intent;
		intent = new Intent(getApplicationContext(),RightQuestions.class);
		startActivity(intent);
		}
		}
	
	public void showWrongQuestions(View view)
	{
		for(int i=0; i<15; i++)
		{
			if(TestOfTheDay.wrongQuestions[i]==null)
				flag1=1;
			else 
			{flag1=0;
			break;
			}
		}
		if(flag1==1)
			Toast.makeText(getApplicationContext(), "No wrong Questions", Toast.LENGTH_LONG).show();
		else{
		Intent intent;
		intent = new Intent(getApplicationContext(),WrongQuestions.class);
		startActivity(intent);
	}
	}
	
	public void backHome(View view)
	{
		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(intent);
	}
}
