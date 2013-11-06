package com.example.loginws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

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

import com.exemple.activities.ListarFamiliaActivity;

public class BuscarFamilias extends AsyncTask<String, String, String> {

    private ProgressDialog progress;
    private Context context;
    
	public BuscarFamilias(Context context) {
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
        String URL_WS = paramss[0];
        Log.i("buscarFamilias", "Teste 1 dentro async");
        SoapObject soap = new SoapObject(URL_WS, "ListarFamilias");
                        
        Log.i("buscarFamilias", "OK");
        
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        
        Log.i("buscarFamilias", "Chamando Webservice..." + URL_WS);
        
        HttpTransportSE httpTransport = new HttpTransportSE(URL_WS);

        Log.i("buscarFamilias", "Passou2..." + URL_WS);
        
        try {   
            httpTransport.call("", envelope);
            Log.i("buscarFamilias", "Passou3..." + URL_WS);
            Object result = envelope.getResponse();
            
            Vector<SoapObject> response = ((Vector<SoapObject>) result);
            
            ArrayList<Familia> listFamilias = new ArrayList<Familia>();
            
            for (SoapObject msg : response) {
            	Familia f = new Familia();
            	
            	f.setId((Long.parseLong(((SoapObject) msg.getProperty(0)).getProperty(1).toString())));
            	f.setNome_titular(((SoapObject) msg.getProperty(1)).getProperty(1).toString());
            	f.setData_nascimento_titular(((SoapObject) msg.getProperty(2)).getProperty(1).toString());
            	f.setEndereco(((SoapObject) msg.getProperty(3)).getProperty(1).toString());
            	f.setTelefone_titular(((SoapObject) msg.getProperty(4)).getProperty(1).toString());
            	f.setCEP(((SoapObject) msg.getProperty(5)).getProperty(1).toString());
            	f.setBairro(((SoapObject) msg.getProperty(6)).getProperty(1).toString());
            	f.setRg_titular(((SoapObject) msg.getProperty(7)).getProperty(1).toString());
            	f.setCpf_titular(((SoapObject) msg.getProperty(8)).getProperty(1).toString());
            	f.setLock(Boolean.parseBoolean(((SoapObject) msg.getProperty(9)).getProperty(1).toString()));
            	f.setLat(Double.parseDouble(((SoapObject) msg.getProperty(10)).getProperty(1).toString()));
            	f.setLon(Double.parseDouble(((SoapObject) msg.getProperty(11)).getProperty(1).toString()));
            	
            	listFamilias.add(f);
            }
            
            final FamiliaDAO familia = new FamiliaDAO(context);
    		familia.open();
            
            for (int i = 0; i < listFamilias.size(); i++) {
            	familia.create(
            			listFamilias.get(i).getNome_titular(), 
            			listFamilias.get(i).getRg_titular(), 
            			listFamilias.get(i).getCpf_titular(), 
            			listFamilias.get(i).getData_nascimento_titular(), 
            			listFamilias.get(i).getTelefone_titular(), 
            			listFamilias.get(i).getCEP(), 
            			listFamilias.get(i).getEndereco(), 
            			listFamilias.get(i).getBairro(), 
            			listFamilias.get(i).getLon(), 
            			listFamilias.get(i).getLat()
            				  );
            }
            
            return "Famílias importadas com sucesso!";
            
        }
        catch (IOException ex) {
            Log.i("buscarFamilias", "Catch 1: " + ex.getMessage());
            System.out.println(ex.getMessage());
        }
        catch (XmlPullParserException ex) {
            Log.i("buscarFamilias", "Catch 2: " + ex.getMessage());
            System.out.println(ex.getMessage());
        }
        return "Falha ao importar famílias...";
    }

    @Override
    protected void onPostExecute(String result) {
        //Cancela progressDialogo
        
        progress.dismiss();
        
        Log.i("buscarFamilias", "Resultado da operação: " + result);
        
        final Intent telaListarFamilia = new Intent(context, ListarFamiliaActivity.class);
        
        context.startActivity(telaListarFamilia);
        
    }
	
}
