package com.exemple.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginws.FamiliaDAO;
import com.example.loginws.R;

public class NewUserActivity extends Activity {

	private FamiliaDAO dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		dao = new FamiliaDAO(this);
		dao.open();
		
		Button save = (Button)findViewById(R.id.buttonSave);
		final EditText user = (EditText)findViewById(R.id.editUser);
		final EditText pwd = (EditText)findViewById(R.id.editPwd);
		
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				String usuario = user.getEditableText().toString();
				String senha = pwd.getEditableText().toString();
				//dao.create(usuario, senha);
				finish();
				Log.i("SQLite", "Provavelmente gravou!");
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_user, menu);
		return true;
	}

}
