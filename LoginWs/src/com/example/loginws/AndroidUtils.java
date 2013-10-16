package com.example.loginws;

import android.content.Context;
import android.net.ConnectivityManager;

public class AndroidUtils {
	
	public static boolean verificaConexao(Context ctx) {  
	    boolean conectado;  
	    ConnectivityManager conectivtyManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);  
	    if (conectivtyManager.getActiveNetworkInfo() != null  
	            && conectivtyManager.getActiveNetworkInfo().isAvailable()  
	            && conectivtyManager.getActiveNetworkInfo().isConnected()) {  
	        conectado = true;  
	    } else {  
	        conectado = false;  
	    }  
	    return conectado;  
	}

}
