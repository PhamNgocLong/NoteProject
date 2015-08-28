package com.example.noteproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class TextChoice extends ActionBarActivity {
	EditText title, content;
	String action;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_choice);
		title = (EditText) findViewById(R.id.editText1);
		content = (EditText) findViewById(R.id.editText2);
		action = "add new";
		Intent edit = getIntent();
		if (edit.hasExtra(MainActivity.KEY_CODE)
				&& edit.getIntExtra(MainActivity.KEY_CODE, 0) == MainActivity.EDIT_KEY) {
			onDisplay(edit.getStringExtra("title"),
					edit.getStringExtra("content"));
			action = "edit";
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (action.equals("add new")) {
			Intent returnIntent = new Intent();
			if (!title.getText().toString().isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				// System.out.println(dateFormat.format(date));
				returnIntent.putExtra("title", title.getText().toString());
				returnIntent.putExtra("content", content.getText().toString());
				returnIntent.putExtra("date", dateFormat.format(date));
				setResult(RESULT_OK, returnIntent);
				finish();
			} else {
				setResult(RESULT_CANCELED, returnIntent);
				finish();
			}
		}
		
		if(action.equals("edit"))
		{
			Intent returnIntent = new Intent();
			returnIntent.putExtra("title", title.getText().toString());
			Log.d("title", title.getText().toString());
			returnIntent.putExtra("content", content.getText().toString());
			Log.d("content", content.getText().toString());
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		// super.onBackPressed();
	}

	public void onDisplay(String title_str, String content_str) {
		title.setText(title_str);
		content.setText(content_str);
	}
}
