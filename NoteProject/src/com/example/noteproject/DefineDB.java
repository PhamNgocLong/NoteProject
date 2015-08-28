package com.example.noteproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DefineDB extends SQLiteOpenHelper {
	public static final String TABLE_NAME = "notes";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_CONTENT = "content";
	public static final String COLUMN_DATE_CREATED = "created";
	public static final String COLUMN_DATE_MODIFIED = "modified";
	public static final String COLUMN_ALARM = "alarm";
	public static final String COLUMN_REMIND = "remind";
	private static final int DB_VERSION = 1;

	// Database creation sql statement
	public static final String CREATE_TABLE = "create table " + TABLE_NAME
			+ "(" 
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_TITLE + " text not null," 
			+ COLUMN_CONTENT + " text ," 
			+ COLUMN_DATE_CREATED+ " text ," 
			+ COLUMN_DATE_MODIFIED+ " text ," 
			+ COLUMN_ALARM + " INTERGER DEFAULT 0," 
			+ COLUMN_REMIND + " text);";

	public DefineDB(Context context) {
		super(context, CREATE_TABLE, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);

	}

	
}
