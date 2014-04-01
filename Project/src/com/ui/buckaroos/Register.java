package com.ui.buckaroos;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;

/**
 * This class defines a Register screen activity for the application.
 * 
 * @author Gene Hynson
 * @version 1.0
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Register extends Activity implements OnClickListener {

    private EditText etName, etEmail, etPass;
    private Button bRegister;
    private UserAccountController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    /**
     * Defines and initializes all fields and buttons
     */
    private void initialize() {
        controller = new UserAccountController(this);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        bRegister = (Button) findViewById(R.id.bRegister);
        getActionBar().hide();
        bRegister.setOnClickListener(this);
    }

    /**
     * All fields required, creates account, goes to main screen
     */
    @Override
    public void onClick(View v) {
        if (!etName.getText().toString().equals("")
                && !etEmail.getText().toString().equals("")
                && !etPass.getText().toString().equals("")) {
            if (controller.getLoginAccount(etName.getText().toString()) == null) {
                controller.addLoginAccount(etName.getText().toString(),
                        etPass.getText().toString(), etEmail.getText()
                                .toString());
                startActivity(new Intent(Register.this,
                        CreateAccount.class));
            } else {
                Toast toast =
                        Toast.makeText(this, "Account already exists",
                                Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            Toast toast =
                    Toast.makeText(this, "All fields required.",
                            Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
