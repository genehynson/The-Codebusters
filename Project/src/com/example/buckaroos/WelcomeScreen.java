package com.example.buckaroos;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.buckaroos.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class WelcomeScreen extends Activity {
	
	Thread t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_screen);
		t = new Thread(){
			public void run() {
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent nn = new Intent(WelcomeScreen.this, Login.class);
					startActivity(nn);
				}
			}
		};
		t.start();
	}

}
