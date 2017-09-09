package com.ALS.als_testoftheday1;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RightQuestions extends ActionBarActivity {
	RadioGroup answerGroup;
	RadioButton option1, option2, option3, option4;
	TextView difficultyLevel;
	TextView question,rightTv;
	String strResult;
	String explanation;
	String questiondAndOptions[];
	int currentIndex = 0;
	RadioButton tempRadioButton;
	int tempIndex;
	int count = 0, countEasy = 0, countMedium = 0, countDifficult = 0;
	int totalCorrectCount = 0;
	int selectedId;
	Button submitButton;
	boolean isClickedOnSubmit=false;
	boolean isClickedOnNext=false;
	String userChoice="";
	Button buttonShowExplanation;
	String[] rightQuestions;
	String[] wrongQuestions;
	int rightIndex,wrongIndex;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_right_questions);
		
		submitButton = (Button) findViewById(R.id.btnSubmit);
		answerGroup = (RadioGroup) findViewById(R.id.optionsGroup);
		option1 = (RadioButton) findViewById(R.id.radio1);
		option2 = (RadioButton) findViewById(R.id.radio2);
		option3 = (RadioButton) findViewById(R.id.radio3);
		option4 = (RadioButton) findViewById(R.id.radio4);
		question = (TextView) findViewById(R.id.textViewQuestion);
		difficultyLevel = (TextView) findViewById(R.id.difficultyLevel);
		buttonShowExplanation = (Button)findViewById(R.id.buttonExplanation);
		rightTv = (TextView)findViewById(R.id.rightQuestions);
		rightTv.setText("CORRECT QUESTIONS");
//		questiondAndOptions = Questions.questionslist;

		getQuestionAndIndex();
		showNextQuestion();

	}

	void showNextQuestion() {
		isClickedOnSubmit=false;
		isClickedOnNext = false;
		answerGroup.clearCheck();
//		submitButton.setEnabled(true);
//		buttonShowExplanation.setEnabled(false);
		StringTokenizer strTok = new StringTokenizer(
				questiondAndOptions[currentIndex], "*");
		


		// Reset the color of Options
		option1.setBackgroundColor(Color.parseColor("#D6EBFF"));
		option2.setBackgroundColor(Color.parseColor("#D6EBFF"));
		option3.setBackgroundColor(Color.parseColor("#D6EBFF"));
		option4.setBackgroundColor(Color.parseColor("#D6EBFF"));
		
		submitButton.setEnabled(true);
		submitButton.setText("Submit");

		String strQuestion = strTok.nextToken();
		String strOption1 = strTok.nextToken();
		String strOption2 = strTok.nextToken();
		String strOption3 = strTok.nextToken();
		String strOption4 = strTok.nextToken();

		strResult = strTok.nextToken();

		if(strTok.countTokens()==1)
		{
			buttonShowExplanation.setVisibility(Button.VISIBLE);
			explanation = strTok.nextToken();
		}
		else 
			buttonShowExplanation.setVisibility(Button.GONE);
		tempIndex = currentIndex % 15;

//		if (tempIndex >= 0 && tempIndex <= 4) {
//			difficultyLevel.setText("Easy" + "(" + count + "/" + 5 + ")");
//			if (tempIndex == 0)
//				count = 0;
//		} else if (tempIndex >= 5 && tempIndex <= 9) {
//			if (tempIndex == 5) {
//				count = 0;
//			}
//			difficultyLevel.setText("Medium" + "(" + count + "/" + 5 + ")");
//		} else if (tempIndex >= 10 && tempIndex <= 14) {
//			if (tempIndex == 10)
//				count = 0;
//			difficultyLevel.setText("Hard" + "(" + count + "/" + 5 + ")");

//		}
		question.setText("Q." + (tempIndex + 1) + ":" + strQuestion);
		option1.setText(strOption1);
		option2.setText(strOption2);
		option3.setText(strOption3);
		option4.setText(strOption4);
		
		currentIndex++;
		
	}

	public void submitTheOption(View view) {
			
		selectedId = answerGroup.getCheckedRadioButtonId();
		tempRadioButton = (RadioButton) findViewById(selectedId);

		if (selectedId == -1) {
			Toast.makeText(getApplicationContext(),
					"Please select any 1 Option", Toast.LENGTH_LONG).show();

		} else {

			 userChoice = tempRadioButton.getText().toString();

			if (userChoice.equals(strResult)){
//				rightQuestions[rightIndex] = questiondAndOptions[currentIndex];
//				rightIndex++;
//				submitButton.setEnabled(false);
//				buttonShowExplanation.setEnabled(true);
				tempRadioButton.setBackgroundColor(Color.parseColor("#66FF66"));
//				if (isClickedOnSubmit == false) {
//
//					if (tempIndex >= 0 && tempIndex <= 4) {
//						countEasy++;
//					} else if (tempIndex >= 5 && tempIndex <= 9) {
//						countMedium++;
//					} else if (tempIndex >= 10 && tempIndex <= 14) {
//						countDifficult++;
//					}
//				count++;
//				totalCorrectCount++;
				// Toast.makeText(QuestionOfTheDayActivity.this,
				// "Your Answer is Correct", Toast.LENGTH_SHORT).show();

				// /showDialog("Your Answer is Correct");
//				}
			} else {
				tempRadioButton = (RadioButton) findViewById(selectedId);
				tempRadioButton.setBackgroundColor(Color.parseColor("#FF3300"));
//				submitButton.setEnabled(false);
//				submitButton.setText("Try Again");
				answerGroup.clearCheck();
				// showDialog("Your Answer is Wrong ,the correct answer is " +
				// strResult);
				// Toast.makeText(QuestionOfTheDayActivity.this,
				// "Your Answer is Wrong ,the correct answer is " + strResult,
				// Toast.LENGTH_SHORT).show();
			}
			 isClickedOnSubmit = true;
			 isClickedOnNext = false;
			showDialog();
			//currentIndex++;
			
		}
	}

	void showDialog() {
		if(isClickedOnSubmit==true)
		{
			if(tempIndex==TestOfTheDay.rightIndex-1)
				showTestEndDialog();
			else 
				return;
		}
		else
		if(tempIndex==TestOfTheDay.rightIndex-1)
				showTestEndDialog();
		else
			showNextQuestion();

//		AlertDialog dialog;
//		String levelCompletedMessage = "";
//		String message = "";
//		if ((tempIndex == 4 || tempIndex == 9 || tempIndex == 14)  && (userChoice.equals(strResult) || isClickedOnNext == true || !userChoice.equals(strResult))) {
//			levelCompletedMessage = "\nYou got " + count
//					+ " questions right in this section";
//			message = message + levelCompletedMessage;
//		} else
//			return;
//
//		dialog = new AlertDialog.Builder(this).create();
//		dialog.setTitle("Result");
//		dialog.setIcon(android.R.drawable.ic_dialog_info);
//		dialog.setMessage(message);
//
//		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
//				new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int which) {
//						//tempRadioButton = (RadioButton) findViewById(selectedId);
//						//tempRadioButton.setBackgroundColor(Color
//								///.parseColor("#D6EBFF"));
//						if (tempIndex == 14) {
//							showTestEndDialog();
//							count = 0;
//							countEasy = countMedium = countDifficult = totalCorrectCount = 0;
//							dialog.cancel();
//						}
//						else
//						{
//							showNextQuestion();
//						}
//					}
//				});
//
//		dialog.show();
	}

	public void nextQuestion(View view) {
//		submitButton.setText("Submit");
//		if (tempIndex == 4 || tempIndex == 9 || tempIndex == 14) 
//			showDialog();
//		else
		isClickedOnSubmit=false;

		showDialog();		
		
		isClickedOnNext = true;

	}

	public void showTheAnswer(View view) {
		
		submitButton.setEnabled(false);
//		buttonShowExplanation.setEnabled(true);
		if (strResult.equals(option1.getText().toString())) {
			option1.setBackgroundColor(Color.parseColor("#66FF66"));
			option1.setChecked(true);
		} else if (strResult.equals(option2.getText().toString())) {
			option2.setBackgroundColor(Color.parseColor("#66FF66"));
			option2.setChecked(true);

		} else if (strResult.equals(option3.getText().toString())) {
			option3.setBackgroundColor(Color.parseColor("#66FF66"));
			option3.setChecked(true);

		} else if (strResult.equals(option4.getText().toString())) {
			option4.setBackgroundColor(Color.parseColor("#66FF66"));
			option4.setChecked(true);

		}
	}

	
	
	void showTestEndDialog() {
		AlertDialog dialog;
		dialog = new AlertDialog.Builder(this).create();
		dialog.setTitle("Review Completed");
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setMessage("You have Reviewed all the Right Questions");

		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Exit",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						Intent intentHomeActivity = new Intent(
								getApplicationContext(), MainActivity.class);
						startActivity(intentHomeActivity);
						finish();
					}
				});
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Review Wrong Questions",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						Intent intentHomeActivity = new Intent(
								getApplicationContext(), ReviewPaper.class);
						startActivity(intentHomeActivity);
					}
				});
//		dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK",
//				new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int which) {
//
//						Intent intentHomeActivity = new Intent(
//								getApplicationContext(), Home_Activity.class);
//						startActivity(intentHomeActivity);
//						finish();
//						dialog.cancel();
//					}
//				});

		dialog.show();
	}
	
	
	
	
	
	
	
	void getQuestionAndIndex()
	{
		questiondAndOptions = TestOfTheDay.rightQuestions;
	}
	
	public void showExplanation(View view)
	{
		final AlertDialog explanationDialog;
		explanationDialog = new AlertDialog.Builder(this).create();
		explanationDialog.setTitle("Result");
		explanationDialog.setIcon(android.R.drawable.ic_dialog_info);
		explanationDialog.setMessage(explanation);

		explanationDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						explanationDialog.dismiss();
					}
				});

		explanationDialog.show();
	}
	}


