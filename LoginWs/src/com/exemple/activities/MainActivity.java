package com.exemple.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginws.AndroidUtils;
import com.example.loginws.R;
import com.example.loginws.WebServiceDroid;

public class MainActivity extends Activity {
	
	private BroadcastReceiver connectivityReceiver;
	
	EditText senha;
	EditText usuario;
	Button auth;
	TextView tvTeste;
	int count = 0;
	WebServiceDroid processo = new WebServiceDroid(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		final Intent i = new Intent(this, NewUserActivity.class);
		
		usuario = (EditText) findViewById(R.id.usuario);
		senha = (EditText) findViewById(R.id.senha);
		
		auth = (Button) findViewById(R.id.auth);
		tvTeste = (TextView) findViewById(R.id.teste);
		
		connectivityReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				if (!AndroidUtils.verificaConexao(context)) { //Internet desativada
					Log.i("loginWs", ++count + ") Internet desabilitada!");
					
					usuario.setEnabled(false);
					senha.setEnabled(false);
					auth.setClickable(false);
					
					Toast.makeText(context, "Por favor ative sua Internet!!", Toast.LENGTH_SHORT).show();
					
					startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
				} else { //Internet ativada
					Log.i("loginWs", ++count + ") Internet habilitada!");
					
					Toast.makeText(context, "Internet habilitada!", Toast.LENGTH_SHORT).show();
					
					usuario.setEnabled(true);
					senha.setEnabled(true);
					auth.setClickable(true);				
				}
			}
		};
		
		auth.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		        processo.execute("http://10.6.125.119/WebService/server.php", usuario.getText().toString(), senha.getText().toString());
				
			}
			
		});

	}
		
	@Override
	protected void onResume() {
		super.onResume();
		
		// Cria o intent filter e registra o receiver que ir� tratar esta
        // notifica��o
		
		IntentFilter intentFilter = new IntentFilter();
		
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(connectivityReceiver, intentFilter);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		// Remove a notifica��o, pois ela s� � necess�ria quando a activity est�
        // no topo da pilha
		
		unregisterReceiver(connectivityReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}