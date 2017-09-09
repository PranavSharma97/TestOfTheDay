package com.ALS.als_testoftheday1;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HelpFragment extends Fragment{
	
	public HelpFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_help, container, false);
         
        String help = "This App is designed for IAS aspirants. Our Aim is to prepare you thoroughly for the "
				+ "General Studies exam,and help you Score the highest marks.\n"
				+ " You can test your General studies "
				+ "skills here, testing one subject each day of the week. Each day, you will get a test consisiting of "
				+ "15 Questions : 5 Easy,5 Medium,and 5 Hard."
				+ "The Subjects alloted for each day are : \n\n"
				+ "Monday : Political Science\n"
				+ "Tuesday : Economics\n"
				+ "Wednesday : Science\n"
				+ "Thursday : Current Affairs\n"
				+ "Friday : Mixed Bag\n"
				+ "Saturday : Geography\n"
				+ "Sunday : History"
				+ "\n\nALARM SETTINGS : \n\n"
				+ "You can go to 'Remind me' by clicking on the 3 dots on the right hand corner of the HOME page OR "
				+ "can click on the left touch button of your phone."
				+ "From there You can schedule the time you want to take the TEST each day,"
				+ "as per your convenience. At that time you will recieve a notification to remind to take the test "
				+ "of that day"
				+ "\n\n Missed the Test of some day?\n\n"
				+ "No problem ! Just go to Previous Test papers, and find all the Previous "
				+ "Test Papers there.";
		
		
		TextView helpTextView = (TextView)rootView.findViewById(R.id.textViewHelp);
		helpTextView.setText(help);
        
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        
        return rootView;
    }
	
}

