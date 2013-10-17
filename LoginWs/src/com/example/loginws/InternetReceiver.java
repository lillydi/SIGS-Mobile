package com.example.loginws;

import com.exemple.activities.InternetDesativada;
import com.exemple.activities.MainActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class InternetReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		Intent main = new Intent(context, MainActivity.class);
		Intent it = new Intent(context, InternetDesativada.class);
		main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (!AndroidUtils.verificaConexao(context)) {
			context.startActivity(it);
		} else {
			context.startActivity(main);
		
		}
	}
}
