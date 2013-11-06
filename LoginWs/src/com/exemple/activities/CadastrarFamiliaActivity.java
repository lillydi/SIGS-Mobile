package com.exemple.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginws.DatabaseSQLiteOpenHelper;
import com.example.loginws.FamiliaDAO;
import com.example.loginws.R;

public class CadastrarFamiliaActivity extends Activity implements LocationListener {

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
				//lat = 10.32;
				//lon = 10.32;
				Log.i("Cadastro", "Valores dos campos:" +nomeTit.getText().toString()+" "+rgTit.getText().toString()+" "+cpfTit.getText().toString()+" "+
				dataNasc.getText().toString()+" "+telefone.getText().toString()+" "+cep.getText().toString()+" "+
						endereco.getText().toString()+" "+bairro.getText().toString()+" "+lat+" "+lon);
				
					familia.create(
							nomeTit.getText().toString(), 
							rgTit.getText().toString(), 
							cpfTit.getText().toString(), 
							dataNasc.getText().toString(), 
							telefone.getText().toString(), 
							cep.getText().toString(), 
							endereco.getText().toString(), 
							bairro.getText().toString(), 
							lon, 
							lat
								 );
				
				startActivity(listar);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_familia, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		this.lat = location.getLatitude();
		this.lon = location.getLongitude();		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Provedor desabilitado!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Provedor habilitado!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
