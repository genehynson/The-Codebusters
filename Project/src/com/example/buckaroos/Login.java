 package com.example.buckaroos;

import com.password.buckaroos.AppPropertyWriter;
import com.password.buckaroos.CredentialConfirmer;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
		AppPropertyWriter k = new AppPropertyWriter(this);
		CredentialConfirmer confirm = new CredentialConfirmer();
		if (confirm.doesAccountExist(etUser.getText().toString())) {
			if(confirm.isPasswordCorrect(etUser.getText().toString(), etLPass.getText().toString())) {
				startActivity(new Intent(Login.this, LoginSuccess.class));
			} else {
				Toast toast = Toast.makeText(this, "Password incorrect", Toast.LENGTH_SHORT);
				toast.show();
			}
		} else {
			Toast toast = Toast.makeText(this, "No such account.", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
