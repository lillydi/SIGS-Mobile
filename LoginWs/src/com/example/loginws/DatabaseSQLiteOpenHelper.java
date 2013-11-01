package com.example.loginws;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSQLiteOpenHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_FAMILIA = "familias";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NOME = "nome_titular";
	public static final String COLUMN_DATA = "data_nascimento";
	public static final String COLUMN_ENDERECO = "endereco";
	public static final String COLUMN_TELEFONE = "telefone";
	public static final String COLUMN_CEP = "CEP";
	public static final String COLUMN_BAIRRO = "bairro";
	public static final String COLUMN_RG = "rg";
	public static final String COLUMN_CPF = "cpf";
	public static final String COLUMN_LOCK = "lock";
	public static final String COLUMN_LON = "lon";
	public static final String COLUMN_LAT = "lat";
	
	public static final String DATABASE_NAME = "sigs.db";
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_CREATION = "create table "+ TABLE_FAMILIA + "(" + 
			COLUMN_ID + " integer primary key autoincrement, " + 
			COLUMN_NOME + " varchar not null, " + 
			COLUMN_DATA + " date not null, " +
			COLUMN_ENDERECO+ " varchar not null, " +
			COLUMN_TELEFONE + " varchar not null, " +
			COLUMN_CEP + " varchar not null, " +
			COLUMN_BAIRRO + " varchar not null, " +
			COLUMN_RG + " varchar not null, " +
			COLUMN_CPF + " varchar not null, " +
			COLUMN_LOCK + " bool not null, " +
			COLUMN_LON + " double not null ," +
			COLUMN_LAT + " double not null " +
			");";

	public DatabaseSQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXIST " + TABLE_FAMILIA);
		onCreate(db);
	}

}
