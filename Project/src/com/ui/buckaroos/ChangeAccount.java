package com.ui.buckaroos;

import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;
import com.example.buckaroos.R.id;
import com.example.buckaroos.R.layout;
import com.example.buckaroos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeAccount extends Activity implements OnClickListener {

	private Button save;
	private EditText changeAccount;
	private UserAccountController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_account);
		initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_account, menu);
		return true;
	}
	
	public void initialize() {
		changeAccount = (EditText) findViewById(R.id.changeAccount);
		save = (Button) findViewById(R.id.saveChangeAccount);
		save.setOnClickListener(this);
		controller = new UserAccountController(this);
		getActionBar().hide();
	}

	@Override
	public void onClick(View v) {
		if (controller.getUserAccount(changeAccount.getText().toString()) != null) {
			controller.setCurrentAccount(controller.getUserAccount(changeAccount.getText().toString()));
			startActivity(new Intent(ChangeAccount.this, LoginSuccess.class));
		} else {
			Toast toast = Toast.makeText(this, "No such account.", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}
