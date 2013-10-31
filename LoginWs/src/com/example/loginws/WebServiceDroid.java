package com.example.loginws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class WebServiceDroid extends AsyncTask<String, String, ArrayList<String>> {
	 
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
    protected ArrayList<String> doInBackground(String... paramss) {
       String url = paramss[0];
		SoapObject soap = new SoapObject("urn:WsAuth", "Auth");
		
		soap.addProperty("login", paramss[1]);
		try {
			soap.addProperty("senha", AndroidUtils.SHA1(paramss[2]);
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

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        //Cancela progressDialogo
    	String texto = "categorias: ";
        progress.dismiss();
        TextView  t = new TextView(context);
        t= (TextView)findViewById(R.id.test);
        for(int i = 0; i < result.size();i++){
        	texto = texto + result.get(i);
        }
        t.setText(texto);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        //Atualiza mensagem
        progress.setMessage(values[0]);
    }
    private String toString(InputStream is) throws IOException { 
    	byte[] bytes = new byte[1024]; 
    	ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
    	int lidos; 
    	while ((lidos = is.read(bytes)) > 0) { 
    		baos.write(bytes, 0, lidos); 
    		}
    	return new String(baos.toByteArray()); 
    	}


}