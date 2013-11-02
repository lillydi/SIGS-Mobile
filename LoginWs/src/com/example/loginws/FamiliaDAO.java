package com.example.loginws;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FamiliaDAO {
	private SQLiteDatabase database;
	private String[] columns = { 	DatabaseSQLiteOpenHelper.COLUMN_ID,
			DatabaseSQLiteOpenHelper.COLUMN_NOME,
			DatabaseSQLiteOpenHelper.COLUMN_RG,
			DatabaseSQLiteOpenHelper.COLUMN_CPF,
			DatabaseSQLiteOpenHelper.COLUMN_DATA, 
			DatabaseSQLiteOpenHelper.COLUMN_TELEFONE,
			DatabaseSQLiteOpenHelper.COLUMN_CEP,
			DatabaseSQLiteOpenHelper.COLUMN_ENDERECO,
			DatabaseSQLiteOpenHelper.COLUMN_BAIRRO, 
			DatabaseSQLiteOpenHelper.COLUMN_LAT, 
			DatabaseSQLiteOpenHelper.COLUMN_LON,
			DatabaseSQLiteOpenHelper.COLUMN_LOCK		
	};
	
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
	public Familia create(String nome, String rg, String cpf, String data, String telefone, String cep, String endereco, String bairro, 
			Double lon, Double lat){
		ContentValues values = new ContentValues();
		values.put(DatabaseSQLiteOpenHelper.COLUMN_NOME, nome);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_DATA, data);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_RG, rg);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_CPF, cpf);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_LON, lon);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_LAT, lat);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_ENDERECO, endereco);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_TELEFONE, telefone);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_CEP, cep);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_BAIRRO, bairro);
		values.put(DatabaseSQLiteOpenHelper.COLUMN_LOCK, true);
		Log.i("Dentro create", "Cheguei aqui "+values.toString());
		long insertId = database.insert(DatabaseSQLiteOpenHelper.TABLE_FAMILIA, null, values);
		Log.i("Dentro create", "Cheguei aqui");
		Cursor cursor = database.query(DatabaseSQLiteOpenHelper.TABLE_FAMILIA, columns, 
				DatabaseSQLiteOpenHelper.COLUMN_ID +"="+ insertId, null, null, null, null);
		
		cursor.moveToFirst();
		Familia newFamily = new Familia(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), 
				cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), 
				Double.parseDouble(cursor.getString(9)), Double.parseDouble(cursor.getString(10)));
		newFamily.setLock(true);
		cursor.close();
		return newFamily;
	}
	
	
	//Arrumar isso pq columns foi modificada
	public List<Familia> listar(){
		List<Familia> familias = new ArrayList<Familia>();
		Cursor cursor = database.query(DatabaseSQLiteOpenHelper.TABLE_FAMILIA, columns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Familia familia = new Familia();
			familia.setId(cursor.getLong(0));
			familia.setNome_titular(cursor.getString(1));
			familia.setRg_titular(cursor.getString(2));
			familia.setCpf_titular(cursor.getString(3));
			familia.setData_nascimento_titular(cursor.getString(4));
			familia.setTelefone_titular(cursor.getString(5));
			familia.setCEP(cursor.getString(6));
			familia.setEndereco(cursor.getString(7));
			familia.setBairro(cursor.getString(8));
			familia.setLon(Double.parseDouble(cursor.getString(9)));
			familia.setLat(Double.parseDouble(cursor.getString(10)));
			familia.setLock(true);
			familias.add(familia);
			cursor.moveToNext();
		}
		cursor.close();
		return familias;
		
	}
}
