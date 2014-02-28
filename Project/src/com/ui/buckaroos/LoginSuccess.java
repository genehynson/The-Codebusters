package com.ui.buckaroos;

import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;
import com.model.buckaroos.Account;
import com.model.buckaroos.User;
import com.utility.buckaroos.AppPropertyWriter;
import com.utility.buckaroos.CredentialConfirmer;

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
import android.widget.TextView;
import android.widget.Toast;
/**
 * The "main screen" of the app 
 * @author Gene
 *
 */
public class LoginSuccess extends Activity implements OnClickListener, OnCreateContextMenuListener {
	
	private Button addTransaction, makeAccount, accountOverview, changeAccount;
	private UserAccountController controller;
	private TextView currentAccountText;
	private TextView currentLoginUser;


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
	
	/**
	 * Defines fields, buttons, controller, currentAccount
	 */
	private void initialize() {
		addTransaction = (Button) findViewById(R.id.addTransaction);
		addTransaction.setOnClickListener(this);
		accountOverview = (Button) findViewById(R.id.viewAccount);
		accountOverview.setOnClickListener(this);
		changeAccount = (Button) findViewById(R.id.changeAccount);
		changeAccount.setOnClickListener(this);
		makeAccount = (Button) findViewById(R.id.createAccount);
		makeAccount.setOnClickListener(this);
		currentAccountText = (TextView) findViewById(R.id.currentAccountText);
		currentLoginUser = (TextView) findViewById(R.id.loginsuccess);
		controller = new UserAccountController(this);
		if (controller.getCurrentAccount() == null) {
			ensureCurrentAccount();
		}
		currentAccountText.setText(controller.getCurrentAccount().getName());
		currentLoginUser.setText(controller.getCurrentUser().get_accountName());
		
	}
	/**
	 * Gets current "bank" account
	 * @return
	 */
	private void ensureCurrentAccount() {
		if (controller.hasAccount()) {
			controller.setCurrentAccount(controller.getFirstUserAccount());
		} else {
			startActivity(new Intent(LoginSuccess.this, CreateAccount.class));
		}
		
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
		case R.id.addTransaction:
			startActivity(new Intent(LoginSuccess.this, Transaction.class));
			break;
		case R.id.createAccount:
			startActivity(new Intent(LoginSuccess.this, CreateAccount.class));
			break;
		case R.id.changeAccount:
			startActivity(new Intent(LoginSuccess.this, ChangeAccount.class));
			break;
		case R.id.viewAccount:
			startActivity(new Intent(LoginSuccess.this, AccountOverview.class));

		}
	}
	

}
