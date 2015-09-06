package com.mcerp.database;

import java.util.ArrayList;
import java.util.List;

import com.mcerp.model.Push;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "mcerpdb";
	Context con;
	private static final String TABLE_PUSH = "Push";
	private static final String KEY_VALUE = "value";

	private static final String KEY_ID = "id";

	public DatabaseHandler(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.con = context;
	}

	public void onCreate(SQLiteDatabase db) {
		String CREATE_PUSH_TABLE = "CREATE TABLE " + TABLE_PUSH + "(" + KEY_ID
				+ " TEXT," + KEY_VALUE + " TEXT" + ")";
		db.execSQL(CREATE_PUSH_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUSH);
		// Create tables again
		onCreate(db);
	}

	// inserting into push table
	public void addcontactpush(Push push) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, "regId");
		values.put(KEY_VALUE, push.getMsg());

		// Inserting Row
		db.insert(TABLE_PUSH, null, values);
		// Closing database connection
		db.close();
	}

	// Get All Messages From Push Table
	public List<Push> getAllPushMessages() {
		List<Push> contactList = new ArrayList<Push>();

		String selectQuery = "SELECT  * FROM " + TABLE_PUSH;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Push contact = new Push();
				contact.setMsg(cursor.getString(1));

				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		return contactList;
	}
}
