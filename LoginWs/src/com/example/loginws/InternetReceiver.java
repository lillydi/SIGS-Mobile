package com.example.loginws;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.exemple.activities.InternetDesativada;
import com.exemple.activities.MainActivity;

@SuppressLint("ShowToast")
public class InternetReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving

		Intent mainActivity = new Intent(context, MainActivity.class);
		Intent internetDesativada = new Intent(context, InternetDesativada.class);
		
		mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		internetDesativada.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		if (!AndroidUtils.verificaConexao(context)) {
			Log.i("InternetReceiver", "SEM INTERNET!");
		
			Toast.makeText(context, "Internet DESATIVADA!!", 1).show();
			//context.startActivity(mainActivity);
		} else {
			Log.i("InternetReceiver", "COM INTERNET!");
			
			Toast.makeText(context, "Internet ATIVADA!!", Toast.LENGTH_SHORT).show();
			//context.startActivity(mainActivity);		
		}
	}
}
