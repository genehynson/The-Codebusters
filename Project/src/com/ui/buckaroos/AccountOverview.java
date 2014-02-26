package com.ui.buckaroos;

import com.example.buckaroos.R;
import com.example.buckaroos.R.layout;
import com.example.buckaroos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AccountOverview extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_overview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_overview, menu);
		return true;
	}

}
