package com.ALS.als_testoftheday1;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PreviousTestsActivity extends ActionBarActivity {
	RadioGroup answerGroup;
	RadioButton option1, option2, option3, option4;
	TextView difficultyLevel;
	TextView question;
	boolean isClickedOnNextPaper = false;
	String strResult;
	String questiondAndOptions[];
	static int currentIndex = 0;
	RadioButton tempRadioButton;
	int tempIndex;
	float countEasy = 0, countMedium = 0, countDifficult = 0;
	float totalCorrectCount = 0;
	int count=0,countWrong=0;
	int selectedId;
	Button submitButton;
	static int testNumber = 1;
	boolean isClickedOnSubmit = false;
	boolean isClickedOnNext = false;
	boolean isAnswerCorrect=false;
	boolean answerCalledThroughSubmit=false;
	String userChoice = "";
	TextView tvTestNumber;
	Button buttonShowExplanation;
	Button answerButton;
	String explanation;
	static int finalIndex;
	static int skipIndex = 0;
	static String[] rightQuestions = new String[15];
	static String[] wrongQuestions = new String[15];
	static int rightIndex = 0;
	static int wrongIndex = 0;

	int numberOfTests=4;
	
	CheckBox dontShowAgain;
	
	boolean doNotAsk = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_previous_tests);

		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		// Toast.makeText(getApplicationContext(), "current : " + currentIndex,
		// Toast.LENGTH_LONG).show();
		skipIndex = 0;
		currentIndex = 0;
		testNumber=1;
		wrongIndex = 0;
		rightIndex = 0;

		for(int i=0; i<15; i++)
		{
			rightQuestions[i]=wrongQuestions[i]=null;
		}
		
		
//		if(ChooseTestNumber.isStarted==true)
//		{
//			currentIndex=0;
//		}
//		else 
		
		if(ChooseTestNumber.isSubmitted==true)
		{
			currentIndex = ((ChooseTestNumber.choice)-1)*15;
			skipIndex=currentIndex;
			testNumber=ChooseTestNumber.choice;
		}
		
//		Toast.makeText(this, "is submitted = " + ChooseTestNumber.isSubmitted, Toast.LENGTH_LONG).show();

		
		submitButton = (Button) findViewById(R.id.btnSubmit);
		answerGroup = (RadioGroup) findViewById(R.id.optionsGroup);
		option1 = (RadioButton) findViewById(R.id.radio1);
		option2 = (RadioButton) findViewById(R.id.radio2);
		option3 = (RadioButton) findViewById(R.id.radio3);
		option4 = (RadioButton) findViewById(R.id.radio4);
		question = (TextView) findViewById(R.id.textViewQuestion);
		difficultyLevel = (TextView) findViewById(R.id.difficultyLevel);
		
		answerButton = (Button)findViewById(R.id.answerButton);
		
		// buttonShowExplanation = (Button)findViewById(R.id.buttonExplanation);

		tvTestNumber = (TextView) findViewById(R.id.testNo);

		// questiondAndOptions = Questions.questionslist;

		getQuestionAndIndex();
		showNextQuestion();

//
		AlertDialog dialog;
		dialog = new AlertDialog.Builder(this).create();

		dialog = new AlertDialog.Builder(this).create();
		dialog.setTitle("Instructions");
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setMessage("You will get +2 for every correct answer, and -0.66 for every incorrect answer."
				+ "You can also skip questions if you wish, no marks will be deducted.");
		dialog.setCancelable(false);

		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
					}
				});

		dialog.show();
//		
	}

	void showNextQuestion() {
		isClickedOnSubmit = false;
		isClickedOnNext = false;
		answerGroup.clearCheck();
		answerButton.setEnabled(false);
		
		if (PreviousTestsWrongQuestions.isReviewed == true && PreviousTestsWrongQuestions.allTestOver==false
				) {
			// Toast.makeText(getApplicationContext(), "temp current : " +
			// PreviousTestsWrongQuestions.tempCurrentIndex,
			// Toast.LENGTH_LONG).show();
			currentIndex = PreviousTestsWrongQuestions.tempCurrentIndex;
			testNumber = PreviousTestsWrongQuestions.tempTestNumber+1;
			wrongIndex = 0;
			rightIndex = 0;
			skipIndex = 15;
			PreviousTestsWrongQuestions.isReviewed = false;

			if (currentIndex - 1 == finalIndex) {
				Toast.makeText(this, "All Test  Over", Toast.LENGTH_LONG)
						.show();
				Intent intent;
				intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();

			}

			// Toast.makeText(getApplicationContext(), "wrong " + wrongIndex +
			// "current : " + currentIndex, Toast.LENGTH_LONG).show();
		}
		if (PreviousTestsRightQuestions.isReviewed == true && PreviousTestsRightQuestions.allTestOver==false) {
			// Toast.makeText(getApplicationContext(), "temp current : " +
			// PreviousTestsWrongQuestions.tempCurrentIndex,
			// Toast.LENGTH_LONG).show();
			currentIndex = PreviousTestsRightQuestions.tempCurrentIndex;
			testNumber = PreviousTestsRightQuestions.tempTestNumber+1;
			wrongIndex = 0;
			rightIndex = 0;
			skipIndex = 15;
			PreviousTestsRightQuestions.isReviewed = false;

			if (currentIndex - 1 == finalIndex) {
				Toast.makeText(this, "All Test  Over", Toast.LENGTH_LONG)
						.show();
				Intent intent;
				intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();

			}
		}
		
		if(isClickedOnNextPaper==true)
		{
			skipIndex=currentIndex;
			isClickedOnNextPaper=false;
		}
		
		tvTestNumber.setText("Test : " + testNumber);

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

		// if(strTok.countTokens()==1)
		// {
		// buttonShowExplanation.setVisibility(Button.VISIBLE);
		// explanation = strTok.nextToken();
		// }
		// else
		// buttonShowExplanation.setVisibility(Button.GONE);
		//
		tempIndex = currentIndex % 15;

		if (tempIndex >= 0 && tempIndex <= 4) {
			difficultyLevel.setText("Easy" + "(" + count + "/" + 5 + ")");
			if (tempIndex == 0)
			{
				count = 0;
				countWrong=0;
			}
		} else if (tempIndex >= 5 && tempIndex <= 9) {
			if (tempIndex == 5) {
				count = 0;
				countWrong=0;
			}
			difficultyLevel.setText("Medium" + "(" + count + "/" + 5 + ")");
		} else if (tempIndex >= 10 && tempIndex <= 14) {
			if (tempIndex == 10)
			{
				count = 0;
				countWrong=0;}
			difficultyLevel.setText("Hard" + "(" + count + "/" + 5 + ")");

		}
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

			if (userChoice.equals(strResult)) {
				isAnswerCorrect=true;
				rightQuestions[rightIndex] = questiondAndOptions[currentIndex - 1];
				rightIndex++;
				tempRadioButton.setBackgroundColor(Color.parseColor("#66FF66"));
				if (isClickedOnSubmit == false) {

					if (tempIndex >= 0 && tempIndex <= 4) {
						countEasy+=2;
					} else if (tempIndex >= 5 && tempIndex <= 9) {
						countMedium+=2;
					} else if (tempIndex >= 10 && tempIndex <= 14) {
						countDifficult+=2;
					}
					count++;
					totalCorrectCount+=2;
					// Toast.makeText(QuestionOfTheDayActivity.this,
					// "Your Answer is Correct", Toast.LENGTH_SHORT).show();

					// /showDialog("Your Answer is Correct");
				}
			} else {
				isAnswerCorrect=false;
				tempRadioButton = (RadioButton) findViewById(selectedId);
				wrongQuestions[wrongIndex] = questiondAndOptions[currentIndex - 1];
				wrongIndex++;
				tempRadioButton.setBackgroundColor(Color.parseColor("#FF3300"));
				// submitButton.setText("Try Again");
				
				if (tempIndex >= 0 && tempIndex <= 4) {
					countEasy-=0.66;
				} else if (tempIndex >= 5 && tempIndex <= 9) {
					countMedium-=0.66;
				} else if (tempIndex >= 10 && tempIndex <= 14) {
					countDifficult-=0.66;
				}
				countWrong++;
				totalCorrectCount-=0.66;

				
				answerGroup.clearCheck();
				// showDialog("Your Answer is Wrong ,the correct answer is " +
				// strResult);
				// Toast.makeText(QuestionOfTheDayActivity.this,
				// "Your Answer is Wrong ,the correct answer is " + strResult,
				// Toast.LENGTH_SHORT).show();
			}
			
			isClickedOnSubmit = true;
			isClickedOnNext = false;
			answerButton.setEnabled(true);
			if(isAnswerCorrect==true)
			showDialog();
			else
			{
				answerCalledThroughSubmit=true;
//				showTheAnswer(view);
			}
			submitButton.setEnabled(false);
			// currentIndex++;

		}
	}

	void showDialog() {
		AlertDialog dialog;

		String levelCompletedMessage = "";
		String message = "";
		if ((tempIndex == 4 || tempIndex == 9 || tempIndex == 14)
				&& (userChoice.equals(strResult) || isClickedOnNext == true)) {
			levelCompletedMessage = "\nYou got " + count
					+ " questions right in this section and " + countWrong + " questions wrong";
			message = message + levelCompletedMessage;
		} else
			return;

		dialog = new AlertDialog.Builder(this).create();
		dialog.setCancelable(false);

		dialog.setTitle("Result");
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setMessage(message);

		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// tempRadioButton = (RadioButton)
						// findViewById(selectedId);
						// tempRadioButton.setBackgroundColor(Color
						// /.parseColor("#D6EBFF"));
						if (tempIndex == 14) {
							showTestEndDialog();
							count = 0;
							countEasy = countMedium = countDifficult = totalCorrectCount=countWrong= 0;
							dialog.cancel();
						} else {
							showNextQuestion();
						}
					}
				});

		dialog.show();
	}

	public void nextQuestion(View view) {

		isClickedOnNext = true;
		PreviousTestsWrongQuestions.isReviewed = false;
		PreviousTestsRightQuestions.isReviewed = false;
		if (isClickedOnSubmit == false) {
			wrongQuestions[wrongIndex] = questiondAndOptions[currentIndex - 1];
			wrongIndex++;
		}
		
		if (doNotAsk == false && isClickedOnSubmit == false) {
			AlertDialog dialog;
			// following code will be in your activity.java file
			// final CharSequence[] items = {"Do not ask again"};
			// // arraylist to keep the selected items
			// final ArrayList seletedItems=new ArrayList();
			

			AlertDialog.Builder builder = new AlertDialog.Builder(PreviousTestsActivity.this);
			LayoutInflater adbInflater = LayoutInflater.from(PreviousTestsActivity.this);
			View checkLayout = adbInflater.inflate(R.layout.checkbox, null);
			builder.setTitle("Confirm Skippping Question");
			builder.setCancelable(false);
			builder.setView(checkLayout);
			builder.setMessage("Are You sure you want to skip this Question?"
					+ "You will not be able to return to this question.");
			dontShowAgain = (CheckBox)checkLayout.findViewById(R.id.skip);
//			builder.setMultiChoiceItems(items, null,
//					new DialogInterface.OnMultiChoiceClickListener() {
//						// indexSelected contains the index of item (of
//						// which checkbox checked)
//						@Override
//						public void onClick(DialogInterface dialog,
//								int indexSelected, boolean isChecked) {
//							if (isChecked) {
//								doNotAsk = true;
//								seletedItems.add(indexSelected);
//							} else if (seletedItems.contains(indexSelected)) {
//								// Else, if the item is already in the
//								// array, remove it
//								// write your code when user Uchecked the
//								// checkbox
//								seletedItems.remove(Integer
//										.valueOf(indexSelected));
//							}
//						}
//					})
//					// Set the action buttons
			
					builder.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									if(dontShowAgain.isChecked())
									{
										doNotAsk=true;
									}
									// Your code when user clicked on OK
									// You can write the code to save the
									// selected item here
									if (tempIndex == 4 || tempIndex == 9
											|| tempIndex == 14)
										showDialog();
									else
										showNextQuestion();

								}
							});
					builder.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									// Your code when user clicked on Cancel
									dialog.dismiss();
								}
							});

			dialog = builder.create();// AlertDialog dialog; create like
										// this outside onClick
			dialog.show();
		}
	

	else{
	submitButton.setText("Submit");
	 if (tempIndex == 4 || tempIndex == 9 || tempIndex == 14)
	 showDialog();
	 else
	 showNextQuestion();
	}

	//isClickedOnNext = true;


	}

	public void showTheAnswer(View view) {

		submitButton.setEnabled(false);
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
		
		if(answerCalledThroughSubmit==true)
		{
			answerCalledThroughSubmit=false;
			showDialog();
		}
	}

	void showTestEndDialog() {
		AlertDialog dialog;
		dialog = new AlertDialog.Builder(this).create();
		dialog.setTitle("Final Result");
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setMessage("Final Score is : " + totalCorrectCount + "\nEasy = "
				+ countEasy + "\nMedium = " + countMedium + "\nDifficult = "
				+ countDifficult);

		dialog.setCancelable(false);

		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Next Paper",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						isClickedOnNextPaper = true;
						resetAllData();
						dialog.cancel();
					}
				});

		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Home",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						Intent intentHomeActivity = new Intent(
								getApplicationContext(), MainActivity.class);
						startActivity(intentHomeActivity);
						finish();
					}
				});
		dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Review Paper",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						Intent intentHomeActivity = new Intent(
								getApplicationContext(),
								PreviousTestsReviewPaper.class);
						startActivity(intentHomeActivity);
					}
				});
		dialog.show();
	}

	void getQuestionAndIndex() {
		GregorianCalendar gc = new GregorianCalendar();
		int currentWeek = gc.get(Calendar.WEEK_OF_YEAR);
		int baseWeek = -4;
 
		// int day = gc.get(Calendar.DAY_OF_WEEK);

		// get the Subject
		if (MainActivity.selectedSubject.equals("MixedBag")) {
			questiondAndOptions = MixedBag.questionslist;
		} else if (MainActivity.selectedSubject.equals("Science")) {
			questiondAndOptions = Science.questionslist;
		} else if (MainActivity.selectedSubject.equals("Geography")) {
			questiondAndOptions = Geography.questionslist;
		} else if (MainActivity.selectedSubject.equals("History")) {
			questiondAndOptions = History.questionslist;
			// Toast.makeText(this, "Select Subject is History",
			// Toast.LENGTH_LONG).show();
		} else if (MainActivity.selectedSubject.equals("CurrentAffairs")) {
			questiondAndOptions = CurrentAffairs1.questionslist;
			// Toast.makeText(this,
			// "Select Subject is Current 1 and day is Thurs",
			// Toast.LENGTH_LONG).show();
		} else if (MainActivity.selectedSubject.equals("Polity")) {
			questiondAndOptions = Polity.questionslist;
		} else if (MainActivity.selectedSubject.equals("Economics")) {
			questiondAndOptions = Economics.questionslist;
		}

		// get the required index

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

		if(requiredWeek==1 && (MainActivity.selectedDay>day || (MainActivity.selectedDay==1 && day!=1))  && day!=1)
		{
//			finalIndex = 15 * (requiredWeek - 1) - 1;
			Toast.makeText(getApplicationContext(), "No Tests Available", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(intent);
			
		}
		if (requiredWeek > numberOfTests)
			finalIndex = (15*numberOfTests)-1;

		// Toast.makeText(this, "Final Index is :" + finalIndex + "skip : "+
		// skipIndex,
		// Toast.LENGTH_LONG).show();

	}

	public void resetAllData() {
		testNumber++;
		countWrong=0;
		count = 0;
		countEasy = 0;
		countMedium = 0;
		countDifficult = 0;
		totalCorrectCount = 0;
		wrongIndex = rightIndex = 0;
		// rightQuestions=null;
		// wrongQuestions=null;

		isClickedOnSubmit = false;
		isClickedOnNext = false;
		// Toast.makeText(this,
		// "resetAllData called currentIndex  is "+currentIndex ,
		// Toast.LENGTH_LONG).show();

		if (currentIndex - 1 == finalIndex) {
			currentIndex=0;
			testNumber=1;
			Toast.makeText(this, "All Test  Over", Toast.LENGTH_LONG).show();
			Intent intent;
			intent = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(intent);
			finish();

		} else
			showNextQuestion();

		// Toast.makeText(this,
		// "resetAllData called currentIndex  is "+currentIndex ,
		// Toast.LENGTH_LONG).show();

	}

	// public void showExplanation(View view)
	// {
	// final AlertDialog explanationDialog;
	// explanationDialog = new AlertDialog.Builder(this).create();
	// explanationDialog.setTitle("Result");
	// explanationDialog.setIcon(android.R.drawable.ic_dialog_info);
	// explanationDialog.setMessage(explanation);
	//
	// explanationDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
	// new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int which) {
	// explanationDialog.dismiss();
	// }
	// });
	//
	// explanationDialog.show();
	// }

	public void skipTest(View view) {

		AlertDialog dialog;
		dialog = new AlertDialog.Builder(this).create();

		dialog = new AlertDialog.Builder(this).create();
		dialog.setTitle("Confirm submission");
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setMessage("Are you sure you want to skip this Test? ALL PROGRESS FORM THE CURRENT TEST WILL "
				+ "BE LOST.");
		dialog.setCancelable(false);

		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						skipIndex = skipIndex + 15;
						currentIndex = skipIndex;
//						// Toast.makeText(this,
//						// "resetAllData called currentIndex  is "+currentIndex ,
//						// Toast.LENGTH_LONG).show();
						resetAllData();	
						dialog.cancel();
					}
				});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		dialog.show();
//		skipIndex = skipIndex + 15;
//		currentIndex = skipIndex;
//		// Toast.makeText(this,
//		// "resetAllData called currentIndex  is "+currentIndex ,
//		// Toast.LENGTH_LONG).show();
//		resetAllData();
	}

	// public static void goToResetAllData()
	// {
	// resetAllData();
	// }

}
