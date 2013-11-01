package com.exemple.activities;

import com.example.loginws.R;
import com.example.loginws.R.layout;
import com.example.loginws.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class OpcaoActivity extends Activity {

	Button cadastrarFamilia;
	Button listarFamilias;
	Button buscarFamilias;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opcao);
		final Intent telaCadastro = new Intent(this, CadastrarFamiliaActivity.class);
		final Intent telaListagem = new Intent(this, ListarFamiliaActivity.class);
		
		cadastrarFamilia = (Button) findViewById(R.id.cadastrarOpt);
		listarFamilias = (Button) findViewById(R.id.listarOpt);
		buscarFamilias = (Button) findViewById(R.id.buscarOpt);
		
		cadastrarFamilia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(telaCadastro);
			}
		});
		listarFamilias.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			startActivity(telaListagem);	
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opcao, menu);
		return true;
	}

}
