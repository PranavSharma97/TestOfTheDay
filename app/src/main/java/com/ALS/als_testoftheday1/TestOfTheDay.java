package com.ALS.als_testoftheday1;

import java.util.Calendar;
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

public class TestOfTheDay extends ActionBarActivity {
	RadioGroup answerGroup;
	RadioButton option1, option2, option3, option4;
	TextView difficultyLevel, subjectName;
	TextView question;
	String strResult;
	String explanation;
	static String questiondAndOptions[];
	static int currentIndex = 0;
	RadioButton tempRadioButton;
	int tempIndex;
	float countEasy = 0, countMedium = 0, countDifficult = 0;
	float totalCorrectCount = 0;
	int count=0,countWrong=0;
	int selectedId;
	int baseWeek = -4;
	int counter;
	int numberOfTests=4;
	Button submitButton;
	Button answerButton;
	boolean isClickedOnSubmit = false;
	boolean isClickedOnNext = false;
	boolean answerCalledThroughSubmit=false;
	boolean isAnswerCorrect=false;
	String userChoice = "";
	
	Calendar gc = Calendar.getInstance();
	
	// now.get(Calendar.WEEK_OF_YEAR);
	int currentWeek;
	Button buttonShowExplanation;
	static String[] rightQuestions = new String[15];
	static String[] wrongQuestions = new String[15];
	// static ArrayList<String> rightQuestions = new ArrayList(15);
	// static ArrayList<String> wrongQuestions = new ArrayList(15);

	static int rightIndex = 0;
	static int wrongIndex = 0;

	boolean doNotAsk = false;

	CheckBox dontShowAgain;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.testoftheday);

		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		rightIndex = 0;
		wrongIndex = 0;

		submitButton = (Button) findViewById(R.id.btnSubmit);
		answerGroup = (RadioGroup) findViewById(R.id.optionsGroup);
		option1 = (RadioButton) findViewById(R.id.radio1);
		option2 = (RadioButton) findViewById(R.id.radio2);
		option3 = (RadioButton) findViewById(R.id.radio3);
		option4 = (RadioButton) findViewById(R.id.radio4);
		question = (TextView) findViewById(R.id.textViewQuestion);
		difficultyLevel = (TextView) findViewById(R.id.difficultyLevel);
		subjectName = (TextView) findViewById(R.id.subject);
		buttonShowExplanation = (Button) findViewById(R.id.buttonExplanation);
		
		answerButton = (Button)findViewById(R.id.answerButton);
		
		// questiondAndOptions = Questions.questionslist;
		// rightQuestions[0] = " ";
		// wrongQuestions =

		getQuestionAndIndex();
		showNextQuestion();

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

		
	}

	void showNextQuestion() {
		isClickedOnSubmit = false;
		isClickedOnNext = false;
		answerGroup.clearCheck();
		submitButton.setEnabled(true);
		answerButton.setEnabled(false);
		// buttonShowExplanation.setEnabled(false);
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

//		if (strTok.countTokens() == 1) {
//			buttonShowExplanation.setVisibility(Button.VISIBLE);
//			explanation = strTok.nextToken();
//		} else
			buttonShowExplanation.setVisibility(Button.GONE);
		
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
			if (tempIndex == 10){
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
				// rightQuestions.add(rightIndex,questiondAndOptions[currentIndex-1]);
				rightQuestions[rightIndex] = questiondAndOptions[currentIndex - 1];
				rightIndex++;
				submitButton.setEnabled(false);
				// buttonShowExplanation.setEnabled(true);
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
				tempRadioButton.setBackgroundColor(Color.parseColor("#FF3300"));
				submitButton.setEnabled(false);
				// wrongQuestions.add(wrongIndex,
				// questiondAndOptions[currentIndex-1]);
				
				if (tempIndex >= 0 && tempIndex <= 4) {
					countEasy-=0.66;
				} else if (tempIndex >= 5 && tempIndex <= 9) {
					countMedium-=0.66;
				} else if (tempIndex >= 10 && tempIndex <= 14) {
					countDifficult-=0.66;
				}
				countWrong++;
				totalCorrectCount-=0.66;
				
				wrongQuestions[wrongIndex] = questiondAndOptions[currentIndex - 1];
				wrongIndex++;
				// submitButton.setText("Try Again");
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
			// currentIndex++;

		}
	}

	void showDialog() {
		AlertDialog dialog;
		String levelCompletedMessage = "";
		String message = "";
		if ((tempIndex == 4 || tempIndex == 9 || tempIndex == 14)
				&& (userChoice.equals(strResult) || isClickedOnNext == true || !userChoice
						.equals(strResult))) {
			levelCompletedMessage = "\nYou got " + count
					+ " questions right in this section and " + countWrong + " questions wrong ";
			message = message + levelCompletedMessage;
		} else
			return;

		dialog = new AlertDialog.Builder(this).create();

		dialog = new AlertDialog.Builder(this).create();
		dialog.setTitle("Result");
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setMessage(message);
		dialog.setCancelable(false);

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
		dialog.show();
	}

	public void nextQuestion(View view) {
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
				

				AlertDialog.Builder builder = new AlertDialog.Builder(TestOfTheDay.this);
				LayoutInflater adbInflater = LayoutInflater.from(TestOfTheDay.this);
				View checkLayout = adbInflater.inflate(R.layout.checkbox, null);
				builder.setTitle("Confirm Skippping Question");
				builder.setCancelable(false);
				builder.setView(checkLayout);
				builder.setMessage("Are You sure you want to skip this Question?"
						+ "You will not be able to return to this question.");
				dontShowAgain = (CheckBox)checkLayout.findViewById(R.id.skip);
//				builder.setMultiChoiceItems(items, null,
//						new DialogInterface.OnMultiChoiceClickListener() {
//							// indexSelected contains the index of item (of
//							// which checkbox checked)
//							@Override
//							public void onClick(DialogInterface dialog,
//									int indexSelected, boolean isChecked) {
//								if (isChecked) {
//									doNotAsk = true;
//									seletedItems.add(indexSelected);
//								} else if (seletedItems.contains(indexSelected)) {
//									// Else, if the item is already in the
//									// array, remove it
//									// write your code when user Uchecked the
//									// checkbox
//									seletedItems.remove(Integer
//											.valueOf(indexSelected));
//								}
//							}
//						})
//						// Set the action buttons
				
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

		isClickedOnNext = true;
	}

	public void showTheAnswer(View view) {

		submitButton.setEnabled(false);
		// buttonShowExplanation.setEnabled(true);
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

		dialog.setCancelable(false);

		dialog.setTitle("Final Result");
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setMessage("Final Score is : " + totalCorrectCount + "\nEasy = "
				+ countEasy + "\nMedium = " + countMedium + "\nDifficult = "
				+ countDifficult);

		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Repeat Test",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						currentIndex = 0;
						wrongIndex = 0;
						rightIndex = 0;
						Intent intentHomeActivity = new Intent(
								getApplicationContext(), TestOfTheDay.class);
						startActivity(intentHomeActivity);
						finish();
					}
				});
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Review Test",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						Intent intentHomeActivity = new Intent(
								getApplicationContext(), ReviewPaper.class);
						startActivity(intentHomeActivity);
					}
				});
		dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						Intent intentHomeActivity = new Intent(
								getApplicationContext(), MainActivity.class);
						startActivity(intentHomeActivity);
						finish();
						dialog.cancel();
					}
				});

		dialog.show();
	}

	void getQuestionAndIndex() {

		currentWeek = gc.get(Calendar.WEEK_OF_YEAR);
		// int baseWeek=28;

		int day = gc.get(Calendar.DAY_OF_WEEK);
//		Toast.makeText(this, "Current week : " + currentWeek, Toast.LENGTH_LONG).show();

		
		// get the Subject
		if (day == 1) {
			questiondAndOptions = History.questionslist;
			subjectName.setText("HISTORY");

//			 Toast.makeText(this,"Current week : " + currentWeek + " bw " + baseWeek,
//			 Toast.LENGTH_LONG).show();

		} else if (day == 2) {
			questiondAndOptions = Polity.questionslist;
			subjectName.setText("POLITY");

		} else if (day == 3) {
			questiondAndOptions = Economics.questionslist;
			subjectName.setText("ECONOMICS");

		} else if (day == 4) {
			questiondAndOptions = Science.questionslist;
			subjectName.setText("SCIENCE");

			// Toast.makeText(this,"Select Subject is History and day is WED",
			// Toast.LENGTH_LONG).show();
		} else if (day == 5) {
			questiondAndOptions = CurrentAffairs1.questionslist;
			subjectName.setText("CURRENT AFFAIRS");

			// Toast.makeText(this,"Select Subject is Current 1 and day is Thurs",
			// Toast.LENGTH_LONG).show();
		} else if (day == 6) {
			questiondAndOptions = MixedBag.questionslist;
			subjectName.setText("MIXED BAG");

		} else if (day == 7) {
			questiondAndOptions = Geography.questionslist;
			subjectName.setText("GEOGRAPHY");

		}

		// get the required index

		int requiredWeek = currentWeek - baseWeek;
//		Toast.makeText(this, "Required week : " + requiredWeek, Toast.LENGTH_LONG).show();

		// int copyRequiredWeek = requiredWeek;
		// counter = requiredWeek-4;
		if (requiredWeek == numberOfTests+1) {
			counter = requiredWeek - numberOfTests;
			// baseWeek=currentWeek-1;
			// requiredWeek=1;
		}
		// Toast.makeText(getApplicationContext(), " baseweek : " + baseWeek,
		// Toast.LENGTH_LONG).show();
		if (requiredWeek == ((numberOfTests+1) * counter) - (counter - 1)) {
//			Toast.makeText(this, "in 2nd if", Toast.LENGTH_LONG).show();
			requiredWeek = 1;
			baseWeek = currentWeek - 1;
			counter++;
		} else {
//			Toast.makeText(this, "in 2nd else", Toast.LENGTH_LONG).show();
			requiredWeek = requiredWeek % numberOfTests;
			if (requiredWeek == 0)
				requiredWeek = numberOfTests;
		}
//		 Toast.makeText(getApplicationContext(), " required : " + requiredWeek,
//				 Toast.LENGTH_LONG).show();
		// requiredWeek = currentWeek - baseWeek;
		currentIndex = 15 * (requiredWeek - 1);

		// Toast.makeText(this,"Current Index is :"+ currentIndex,
		// Toast.LENGTH_LONG).show();

	}

	public void showExplanation(View view) {
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
