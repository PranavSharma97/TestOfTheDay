package com.ALS.als_testoftheday1;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class OurTeachersFragment extends Fragment {

public OurTeachersFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_teachers, container, false);
         
        String teachers[] = { "Shashank Atom", "Jojo Mathews",
				"Manoj Kumar Singh", "K M Pathi", "Manish Gautam",
				"Sharad Tripathi", "Manjula Khatri","Dr.Sanjay Pandey","Arbind Singh","Sachin Arora",
						 "R C Sinha","Arunesh Singh","Ajay Srivastava","Shweta Singh","Ranjana Subberwal","K Krishna Reddy" };

		ListViewAdapter teachersAdapter = new ListViewAdapter(
				rootView.getContext(), teachers);
		ListView listAdapter = (ListView)rootView.findViewById(R.id.listOfTeachers);
		listAdapter.setAdapter(teachersAdapter);
	
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        
        return rootView;
    }
}
