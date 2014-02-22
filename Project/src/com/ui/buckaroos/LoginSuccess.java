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
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * The "main screen" of the app 
 * @author Gene
 *
 */
public class LoginSuccess extends Activity implements OnClickListener, OnCreateContextMenuListener {
	
	private Button withdraw, deposit, makeAccount;
	private EditText amount;
	private UserAccountController controller;
	private Account currentAccount;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_success);
		initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(R.id.logout);
		getMenuInflater().inflate(R.menu.login_success, menu);
		return true;
	}
	
//	@Override
//	public void onCreateContextMenu(ContextMenu menu, View v,
//	                                ContextMenuInfo menuInfo) {
//	    super.onCreateContextMenu(menu, v, menuInfo);
////	    menu.add(R.id.);
//	    MenuInflater inflater = getMenuInflater();
//	    inflater.inflate(R.menu.login_success, menu);
////	invalidateOptionsMenu ()
//	}

	/**
	 * Defines fields, buttons, controller, currentAccount
	 */
	private void initialize() {
		withdraw = (Button) findViewById(R.id.withdraw);
		withdraw.setOnClickListener(this);
		deposit = (Button) findViewById(R.id.deposit);
		deposit.setOnClickListener(this);
		makeAccount = (Button) findViewById(R.id.createAccount);
		makeAccount.setOnClickListener(this);
		amount = (EditText) findViewById(R.id.amount);
		controller = new UserAccountController();
		currentAccount = getCurrentAccount();
//		registerForContextMenu(makeAccount);

	}
	/**
	 * Gets current "bank" account
	 * @return
	 */
	private Account getCurrentAccount() {
//		if (controller.hasAccount()) {
//			return controller.getFirstUserAccount();
//		} else {
			startActivity(new Intent(LoginSuccess.this, CreateAccount.class));
			return null;
//		}
		
	}
	/**
	 * adds "logout" option to dropdown menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.logout:
				startActivity(new Intent(LoginSuccess.this, WelcomeScreen.class));
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Depending on what button was clicked, do something
	 */
	@Override
	public void onClick(View v) {
		if (!controller.hasAccount()) {
			v.setId(R.id.createAccount);
		}
		switch (v.getId()) {
		case R.id.withdraw:
			controller.addWithdrawal(Integer.parseInt(amount.getText().toString()), null, null);
			break;
		case R.id.deposit:
			controller.addDeposit(Integer.parseInt(amount.getText().toString()), null, null);
			break;
		case R.id.createAccount:
			startActivity(new Intent(LoginSuccess.this, CreateAccount.class));
			break;
		case R.id.changeAccount:
			//popup that allows user to select account
			break;
		}
	}
	

}
