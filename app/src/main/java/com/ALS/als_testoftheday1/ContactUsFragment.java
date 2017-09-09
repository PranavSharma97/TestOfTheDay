package com.ALS.als_testoftheday1;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactUsFragment extends Fragment {

public ContactUsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.contact_us, container, false);
        
TextView contactUs = (TextView)rootView.findViewById(R.id.textViewContactUs);

LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
params.setMargins(5, 10, 10, 10);
contactUs.setPadding(10, 10, 10, 10);
contactUs.setLayoutParams(params);
        
        contactUs.setText("Website: www.iasals.com     Email: info@iasalscom\n\n"
        		+ "SHALIMAR PLACE:\n\n"
        		+ "Contact Person: Nilabh Singh\n"
        		+ "Address: A-Block, Plot II, \n"
        		+ "Shalimar District Centre (Opp. Rohini Jail), \n"
        		+ "Outer Ring Road,\n"
        		+ "Delhi 110088.\n"
        		+ "Telephone: +91 9911802227; 011 27495600\n"
        		+ "Email: info.shalimarplace@alsias.com\n\n\n"
        		+ "MUKHERJEE NAGAR\n\n"
        		+ "Contact Person: R K Sharma\n"
        		+ "Address: B-19, ALS House, Commercial Complex,\n"
        		+ "Dr. Mukherjee Nagar, Delhi-110009\n"
        		+ "Telephone: +91 9911801117; 011 27651110\n"
        		+ "Email: info.mukherjeenagar@alsias.com\n\n\n"
        		+ "KAROL BAGH\n\n"
        		+ "Contact Person: Prabhash Kumar\n"
        		+ "Address: 20, Pusa Road, \n"
        		+ "Metro Pillar No. 97, \n"
        		+ "New Delhi 110005\n"
        		+ "Telephone: +91 9911803337; 011 28751379\n"
        		+ "Email: info.karolbagh@alsias.com\n\n\n"
        		+ "R.K. PURAM\n\n"
        		+ "Contact Person: Mohd. Ishaq\n"
        		+ "Address: HDFC Bank Campus,\n"
        		+ "8 Institutional Area\n"
        		+ "Sector 4, RK Puram, \n"
        		+ "New Delhi 110022\n"
        		+ "Telephone: +91 9911804447, 011 26713811\n"
        		+ "Email: info.rkpuram@alsias.com"
);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        
        return rootView;
    }
}
