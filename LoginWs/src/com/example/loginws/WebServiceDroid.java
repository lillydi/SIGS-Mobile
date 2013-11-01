package com.example.loginws;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.exemple.activities.InternetDesativada;
import com.exemple.activities.NewUserActivity;
import com.exemple.activities.OpcaoActivity;

public class WebServiceDroid extends AsyncTask<String, String, String> {
	 
    private ProgressDialog progress;
    private Context context;

    public WebServiceDroid(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        //Cria novo um ProgressDialogo e exibe
        progress = new ProgressDialog(context);
        progress.setMessage("Aguarde...");
        progress.show();
    }

    @Override
    protected String doInBackground(String... paramss) {
       String url = paramss[0];
       Log.i("Teste", "Teste 1 dentro async"+paramss[0]);
		SoapObject soap = new SoapObject("urn:WsAuth", "Auth");
		
		soap.addProperty("login", paramss[1]);
		try {
			soap.addProperty("senha", AndroidUtils.SHA1(paramss[2]));
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
		//Log.i("loginWs", "Passou1..." + url);
		try {
			
			httpTransport.call("", envelope);
			Log.i("loginWs", "Passou2..." + url);
			Object msg = envelope.getResponse();
			//Log.i("loginWs", "Passou3..." + url);
			//Log.i("loginWs", "Webservice executado!! Resposta: " + msg.toString());
			//Log.i("Teste", "Teste resultado servidor " +msg.toString());
			if (msg.equals("ok")) {
				 return "ok";
			} else {
				//TextView t = (TextView)findViewById(R.id.teste);
				//t.setText(msg.toString());
				return "fail";
			}
			
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		catch (XmlPullParserException ex) {
			System.out.println(ex.getMessage());
		}
		return "fail";
    }

    @Override
    protected void onPostExecute(String result) {
        //Cancela progressDialogo
    	  progress.dismiss();
    	final Intent telaOpcao = new Intent(context, OpcaoActivity.class);
    	final Intent telaInternetDesativada = new Intent(context, InternetDesativada.class);
    	if (result.equals("ok")) {
			//return "ok";
    		context.startActivity(telaOpcao);
		} else {
			context.startActivity(telaInternetDesativada);
		}
    	
    }

    @Override
    protected void onProgressUpdate(String... values) {
        //Atualiza mensagem
        progress.setMessage(values[0]);
    }
}