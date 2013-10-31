package com.example.loginws;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FamiliaDAO {
	private SQLiteDatabase database;
	private String[] columns = { 	DatabaseSQLiteOpenHelper.COLUMN_ID, DatabaseSQLiteOpenHelper.COLUMN_NOME,
			DatabaseSQLiteOpenHelper.COLUMN_DATA, DatabaseSQLiteOpenHelper.COLUMN_ENDERECO,
			DatabaseSQLiteOpenHelper.COLUMN_TELEFONE, DatabaseSQLiteOpenHelper.COLUMN_CEP,
			DatabaseSQLiteOpenHelper.COLUMN_BAIRRO, DatabaseSQLiteOpenHelper.COLUMN_RG,
			DatabaseSQLiteOpenHelper.COLUMN_CPF, DatabaseSQLiteOpenHelper.COLUMN_LOCK,
			DatabaseSQLiteOpenHelper.COLUMN_LAT, DatabaseSQLiteOpenHelper.COLUMN_LON};
	
	private DatabaseSQLiteOpenHelper sqliteHelper;
	
	public FamiliaDAO(Context context){
		sqliteHelper = new DatabaseSQLiteOpenHelper(context);
	}
	public void open(){
		database = sqliteHelper.getWritableDatabase();
	}
	
	public void close(){
		sqliteHelper.close();
	}
	public Familia create(String nome, String rg, String cpf, Double lon, Double lat ){
		ContentValues values = new ContentValues();
		values.put(DatabaseSQLiteOpenHelper.COLUMN_NOME, nome);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_DATA, "1992-06-15 00:00:00");
		values.put(DatabaseSQLiteOpenHelper.COLUMN_RG, rg);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_CPF, cpf);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_LON, lon);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_LAT, lat);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_ENDERECO, "Rua do Wesley");
		values.put(DatabaseSQLiteOpenHelper.COLUMN_TELEFONE, "9999-9999");
		values.put(DatabaseSQLiteOpenHelper.COLUMN_CEP, "79051-520");
		values.put(DatabaseSQLiteOpenHelper.COLUMN_BAIRRO, "Bairro da Liberdade");
		values.put(DatabaseSQLiteOpenHelper.COLUMN_LOCK, true);
		long insertId = database.insert(DatabaseSQLiteOpenHelper.TABLE_FAMILIA, null, values);
		Cursor cursor = database.query(DatabaseSQLiteOpenHelper.TABLE_FAMILIA, columns, 
				DatabaseSQLiteOpenHelper.COLUMN_ID +"="+ insertId, null, null, null, null);
		cursor.moveToFirst();
		Familia newFamily = new Familia();
		newFamily.setId(cursor.getLong(0));
		newFamily.setNome_titular(cursor.getString(1));
		newFamily.setData_nascimento_titular(cursor.getString(2));
		newFamily.setRg_titular(cursor.getString(3));
		newFamily.setCpf_titular(cursor.getString(4));
		newFamily.setLon(Double.parseDouble(cursor.getString(5)));
		newFamily.setLat(Double.parseDouble(cursor.getString(6)));
		newFamily.setEndereco(cursor.getString(7));
		newFamily.setTelefone_titular(cursor.getString(8));
		newFamily.setCEP(cursor.getString(9));
		newFamily.setBairro(cursor.getString(10));
		newFamily.setLock(true);
		cursor.close();
		return newFamily;
	}
	
	public List<Familia> listar(){
		List<Familia> familias = new ArrayList<Familia>();
		Cursor cursor = database.query(DatabaseSQLiteOpenHelper.TABLE_FAMILIA, columns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Familia familia = new Familia();
			familia.setId(cursor.getLong(0));
			familia.setNome_titular(cursor.getString(1));
			familia.setData_nascimento_titular(cursor.getString(2));
			familia.setRg_titular(cursor.getString(3));
			familia.setCpf_titular(cursor.getString(4));
			familia.setLon(Double.parseDouble(cursor.getString(5)));
			familia.setLat(Double.parseDouble(cursor.getString(6)));
			familia.setEndereco(cursor.getString(7));
			familia.setTelefone_titular(cursor.getString(8));
			familia.setCEP(cursor.getString(9));
			familia.setBairro(cursor.getString(10));
			familia.setLock(true);
			familias.add(familia);
			cursor.moveToNext();
		}
		cursor.close();
		return familias;
		
	}
}
