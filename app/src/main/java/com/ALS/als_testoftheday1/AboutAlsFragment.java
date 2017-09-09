package com.ALS.als_testoftheday1;

//import info.androidhive.slidingmenu.R;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutAlsFragment extends Fragment {

public AboutAlsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.about_als, container, false);
         
        TextView about = (TextView)rootView.findViewById(R.id.textViewAbout);
        
        about.setText("ALTERNATIVE LEARNING SYSTEMS\n\n"
        		+ "Founded in the pristine character of flawless teaching methodology and fine mentoring that has driven students to attain time-sensitive goals and smoothly adapt into roles that shape India.\n\n"
        		+ "At the core of the institute lies a vibrant and truly diverse community. Underpinning its offering is a network of gifted communicators – expert and enthusiastic teachers, who are dedicated to making their courses both academically rigorous and immensely enjoyable. Fellow classmates equally eager to learn and expand their horizons are graduate and post-graduate students, and working professionals from diverse backgrounds, bringing other 'life experience' to the classroom. \n\n"
        		+ "ALS has been loftily nurturing their pupils and seeing them crack the Civil Services Examination with poise. There is a rationale why more than 20 percent of seats are secured by ALS alumni. The institute offers a rich and rewarding mix of courses, teaching methodology and rigorous academic exposure in an inspirational environment. It all adds up to a winning combination of innovation and tradition: the best of both worlds.\n\n"
        		+ "CAPTION – Mentors at ALS take the arduous Civil Services Examination seriously, nevertheless infectiously breed the belief in their students that it can be vanquished with flair.\n"
        		+ "\nThe plenary alternative learning technology that is employed in the classrooms at ALS over the past 20 years is based on the concept of lateral thinking and is formulated to promote creativity, critical thinking and effortless learning. The classroom sessions are supplemented with learning aids and exhaustive study material that result in timely completion of courses with panoptic coverage and an unwavering foundation.\n\n"
        		+ "Students are welcomed into the committed community and encouraged to share their experiences, narratives, skills and backgrounds to invoke a sense of cross-disciplinary study supported with structured educational programmes to model their respective career choices and let them be:  the true citizens of the world.\n\n"
        		+ "\n\nCAPTION - Champion the training modules at ALS under pioneers of civil services training:  Shashank Atom, Jojo Mathew, Manoj K. Singh amongst others at the institute who famously chisel out IAS aspirants into steel pillars of the nation."
        		+ "\n\nAt ALS, the tough taskmasters ignite the learner's intellect in you.\n\n"
        		+ "ALS expects its faculty to be leaders in their respective fields. It follows a strict code of ethics for the 'Professorial Scholars' who develop thorough cognition in students that prepares them for life.\n\n"
        		+ "Teachers at ALS shape educational practices, promote conceptual clarity and actuate the drive to perform among aspirants to enable them to take leadership positions. Students gain comprehensive access to India's smartest IAS coaching faculty with a teaching methodology that elevates versatile eruditeness.\n\n"
        		+ "Tap into ALS, its cutting edge insight and intellectual capital to actualise your goal of becoming a Civil Servant."

);
        
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        
        return rootView;
    }
	
}
