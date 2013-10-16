package com.example.loginws;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDAO {
	private SQLiteDatabase database;
	private String[] columns = { 	DatabaseSQLiteOpenHelper.COLUMN_ID, DatabaseSQLiteOpenHelper.COLUMN_USER
			, DatabaseSQLiteOpenHelper.COLUMN_PASSWORD};
	
	private DatabaseSQLiteOpenHelper sqliteHelper;
	
	public UsersDAO(Context context){
		sqliteHelper = new DatabaseSQLiteOpenHelper(context);
	}
	public void open(){
		database = sqliteHelper.getWritableDatabase();
	}
	
	public void close(){
		sqliteHelper.close();
	}
	public Users create(String user, String pwd){
		ContentValues values = new ContentValues();
		values.put(DatabaseSQLiteOpenHelper.COLUMN_USER, user);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_PASSWORD, pwd);
		long insertId = database.insert(DatabaseSQLiteOpenHelper.TABLE_USERS, null, values);
		Cursor cursor = database.query(DatabaseSQLiteOpenHelper.TABLE_USERS, columns, 
				DatabaseSQLiteOpenHelper.COLUMN_ID +"="+ insertId, null, null, null, null);
		cursor.moveToFirst();
		Users newUser = new Users();
		newUser.setId(cursor.getLong(0));
		newUser.setUser(cursor.getString(1));
		newUser.setPwd(cursor.getString(2));
		cursor.close();
		return newUser;
	}
}
