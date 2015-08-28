package com.example.noteproject;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AccessDB {
	public SQLiteDatabase database;
	public DefineDB sqlHelper;
	public String[] rowData = { DefineDB.COLUMN_ID, DefineDB.COLUMN_TITLE,
			DefineDB.COLUMN_CONTENT, DefineDB.COLUMN_DATE_CREATED,
			DefineDB.COLUMN_DATE_MODIFIED, DefineDB.COLUMN_ALARM,
			DefineDB.COLUMN_REMIND };

	public AccessDB(Context context) {
		sqlHelper = new DefineDB(context);
	}

	public void openDB() {
		database = sqlHelper.getWritableDatabase();
	}

	public void closeDB() {
		database.close();
	}

	public NoteItem createRow(String title, String content, String created,
			String modified, int alarm, String remind) {
		ContentValues values = new ContentValues();
		// values.put(DefineDB.COLUMN_LINK, link);
		values.put(DefineDB.COLUMN_TITLE, title);
		values.put(DefineDB.COLUMN_CONTENT, content);
		values.put(DefineDB.COLUMN_DATE_CREATED, created);
		values.put(DefineDB.COLUMN_DATE_MODIFIED, modified);
		values.put(DefineDB.COLUMN_ALARM, alarm);
		values.put(DefineDB.COLUMN_REMIND, remind);
		long insertId = database.insert(DefineDB.TABLE_NAME, null, values);
		Cursor cursor = database.query(DefineDB.TABLE_NAME, rowData,
				DefineDB.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		NoteItem item = cursorToItem(cursor);
		cursor.close();
		return item;
	}
	
	
	
	public NoteItem findById(int id) {
		Cursor cs1 = database.rawQuery("SELECT * FROM " + DefineDB.TABLE_NAME
				+ " WHERE " + DefineDB.COLUMN_ID + " = " + "'" + id + "';",
				null);
		cs1.moveToFirst();
		return cursorToItem(cs1);
	}

	public void deleteRow(int id) {
		int i = database.delete(DefineDB.TABLE_NAME, DefineDB.COLUMN_ID + "="
				+   id , null);
		Log.d("delete", "" +i);
	}

	// public int selectByPhone(String phone) {
	// Cursor cs1 = database.rawQuery("SELECT * FROM " + DefineDB.TABLE_NAME
	// + " WHERE " + DefineDB.COLUMN_PHONE + " = " + "'" + phone
	// + "';", null);
	// return cs1.getCount();
	// }

	public List<NoteItem> selectAll() {
		List<NoteItem> value = new ArrayList<NoteItem>();
		Cursor cs1 = database.query(DefineDB.TABLE_NAME, rowData, null, null,
				null, null, null);
		cs1.moveToFirst();
		while (!cs1.isAfterLast()) {
			value.add(cursorToItem(cs1));
			cs1.moveToNext();
			
		}
		cs1.close();
		return value;
	}

	private NoteItem cursorToItem(Cursor cursor) {
		NoteItem item = new NoteItem();
		item.setId(cursor.getInt(0));
		item.setInfo(cursor.getString(1), cursor.getString(2),
				cursor.getString(3), Integer.parseInt(cursor.getString(5)));
		item.setModified(cursor.getString(4));
		item.setRemind(cursor.getString(6));
		Log.d("id",""+item.getId());
		return item;
	}

	public void updateTitle(int id, String title) {
		ContentValues values = new ContentValues();
		values.put(DefineDB.COLUMN_TITLE, title);
		database.update(DefineDB.TABLE_NAME, values, DefineDB.COLUMN_ID + " = "
				+ "'" + id + "'", null);
	}

	public void updateContent(int id, String content) {
		ContentValues values = new ContentValues();
		values.put(DefineDB.COLUMN_CONTENT, content);
		database.update(DefineDB.TABLE_NAME, values, DefineDB.COLUMN_ID + " = "
				+ "'" + id + "'", null);
	}

	public void updateAlarm(int id, int alarm, String remind) {
		ContentValues values = new ContentValues();
		values.put(DefineDB.COLUMN_ALARM, alarm);
		values.put(DefineDB.COLUMN_REMIND, remind);
		database.update(DefineDB.TABLE_NAME, values, DefineDB.COLUMN_ID + " = "
				+ "'" + id + "'", null);
	}

	public void updateTitleContent(int id, String title,String content) {
		ContentValues values =new ContentValues();
		values.put(DefineDB.COLUMN_TITLE,title);
		values.put(DefineDB.COLUMN_CONTENT, content);
		database.update(DefineDB.TABLE_NAME, values, DefineDB.COLUMN_ID+" = " + id, null);
	}
}
