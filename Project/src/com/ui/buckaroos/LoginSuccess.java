package com.ui.buckaroos;

import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;
import com.model.buckaroos.Account;
import com.model.buckaroos.User;
import com.password.buckaroos.AppPropertyWriter;
import com.password.buckaroos.CredentialConfirmer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginSuccess extends Activity implements OnClickListener {
	
	private Button withdraw, deposit, makeAccount;
	private EditText amount;
	private User user;
	private UserAccountController controller = new UserAccountController(user);
	private Account currentAccount;
	
	
	public LoginSuccess(User user) {
		this.user = user;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_success);
		initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_success, menu);
		return true;
	}

	
	private void initialize() {
		withdraw = (Button) findViewById(R.id.withdraw);
		getActionBar().hide();
		withdraw.setOnClickListener(this);
		deposit = (Button) findViewById(R.id.deposit);
		deposit.setOnClickListener(this);
		makeAccount = (Button) findViewById(R.id.createAccount);
		makeAccount.setOnClickListener(this);
		amount = (EditText) findViewById(R.id.amount);

	}
	
	private Account getCurrentAccount() {
		//TODO: get the actual current account
		return controller.getUserAccount("");
	}
	
	@Override
	public void onClick(View v) {
		currentAccount = getCurrentAccount();
		switch (v.getId()) {
		case R.id.withdraw:
			currentAccount.setAmount(Integer.parseInt(amount.getText().toString()));
			break;
		case R.id.deposit:
			currentAccount.setAmount(Integer.parseInt(amount.getText().toString()));
			break;
		case R.id.createAccount:
			//goes to new activity to enter account data
			//startActivity(new Intent(LoginSuccess.this, LoginSuccess.class));
			break;
		case R.id.changeAccount:
			//popup that allows user to select account
			break;
		}
	}
	

}
