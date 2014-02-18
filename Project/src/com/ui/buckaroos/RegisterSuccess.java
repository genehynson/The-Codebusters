package com.ui.buckaroos;

import com.example.buckaroos.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RegisterSuccess extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_success);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_success, menu);
		return true;
	}

}
