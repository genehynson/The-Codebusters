package com.example.buckaroos;

import com.controller.buckaroos.UserAccountController;
import com.model.buckaroos.User;
import com.password.buckaroos.AppPropertyWriter;
import com.ui.buckaroos.LoginSuccess;
import com.ui.buckaroos.Register;
import com.ui.buckaroos.RegisterSuccess;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends Activity implements OnClickListener {

	private EditText accountName;
	private EditText startingBalance;
	private EditText interestRate;
	private Button create;
	private UserAccountController controller;
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		initialize();
	}
	
	private void initialize() {
		accountName = (EditText) findViewById(R.id.accountName);
		startingBalance = (EditText) findViewById(R.id.startingBalance);
		interestRate = (EditText) findViewById(R.id.interestRate);
		create = (Button) findViewById(R.id.create);
		getActionBar().hide();
		create.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_acount, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(!accountName.getText().toString().equals("") && !startingBalance.getText().toString().equals("") && !interestRate.getText().toString().equals("")) {
			controller.addAccount(accountName.getText().toString(), Integer.parseInt(startingBalance.getText().toString()), Double.parseDouble(interestRate.getText().toString()));
			startActivity(new Intent(CreateAccount.this, LoginSuccess.class));

		} else {
			Toast toast = Toast.makeText(this, "All fields required.", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}
