package com.example.noteproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.noteproject.OptionDialog.MessageAlertDialogInput;

import android.support.v7.app.ActionBarActivity;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		OptionDialog.MessageAlertDialogInput,
		com.example.noteproject.NoteItemOptionDialog.MessageAlertDialogInput {
	AccessDB database;
	Button add_btn;
	Context context;
	ListView noteList;
	ArrayList<NoteItem> arrlist1;
	public static final int REQUEST_ADD_NEW = 1234;
	public static final int EDIT_KEY = 211;
	public static final String KEY_CODE = "keycode";
	public static final int REQUEST_EDIT = 345;
	int noteItemId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = this;
		add_btn = (Button) findViewById(R.id.button1);
		noteList = (ListView) findViewById(R.id.listView);
		database = new AccessDB(this);
		database.openDB();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		// database.createRow("title", "123", dateFormat.format(date),
		// dateFormat.format(date), 0, null);
		refresh();
		noteList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				NoteItemOptionDialog choice1 = new NoteItemOptionDialog(
						arrlist1.get(position).getId());
				choice1.show(getFragmentManager(), "choice");

				// TODO Auto-generated method stub

			}
		});
		// Log.d("",""+list1.get(0).getId()+ " " + list1.get(0).getTitle() +" "
		// + list1.get(0).getContent() +" "+ list1.get(0).getCreated()+ " " +
		// list1.get(0).getModified()+" " +" " +list1.get(0).getAlarm());

		add_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				OptionDialog dialog1 = new OptionDialog(MainActivity.this);
				dialog1.show(getFragmentManager(), "exp");
			}
		});

	}

	public void refresh() {
		// noteList.removeAllViews();
		List<NoteItem> list1 = database.selectAll();
		arrlist1 = new ArrayList<NoteItem>();
		for (int i = 0; i < list1.size(); i++) {
			arrlist1.add(new NoteItem(list1.get(i).getId(), list1.get(i)
					.getTitle(), list1.get(i).getContent(), null, null, 0, null));
		}
		// ArrayAdapter<String> adp1 = new
		// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrlist1);
		MyAdapter adp1 = new MyAdapter(this, arrlist1);
		noteList.setAdapter(adp1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDialogSaveClick(DialogFragment dialog, String choice) {
		// TODO Auto-generated method stub
		// Log.d("choice", choice);
		// Toast.makeText(MainActivity.this, choice, Toast.LENGTH_SHORT).show();
		if (choice.equals("Text")) {
			Intent intent1 = new Intent(context, TextChoice.class);
			startActivityForResult(intent1, MainActivity.REQUEST_ADD_NEW);
		}
	}

	@Override
	public void onDialogDeleteClick(DialogFragment dialog, String name,
			String phone) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(arg0, arg1, arg2);
		Log.d("key", "" + requestCode);
		if (requestCode == MainActivity.REQUEST_ADD_NEW) {
			if (resultCode == RESULT_OK) {
				database.createRow(data.getStringExtra("title"),
						data.getStringExtra("content"),
						data.getStringExtra("date"),
						data.getStringExtra("date"), 0, null);
				refresh();
			}
			if (resultCode == RESULT_CANCELED)
				Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT)
						.show();
		}
		if (requestCode == MainActivity.REQUEST_EDIT) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(MainActivity.this, "Update", Toast.LENGTH_SHORT)
						.show();

				database.updateTitleContent(noteItemId,
						data.getStringExtra("title"),
						data.getStringExtra("content"));
				// lLog.d("title",data.getStringExtra("title"));
				// noteItemId
				refresh();
			}
		}

	}

	@Override
	public void onDialogItemClick(DialogFragment dialog, String choice1,
			int noteItem_id) {
		// TODO Auto-generated method stub
		// Log.d("Choice",choice1);
		if (choice1.equals("Edit")) {
			NoteItem noteItem1 = database.findById(arrlist1.get(
					noteItem_id).getId());
			noteItemId = noteItem1.getId();
			Intent gotoEdit = new Intent(context, TextChoice.class);
			gotoEdit.putExtra(KEY_CODE, EDIT_KEY);
			gotoEdit.putExtra("title", noteItem1.getTitle());
			gotoEdit.putExtra("content", noteItem1.getContent());

			startActivityForResult(gotoEdit, REQUEST_EDIT);
		}

		if (choice1.equals("Delete")) {
			//delete
			database.deleteRow(noteItem_id);
			Log.d("delete_id",""+noteItem_id);
			refresh();
			Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT)
			.show();
		}
	}

}
