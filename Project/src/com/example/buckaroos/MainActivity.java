 package com.example.buckaroos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener  {
	
	EditText etUser, etPass;
	Button bLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initialize();
	}

	private void initialize() {
		etUser = (EditText) findViewById(R.id.etUser);
		etPass = (EditText) findViewById(R.id.etPass);
		bLogin = (Button) findViewById(R.id.bLogin);
		
		bLogin.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
