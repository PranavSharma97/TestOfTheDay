package com.ALS.als_testoftheday1;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Help extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_help);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		
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
		
		
		TextView helpTextView = (TextView)findViewById(R.id.textViewHelp);
		helpTextView.setText(help);
	}
//	public void openFile(View view)
//	{
//		File pdfFile = new File("res/raw/file.pdf");
//		Uri path = Uri.fromFile(pdfFile);
//		Intent intent = new Intent(Intent.ACTION_VIEW);
//		intent.setDataAndType(path, "application/pdf");
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		CopyReadAssets();
//	}
	
//	  private void CopyReadAssets()
//      {
//          AssetManager assetManager = getAssets();
//
//          InputStream in = null;
//          OutputStream out = null;
//          File file = new File(getFilesDir(), "abc.pdf");
//          try
//          {
//              in = assetManager.open("abc.pdf");
//              out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);
//
//              copyFile(in, out);
//              in.close();
//              in = null;
//              out.flush();
//              out.close();
//              out = null;
//          } catch (Exception e)
//          {
//              Log.e("tag", e.getMessage());
//          }
//
//          Intent intent = new Intent(Intent.ACTION_VIEW);
//          intent.setDataAndType(
//                  Uri.parse("file://" + getFilesDir() + "/abc.pdf"),
//                  "application/pdf");
//
//          startActivity(intent);
//      }
//
//      private void copyFile(InputStream in, OutputStream out) throws IOException
//      {
//          byte[] buffer = new byte[1024];
//          int read;
//          while ((read = in.read(buffer)) != -1)
//          {
//              out.write(buffer, 0, read);
//          }
//      }


  }

