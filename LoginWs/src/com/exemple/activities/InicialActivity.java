package com.exemple.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import com.example.loginws.AndroidUtils;
import com.example.loginws.R;

public class InicialActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicial);
		Intent it;
		
		EditText usuario = (EditText) findViewById(R.id.usuario);
		EditText senha = (EditText) findViewById(R.id.senha);
		
		if (!AndroidUtils.verificaConexao(this)) {
			it = new Intent(this, InternetDesativada.class);
			startActivity(it);
		} else {
			it = new Intent(this, MainActivity.class);
			startActivity(it);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicial, menu);
		return true;
	}

}
