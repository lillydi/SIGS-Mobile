package com.exemple.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginws.R;

public class InternetDesativada extends Activity {

	public Activity activityAtual = this;
	public Activity parent = activityAtual.getParent();
	
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.internet_desativada);
		
		Button bt = (Button) findViewById(R.id.button1);
		
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent it = new Intent(activityAtual, InicialActivity.class);
				startActivity(it);
			}
		});
	}
}
