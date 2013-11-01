package com.exemple.activities;

import com.example.loginws.Familia;
import com.example.loginws.FamiliaDAO;
import com.example.loginws.R;
import com.example.loginws.R.layout;
import com.example.loginws.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarFamiliaActivity extends Activity {

	//criacao de variaveis
	
	EditText nomeTit;
	EditText rgTit;
	EditText cpfTit;
	EditText dataNasc;
	EditText telefone;
	EditText cep;
	EditText endereco;
	EditText bairro;
	Button cadastrar;
	Double lat;
	Double lon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_familia);
		
		final FamiliaDAO familia = new FamiliaDAO(this);
		familia.open();
		final Intent listar = new Intent(this, ListarFamiliaActivity.class) ;
		cadastrar = (Button)findViewById(R.id.buttonCadastrar);
		cadastrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				nomeTit = (EditText)findViewById(R.id.nomeTitular);
				rgTit = (EditText)findViewById(R.id.rgTitular);
				cpfTit = (EditText)findViewById(R.id.cpfTitular);
				dataNasc = (EditText)findViewById(R.id.dataNascTitular);
				telefone = (EditText)findViewById(R.id.telefoneTitular);
				cep = (EditText)findViewById(R.id.cepTitular);
				endereco = (EditText)findViewById(R.id.enderecoTitular);
				bairro = (EditText)findViewById(R.id.bairroTitular);
				lat = 10.32;
				lon = 10.32;
				Log.i("Cadastro", "Valores dos campos:" +nomeTit.getText().toString()+" "+rgTit.getText().toString()+" "+cpfTit.getText().toString()+" "+
				dataNasc.getText().toString()+" "+telefone.getText().toString()+" "+cep.getText().toString()+" "+
						endereco.getText().toString()+" "+bairro.getText().toString()+" "+lat+" "+lon);
				
				familia.create(nomeTit.getText().toString(), rgTit.getText().toString(), cpfTit.getText().toString(),
						dataNasc.getText().toString(), telefone.getText().toString(), cep.getText().toString(), endereco.getText().toString(),
						bairro.getText().toString(), lon, lat);
				
				startActivity(listar);
				}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_familia, menu);
		return true;
	}

}
