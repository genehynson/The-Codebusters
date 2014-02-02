 package com.example.buckaroos;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Login extends Activity implements OnClickListener  {
	
	EditText etUser, etLPass;
	Button bLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		initialize();
	}

	private void initialize() {
		etUser = (EditText) findViewById(R.id.etUser);
		etLPass = (EditText) findViewById(R.id.etLPass);
		bLogin = (Button) findViewById(R.id.bLogin);
		getActionBar().hide();
		bLogin.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		//TODO: continues to main app screen
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
