package com.ui.buckaroos;

import android.app.Activity;
import android.content.Intent;
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
 * This class defines an account creation activity. It Creates a "bank" account
 * activity.
 * 
 * @author Gene Hynson
 * @version 1.0
 * 
 */
public class CreateAccount extends Activity implements OnClickListener {

    private EditText accountName;
    private EditText accountNickName;
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

    /**
     * Defines and initializes all fields, buttons, and an instance of the
     * controller.
     */
    private void initialize() {
        accountName = (EditText) findViewById(R.id.accountName);
        accountNickName = (EditText) findViewById(R.id.accountNickName);
        startingBalance = (EditText) findViewById(R.id.startingBalance);
        interestRate = (EditText) findViewById(R.id.interestRate);
        create = (Button) findViewById(R.id.create);
        getActionBar().hide();
        create.setOnClickListener(this);
        controller = new UserAccountController(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_acount, menu);
        return true;
    }

    /**
     * Must have accountName filled, creates account, starts main screen
     * activity
     */
    @Override
    public void onClick(View v) {
        double balance = 0;
        double interest = 0;
        String nickname;
        if (!accountName.getText().toString().equals("")) {
            if (!startingBalance.getText().toString().equals("")) {
                balance =
                        Double.parseDouble(startingBalance.getText().toString());
            }
            if (!interestRate.getText().toString().equals("")) {
                interest =
                        Double.parseDouble(interestRate.getText().toString());
            }
            if (!accountNickName.getText().toString().equals("")) {
                nickname = accountNickName.getText().toString();
            } else {
                nickname = accountName.getText().toString();
            }

            controller.addAccount(accountName.getText().toString(), nickname,
                    balance, interest);
            startActivity(new Intent(CreateAccount.this, LoginSuccess.class));

        } else {
            Toast toast =
                    Toast.makeText(this, "All fields required.",
                            Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
