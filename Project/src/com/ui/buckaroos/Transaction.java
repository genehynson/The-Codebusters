package com.ui.buckaroos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;

/**
 * Transaction for Buckaroos Transaction activity.
 * 
 * @author Daniel Carnauba
 * 
 */
public class Transaction extends Activity implements OnClickListener {

    private Button save;
    private EditText amount;
    private RadioButton withdraw, deposit;
    private UserAccountController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        initialize();
    }

    private void initialize() {
        amount = (EditText) findViewById(R.id.editAmount);
        save = (Button) findViewById(R.id.saveButton);
        withdraw = (RadioButton) findViewById(R.id.withdrawButton);
        deposit = (RadioButton) findViewById(R.id.depositButton);
        save.setOnClickListener(this);
        getActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transaction, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        // if (!etName.getText().toString().equals("")
        // && !etEmail.getText().toString().equals("")
        // && !etPass.getText().toString().equals("")) {
        // AppPropertyWriter k = new AppPropertyWriter(this);
        // k.storeAccount(etName.getText().toString(), etPass.getText()
        // .toString(), etEmail.getText().toString());
        // startActivity(new Intent(Transaction.this, LoginSuccess.class));
        //
        // } else {
        // Toast toast = Toast.makeText(this, "All fields required.",
        // Toast.LENGTH_SHORT);
        // toast.show();
        // }

    }

}
