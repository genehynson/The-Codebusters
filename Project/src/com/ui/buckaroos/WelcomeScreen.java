package com.ui.buckaroos;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.buckaroos.R;
import com.ui.buckaroos.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class WelcomeScreen extends Activity implements OnClickListener {

	Button bReg, bLog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_screen);
		getActionBar().hide();
		bReg = (Button) findViewById(R.id.bReg);
		bLog = (Button) findViewById(R.id.bLog);
		//need to add AppPropertyWriter?
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bReg:
			startActivity(new Intent(WelcomeScreen.this, Register.class));
			break;
		case R.id.bLog:
			startActivity(new Intent(WelcomeScreen.this, Login.class));
			break;
		}
	}

}
