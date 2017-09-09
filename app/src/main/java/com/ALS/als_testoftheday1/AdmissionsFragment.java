package com.ALS.als_testoftheday1;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdmissionsFragment extends Fragment {

public AdmissionsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.admissions, container, false);
         
        TextView admissions = (TextView)rootView.findViewById(R.id.textViewAdmissions);
        
        admissions.setText("Admission at ALS is a smooth process. The moment you step into an ALS Centre it quickly becomes apparent that you have.\n\n"
        		+ "INTERACTION AND APTITUDE PERUSAL: You will be invited to attend an interactive session where you will be able to discuss your aspirations, choice of course (s), entry criterion and future prospects."
        		+ "\n\nCOUNSELLING: A team of professional counselors will help acquaint yourself with Civil Services Examination, its overall structure and requirements. You can also talk about your concerns, if any, in a friendly and confidential setting. "
        		+ "\n\nFACILITY TOUR: This is the best way to make a decision on both your choice of IAS training course(s) and institute. Get a glimpse of everything at ALS, including classroom infrastructure, a sample of study material and supplements provided, as well as other facilities offered. "
        		+ "\n\nCOURSES & LEARNING: A course co-ordinator helps you unravel planning, preparation and the commitment required for Civil Services Examination. You also get guidance for choosing the “optional subject” that interests & suits you the best. "
        		+ "\n\nSCHEDULE PREPARATION: The administrative cell at your chosen ALS centre will provide you with relevant and detailed course planners while outlining your batch timings, location of classrooms and resource centre."
        		+ "\n\nFEE STRUCTURE: The administrative cell will clearly lay out the fee structure for your chosen course(s). There are concessions available to students under certain circumstances if applicable."
        		+ "\n\nADMISSION FORMS & REGISTRATION: The registration desk may ask you to furnish any formal documents towards your admission. You will be handed out and assisted in filing requisite application forms entitling you to take the chosen course(s) at ALS."
        		+ "\n\nWELCOME KIT HANDOVER: A classroom programme starter pack comprising required books, admission documents and essential ALS stationery will be handed over to you in a sturdy, well-designed bag with ALS branding."  
);
        
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        
        return rootView;
    }
}
