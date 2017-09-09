package com.ALS.als_testoftheday1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<String> {
	Intent intent;
	private final String values[];
	private final Context context;

	public ListViewAdapter(Context context, String values[]) {
		super(context, R.layout.listview_layout, values);
		this.context = context;
		this.values = values;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater
				.inflate(R.layout.listview_layout, parent, false);
		ImageView imageView1 = (ImageView) rowView.findViewById(R.id.icon);
		RoundImage roundedImage;
		Bitmap bm;
		switch (position) {
		case 0:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.shashank);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 1:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.jojo);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 2:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.manoj);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 3:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.kmpathi);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 4:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.manish);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 5:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.sharad);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 6:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.manjula);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 7:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.sanjay);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 8:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.arbind);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 9:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.sachin);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 10:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.rcsinha);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 11:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.arunesh);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 12:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.ajay);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 13:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.shweta);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 14:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.ranjana);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;
		case 15:
			bm = BitmapFactory.decodeResource(rowView.getResources(),
					R.drawable.kkrishna);
			roundedImage = new RoundImage(bm);
			imageView1.setImageDrawable(roundedImage);
			break;

		}

		TextView textView = (TextView) rowView.findViewById(R.id.label);
		textView.setText(values[position]);

		return rowView;
	}
}
