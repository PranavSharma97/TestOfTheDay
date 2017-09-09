package com.ALS.als_testoftheday1;

import java.util.GregorianCalendar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.ListView;

public class Teachers extends ActionBarActivity {

	ImageView imageView1;
	RoundImage roundedImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teachers);
		String teachers[] = { "Shashank Atom", "Jojo Mathews",
				"Manoj Kumar Singh", "Santosh Rai", "Manish Gautam",
				"Neeraj Kumar Singh", "Manjula Khatri","K.Krishna Reddy","Arbind Singh","Sachin Arora"
						+ "","Arunesh Singh","Ajay Srivastava" };

		ListViewAdapter teachersAdapter = new ListViewAdapter(
				getApplicationContext(), teachers);
		ListView listAdapter = (ListView) findViewById(R.id.listOfTeachers);
		listAdapter.setAdapter(teachersAdapter);
	}
}
