package com.example.loginws;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
	    MessageDigest md = MessageDigest.getInstance("SHA-1");
	    byte[] sha1hash = new byte[100];
	    md.update(text.getBytes("iso-8859-1"), 0, text.length());
	    sha1hash = md.digest();
	    return convertToHex(sha1hash);
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

}
