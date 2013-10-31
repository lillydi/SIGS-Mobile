package com.exemple.activities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

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

public class MainActivity extends Activity {
	
	private BroadcastReceiver connectivityReceiver;
	
	EditText senha;
	EditText usuario;
	Button auth;
	TextView tvTeste;
	int count = 0;
	
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
				
				String url = "http://192.168.0.113/WsAuth/server.php";
				//execute(url, usuario, senha)
				SoapObject soap = new SoapObject("urn:WsAuth", "Auth");
				
				soap.addProperty("login", usuario.getText().toString());
				try {
					soap.addProperty("senha", SHA1(senha.getText().toString()));
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
				Log.i("loginWs", "OK");
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(soap);
				
				Log.i("loginWs", "Chamando Webservice..." + url);
				
				HttpTransportSE httpTransport = new HttpTransportSE(url);
				Log.i("loginWs", "Passou1..." + url);
				try {
					httpTransport.call("", envelope);
					//Log.i("loginWs", "Passou2..." + url);
					Object msg = envelope.getResponse();
					//Log.i("loginWs", "Passou3..." + url);
					Log.i("loginWs", "Webservice executado!! Resposta: " + msg);
					
					if (msg.equals("OK")) {
						startActivity(i);
					} else {
						TextView t = (TextView)findViewById(R.id.teste);
						t.setText(msg.toString());							
					}
				}
				catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
				catch (XmlPullParserException ex) {
					System.out.println(ex.getMessage());
				}
				
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
	
	private static String convertToHex(byte[] data) { 
		StringBuffer strbuf = new StringBuffer(data.length * 2);

        for(int i=0; i< data.length; i++)
        {
                if(((int) data[i] & 0xff) < 0x10)
                        strbuf.append("0");
                strbuf.append(Long.toString((int) data[i] & 0xff, 16));
        }
        return strbuf.toString();
	}

	public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
	    MessageDigest md = MessageDigest.getInstance("SHA-1");
	    byte[] sha1hash = new byte[100];
	    md.update(text.getBytes("iso-8859-1"), 0, text.length());
	    sha1hash = md.digest();
	    return convertToHex(sha1hash);
	}
	
	

}
